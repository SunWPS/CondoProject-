package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.fullInformationController.FullDocumentController;
import ku.cs.condo.controllers.fullInformationController.FullLetterController;
import ku.cs.condo.controllers.fullInformationController.FullPackageController;
import ku.cs.condo.controllers.officerControllers.officerSubController.ConfirmItemExitController;
import ku.cs.condo.controllers.officerControllers.officerSubController.OfficerAddDocumentController;
import ku.cs.condo.controllers.officerControllers.officerSubController.OfficerAddLetterController;
import ku.cs.condo.controllers.officerControllers.officerSubController.OfficerAddPackageController;
import ku.cs.condo.models.storage.Document;
import ku.cs.condo.models.storage.Item;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.models.storage.Package;
import ku.cs.condo.models.storage.Storage;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.etcService.StorageFileDataSource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfficerShowItemController extends OfficerParentController{

    private StorageFileDataSource storageData;
    private Storage storage;
    private ImageService imageService;
    private Item selectedItem;
    ObservableList<Item> itemsData;
    FilteredList<Item> itemFilteredList;

    @FXML private ImageView imageProfile, itemImage, logoImage, menuImage1, menuImage2;
    @FXML private Label usernameLabel, receiverLabel;
    @FXML private TextField roomNumFilterField;
    @FXML private Button officerHomeBtn, showRoomBtn, itemOutBtn
                        , rePassBtn, logoutBtn, homeBtn, menuBtn, menuBtn2, fullInfoBtn, changeStatusBtn
                        , addLetterBtn, addDocBtn, addPackageBtn;
    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> buildingCol, floorCol, roomNumCol, receiverCol, senderCol, typeCol, dateTimeCol, officerGetCol;
    @FXML private Pane workPane, menuPane;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();

                usernameLabel.setText(getOfficer().getCurrentAccount().getUsername());

                imageProfile.setImage(new Image(imageService.getImage("officerPicture", getOfficer().getCurrentAccount().getImagePath())));

                Scene thisScene = homeBtn.getScene();
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
        changeStatusBtn.setDisable(true);
        fullInfoBtn.setDisable(true);
        showData();
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null){
                selectedItem(newValue);
            }
        });
    }

    private void showData(){
        itemsData = FXCollections.observableList(storage.sortStillItemsByDate());

        buildingCol.setCellValueFactory(new PropertyValueFactory<Item,String>("building"));
        floorCol.setCellValueFactory(new PropertyValueFactory<Item,String>("floor"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Item,String>("roomNum"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<Item,String>("receiver"));
        senderCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sender"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("dateTime"));
        officerGetCol.setCellValueFactory(new PropertyValueFactory<Item,String>("officerGet"));

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

    private void selectedItem(Item item){
        selectedItem = item;
        receiverLabel.setText(selectedItem.getReceiver());
        itemImage.setImage(new Image(imageService.getImage("itemPicture", selectedItem.getImagePath())));
        changeStatusBtn.setDisable(false);
        fullInfoBtn.setDisable(false);
    }

    @FXML public void handleChangeStatusBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerSubPage/confirm_item_exit.fxml"));
        stage.setScene(new Scene(loader.load(), 600, 400));
        stage.setTitle("Confirm");
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        ConfirmItemExitController confirm = loader.getController();
        confirm.setTheme(getTheme());
        stage.showAndWait();
        if(confirm.finish()){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            selectedItem.changeStatus(getOfficer().getCurrentAccount().getName(), confirm.getResidentGet(),
                    dateFormat.format(date));
            storageData.setStorageData(storage);
            itemsData.remove(selectedItem);
            clearSelected();
            itemTable.refresh();
        }
    }

    private void clearSelected(){
        selectedItem = null;
        receiverLabel.setText("");
        itemImage.setImage(new Image("/IconAndLogo/etcImage/1872739.png"));
        changeStatusBtn.setDisable(true);
        fullInfoBtn.setDisable(true);
    }

    @FXML public void handleFullInfoBtnOnAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));

        switch (selectedItem.getType()) {
            case "Letter": {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/fullInformationPage/full_letter.fxml")

                );
                stage.setScene(new Scene(loader.load(), 900, 400));
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
                stage.setScene(new Scene(loader.load(), 900, 400));
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
                stage.setScene(new Scene(loader.load(), 900, 400));
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


    @FXML public void handleAddLetterBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerSubPage/officer_add_letter.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setResizable(false);
        OfficerAddLetterController of = loader.getController();
        of.setTheme(getTheme());
        of.setOfficer(getOfficer());
        stage.show();
    }

    @FXML public void handleAddDocumentBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerSubPage/officer_add_document.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setResizable(false);
        OfficerAddDocumentController of = loader.getController();
        of.setTheme(getTheme());
        of.setOfficer(getOfficer());
        stage.show();
    }

    @FXML public void handleAddPackageBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerSubPage/officer_add_package.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setResizable(false);
        OfficerAddPackageController of = loader.getController();
        of.setTheme(getTheme());
        of.setOfficer(getOfficer());
        stage.show();
    }

    @FXML public void handleMenuBtnOnAction(ActionEvent event){
        menuPane.toFront();
    }
    @FXML public void handleMenuBtn2OnAction(ActionEvent event){
        workPane.toFront();
    }

}

