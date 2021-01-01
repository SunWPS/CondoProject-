package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class OfficerShowItemOutController extends OfficerParentController {

    private StorageFileDataSource storageData;
    private Storage storage;
    private ImageService imageService;
    private Item selectedItem;
    ObservableList<Item> itemsData;
    FilteredList<Item> itemFilteredList;

    @FXML private ImageView imageProfile, itemImage, logoImage, menuImage1, menuImage2;
    @FXML private Label usernameLabel, receiverLabel;
    @FXML private TextField roomNumFilterField;
    @FXML private Button officerHomeBtn, showRoomBtn, showItemBtn, rePassBtn, logoutBtn, homeBtn, menuBtn, menuBtn2, fullInfoBtn;
    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> buildingCol, floorCol, roomNumCol, receiverCol, senderCol, typeCol, dateTimeOutCol, officerOutCol, residentOutCol;
    @FXML private Pane workPane, menuPane;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();

                usernameLabel.setText(getOfficer().getCurrentAccount().getUsername());

                imageProfile.setImage(new Image(imageService.getImage("officerPicture", getOfficer().getCurrentAccount().getImagePath())));

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

        storageData = new StorageFileDataSource("data", "storage.csv");
        storage = storageData.getStorageData();
        fullInfoBtn.setDisable(true);
        showData();
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null){
                selectedItem(newValue);
            }
        });
    }

    private void showData(){
        itemsData = FXCollections.observableList(storage.sortTakeOutItemsByDateOut());

        buildingCol.setCellValueFactory(new PropertyValueFactory<Item,String>("building"));
        floorCol.setCellValueFactory(new PropertyValueFactory<Item,String>("floor"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Item,String>("roomNum"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<Item,String>("receiver"));
        senderCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sender"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
        dateTimeOutCol.setCellValueFactory(new PropertyValueFactory<Item,String>("dateTimeOut"));
        officerOutCol.setCellValueFactory(new PropertyValueFactory<Item,String>("officerOut"));
        residentOutCol.setCellValueFactory(new PropertyValueFactory<Item,String>("residentOut"));

        itemFilteredList = new FilteredList<>(itemsData, b->true);

        roomNumFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            itemFilteredList.setPredicate(item ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String data = newValue.toLowerCase();

                if(item.getRoomNum().toLowerCase().contains(data)){
                    return true;
                }
                return false;
            });
        });

        SortedList<Item> sortedItems = new SortedList<>(itemFilteredList);

        sortedItems.comparatorProperty().bind(itemTable.comparatorProperty());
        itemTable.setItems(sortedItems);
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
                full.setTheme(getTheme());
                full.setItem(selectedItem);
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
                full.setTheme(getTheme());
                full.setItem(((Document) selectedItem));
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
                full.setTheme(getTheme());
                full.setItem(((Package) selectedItem));
                break;
            }
        }
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    private void selectedItem(Item item){
        selectedItem = item;
        receiverLabel.setText(selectedItem.getReceiver());
        itemImage.setImage(new Image(imageService.getImage("itemPicture", selectedItem.getImagePath())));
        fullInfoBtn.setDisable(false);
    }

    @FXML public void handleMenuBtnOnAction(ActionEvent event){
        menuPane.toFront();
    }
    @FXML public void handleMenuBtn2OnAction(ActionEvent event){
        workPane.toFront();
    }
}
