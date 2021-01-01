package ku.cs.condo.controllers.residentControllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.fullInformationController.FullDocumentController;
import ku.cs.condo.controllers.fullInformationController.FullLetterController;
import ku.cs.condo.controllers.fullInformationController.FullPackageController;
import ku.cs.condo.models.storage.Document;
import ku.cs.condo.models.storage.Item;
import ku.cs.condo.models.storage.Package;
import ku.cs.condo.models.storage.Storage;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.etcService.StorageFileDataSource;

import java.io.IOException;

public class ResidentShowItemOutController extends ResidentParentController{
    private StorageFileDataSource storageData;
    private Storage storage;
    private Item selectedItem;
    private ImageService imageService;
    ObservableList<Item> itemsData;

    @FXML
    private ImageView  itemImage;
    @FXML private Button homeBtn, showItemBtn, rePassBtn, logoutBtn, menuBtn, menuBtn2, fullInfoBtn;
    @FXML private Label usernameLabel;
    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> roomNumCol, nameCol, senderCol, typeCol, dateOutCol, residentOutCol;
    @FXML private Pane workPane, menuPane;
    @FXML private ImageView logoImage, menuImage1, menuImage2;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();

                usernameLabel.setText(getResident().getCurrentAccount().getUsername());

                storageData = new StorageFileDataSource("data", "storage.csv");
                storage = storageData.getStorageData();
                itemsData = FXCollections.observableList(storage.findItemByResident(getResident().getCurrentAccount(), "Take Out"));

                roomNumCol.setCellValueFactory(new PropertyValueFactory<Item,String>("roomNum"));
                nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("receiver"));
                senderCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sender"));
                typeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
                dateOutCol.setCellValueFactory(new PropertyValueFactory<Item,String>("dateTime"));
                residentOutCol.setCellValueFactory(new PropertyValueFactory<Item,String>("residentOut"));

                itemTable.setItems(itemsData);

                Scene thisScene= homeBtn.getScene();
                if(getTheme().equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                    menuImage1.setImage(new Image("/IconAndLogo/abstract/absMenuIcon.png"));
                    menuImage2.setImage(new Image("/IconAndLogo/abstract/absMenuIcon.png"));
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                    menuImage1.setImage(new Image("/IconAndLogo/classic/56763.png"));
                    menuImage2.setImage(new Image("/IconAndLogo/classic/56763.png"));
                }
            }
        });
        fullInfoBtn.setDisable(true);
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null){
                selectedItem(newValue);
            }
        });
    }

    private void selectedItem(Item item){
        selectedItem = item;
        itemImage.setImage(new Image(imageService.getImage("itemPicture", selectedItem.getImagePath())));
        fullInfoBtn.setDisable(false);
    }

    @FXML public void handleFullInfoBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));

        switch (selectedItem.getType()) {
            case "Letter": {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/fullInformationPage/full_letter.fxml")

                );
                stage.setScene(new Scene(loader.load(), 900, 596));
                stage.setTitle("Full Letter Information");
                stage.setResizable(false);
                FullLetterController full = loader.getController();
                full.setItem(selectedItem);
                full.setTheme(getTheme());
                break;
            }
            case "Document": {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/fullInformationPage/full_document.fxml")

                );
                stage.setScene(new Scene(loader.load(), 900, 596));
                stage.setTitle("Full Document Information");
                stage.setResizable(false);
                FullDocumentController full = loader.getController();
                full.setItem(((Document) selectedItem));
                full.setTheme(getTheme());
                break;
            }
            case "Package": {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/fullInformationPage/full_package.fxml")

                );
                stage.setScene(new Scene(loader.load(), 900, 596));
                stage.setTitle("Full Package Information");
                stage.setResizable(false);
                FullPackageController full = loader.getController();
                full.setItem(((Package) selectedItem));
                full.setTheme(getTheme());
                break;
            }
        }
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML public void handleMenuBtnOnAction(ActionEvent event) {
        menuPane.toFront();
    }
    @FXML public void handleMenuBtn2OnAction(ActionEvent event){
        workPane.toFront();
    }
}
