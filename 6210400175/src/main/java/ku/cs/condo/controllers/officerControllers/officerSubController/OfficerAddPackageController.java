package ku.cs.condo.controllers.officerControllers.officerSubController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.officerControllers.officerMainController.OfficerShowItemController;
import ku.cs.condo.models.account.OfficerList;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.etcService.StorageFileDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class OfficerAddPackageController {
    private OfficerList officer;
    private ImageService imageService;
    private StorageFileDataSource storageData;
    private File image;
    private String theme;

    @FXML private Button backBtn, okBtn, browseBtn;
    @FXML private TextField receiverField, buildingField, floorField, roomNumField, senderField, trackNumField;
    @FXML private Label imagePathLabel, alertLabel;
    @FXML private ImageView imagePic, backImage;
    @FXML private ComboBox<String> postOfficeCombo, sizeCombo;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();
                storageData = new StorageFileDataSource("data", "storage.csv");
                ObservableList<String> sizes = FXCollections.observableArrayList("14 x 20 x 6 cm", "17 x 25 x 9 cm", "20 x 30 x 11 cm",
                        "22 x 35 x 14 cm", "24 x 40 x 17 cm", "30 x 45 x 20 cm", "31 x 36 x 26 cm", "40 x 45 x 34 cm", "45 x 55 x 40 cm", " > (45 x 55 x 40 cm)");
                sizeCombo.setItems(sizes);
                ObservableList<String> postOffice = FXCollections.observableArrayList("Thai Post", "Kerry", "CJ GLS", "alpha", "SCG Express",
                        "DHL", "ups", "FedEx", "LALAMOVE", "FLASH", "TNT", "NINJA", "SPEED-D");
                postOfficeCombo.setItems(postOffice);

                Scene thisScene = backBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    backImage.setImage(new Image("/IconAndLogo/abstract/absYellowBack.png"));
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    backImage.setImage(new Image("/IconAndLogo/classic/back.png"));
                }
            }
        });
    }

    @FXML public void handleBrowseBtnOnAction(ActionEvent event) throws IOException{
        try {
            FileChooser fc = new FileChooser();
            File jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
            fc.setInitialDirectory(new File(jarDir.getParentFile().toString() + File.separator + "imageForTest"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fc.showOpenDialog(null);
            imagePathLabel.setText(selectedFile.getName());
            this.image = selectedFile;
            try {
                imagePic.setImage(new Image(selectedFile.toURI().toString()));
            } catch (NullPointerException ignored) { }
        }catch (NullPointerException | URISyntaxException ignored){ }
    }

    @FXML public void handleOkBtnOnAction(ActionEvent event) throws IOException {
        alertLabel.setText(null);
        try {
            String size = sizeCombo.getSelectionModel().getSelectedItem();
            String postOffice = postOfficeCombo.getSelectionModel().getSelectedItem();

            if(!checkInput()) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerSubPage/confirm_add_item.fxml"));
                stage.setScene(new Scene(loader.load(), 400, 300));
                stage.setTitle("Confirm");
                stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                ConfirmAddItemController confirm = loader.getController();
                confirm.setTheme(theme);
                stage.showAndWait();
                if (confirm.finish()) {
                    String fileName = imageService.copyImage("itemPicture", image);
                    storageData.appendItem(receiverField.getText().trim(), buildingField.getText().trim(), floorField.getText().trim(), roomNumField.getText().trim()
                            , size, senderField.getText().trim()
                            , fileName, officer.getCurrentAccount().getName(), postOffice
                            , trackNumField.getText().trim());

                    Button b = (Button) event.getSource();
                    Stage stageBack = (Stage) b.getScene().getWindow();
                    FXMLLoader loaderBack = new FXMLLoader(
                            getClass().getResource("/fxml/officerPage/officerMainPage/officer_show_item.fxml")

                    );
                    stageBack.setScene(new Scene(loaderBack.load(), 800, 600));
                    OfficerShowItemController of = loaderBack.getController();
                    of.setTheme(theme);
                    of.setOfficer(officer);
                    stageBack.show();
                }
            }else{
                alertLabel.setText("Please input all required fields!");
            }
        }catch (IndexOutOfBoundsException e){
            alertLabel.setText("Please input all required fields!");
        }
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_show_item.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerShowItemController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    private boolean checkInput(){
        return  receiverField.getText().trim().equals("") || senderField.getText().trim().equals("") || roomNumField.getText().trim().equals("")
                || floorField.getText().trim().equals("") || buildingField.getText().trim().equals("") || trackNumField.getText().trim().equals("")
                ||imagePathLabel.getText().trim().equals("");
    }


    public void setOfficer(OfficerList officer) {
        this.officer = officer;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
