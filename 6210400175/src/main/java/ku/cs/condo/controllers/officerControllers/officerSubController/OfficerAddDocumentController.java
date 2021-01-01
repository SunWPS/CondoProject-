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

public class OfficerAddDocumentController {

    private OfficerList officer;
    private ImageService imageService;
    private StorageFileDataSource storageData;
    private File image;
    private String theme;


    @FXML private Button backBtn, okBtn, browseBtn;
    @FXML private TextField receiverField, buildingField, floorField, roomNumField, senderField;
    @FXML private Label imagePathLabel, alertLabel;
    @FXML private ImageView imagePic, backImage;
    @FXML private ComboBox<String>  importantLVCombo, sizeCombo;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();
                storageData = new StorageFileDataSource("data", "storage.csv");
                ObservableList<String> sizes = FXCollections.observableArrayList("10 x 14 inch", "9 x 12 inch"
                        , "7 x 10 inch", "5 x 8 inch", "4.5 x 7 inch");
                sizeCombo.setItems(sizes);
                ObservableList<String> imLV = FXCollections.observableArrayList("General", "Confidential"
                        , "Government", "Legal");
                importantLVCombo.setItems(imLV);

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
            String importantLV = importantLVCombo.getSelectionModel().getSelectedItem();

            if(!checkInput()) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerSubPage/confirm_add_item.fxml"));
                stage.setScene(new Scene(loader.load(), 400, 300));
                stage.setTitle("Confirm");
                stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
                stage.centerOnScreen();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                ConfirmAddItemController confirm = loader.getController();
                confirm.setTheme(theme);
                stage.showAndWait();
                if (confirm.finish()) {
                    String fileName = imageService.copyImage("itemPicture", image);
                    storageData.appendItem(receiverField.getText().trim(), buildingField.getText().trim(), floorField.getText().trim(), roomNumField.getText().trim()
                            , size, senderField.getText().trim()
                            , fileName, officer.getCurrentAccount().getName(), importantLV);

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
                || floorField.getText().trim().equals("") || buildingField.getText().trim().equals("") || imagePathLabel.getText().trim().equals("");
    }


    public void setOfficer(OfficerList officer) {
        this.officer = officer;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
