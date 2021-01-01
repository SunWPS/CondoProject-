package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.officerControllers.officerSubController.*;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.models.room.Building;
import ku.cs.condo.models.room.Room;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.services.etcService.BuildingFileDataSource;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.accountDataSource.ResidentFileDataSource;

import java.io.IOException;


public class OfficerShowRoomController extends OfficerParentController{

    private BuildingFileDataSource buildingsData;
    private Building building;
    private Room selectedRoom;
    private ImageService imageService;
    ObservableList<Room> roomsList;
    FilteredList<Room> roomFilteredList;

    @FXML private ImageView imageProfile, logoImage, menuImage1, menuImage2;
    @FXML private Label usernameLabel, buildingLabel, floorLabel, roomNumLabel, typeLabel, statusLabel;
    @FXML private TextField residentFilterField;
    @FXML private Button officerHomeBtn, addRoomBtn, itemOutBtn, showItemBtn
            , rePassBtn, logoutBtn, homeBtn, addResidentInfoBtn, editInfoBtn
            , checkOutBtn, menuBtn, menuBtn2;
    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, String> buildingCol, floorCol, roomNumCol, typeCol, statusCol, ownerCol, coOwnerCol;
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
        addResidentInfoBtn.setDisable(true);
        editInfoBtn.setDisable(true);
        checkOutBtn.setDisable(true);
        buildingsData =  new BuildingFileDataSource("data", "condo.csv");
        building = buildingsData.getBuildingData();

        showData();

        roomTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{
                if(newValue != null){
                    setSelectedRoom(newValue);
                }
            }
        ));
    }

    private void showData(){
        roomsList = FXCollections.observableList(building.sortRoom());


        buildingCol.setCellValueFactory(new PropertyValueFactory<Room,String>("building"));
        floorCol.setCellValueFactory(new PropertyValueFactory<Room,String>("floor"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Room,String>("roomNumber"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Room,String>("type"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Room,String>("status"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<Room,String>("roomOwner"));
        coOwnerCol.setCellValueFactory(new PropertyValueFactory<Room,String>("coOwner"));

        roomFilteredList = new FilteredList<>(roomsList, b->true);

        residentFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            roomFilteredList.setPredicate(room -> {
                if(newValue == null || newValue.isEmpty()){
                    return  true;
                }

                String data = newValue.toLowerCase();

                if(room.getRoomOwner().toLowerCase().contains(data)){
                    return true;
                }
                else if(room.getType().equals("Duplex")){
                    return room.getCoOwner().toLowerCase().contains(data);
                }
                return  false;
            });
        });

        SortedList<Room> sortedRooms = new SortedList<>(roomFilteredList);
        sortedRooms.comparatorProperty().bind(roomTable.comparatorProperty());
        roomTable.setItems(sortedRooms);
    }

    private void setSelectedRoom(Room room){
        selectedRoom = room;
        buildingLabel.setText(selectedRoom.getBuilding());
        floorLabel.setText(selectedRoom.getFloor());
        roomNumLabel.setText(selectedRoom.getRoomNumber());
        typeLabel.setText(selectedRoom.getType());
        statusLabel.setText(selectedRoom.getStatus());
        if(selectedRoom.getStatus().equals("Available")){
            addResidentInfoBtn.setDisable(false);
            editInfoBtn.setDisable(true);
            checkOutBtn.setDisable(true);
        }
        else if(selectedRoom.getStatus().equals("Taken")){
            addResidentInfoBtn.setDisable(true);
            editInfoBtn.setDisable(false);
            checkOutBtn.setDisable(false);
        }

    }

    private void clearSelected(){
        selectedRoom = null;
        buildingLabel.setText(null);
        floorLabel.setText(null);
        roomNumLabel.setText(null);
        typeLabel.setText(null);
        statusLabel.setText(null);
        addResidentInfoBtn.setDisable(true);
        editInfoBtn.setDisable(true);
        checkOutBtn.setDisable(true);
        roomTable.getSelectionModel().clearSelection();
    }

    @FXML public void handleAddRoomBtnOnAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerMainPage/officer_add_room.fxml"));
        stage.setScene(new Scene(loader.load(), 586, 442));
        stage.setTitle("Add Room");
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        OfficerAddRoomController addRoom = loader.getController();
        addRoom.setBuilding(building);
        addRoom.setTheme(getTheme());
        stage.setResizable(false);
        stage.showAndWait();
        if(addRoom.finish()){
            showData();
        }
        roomTable.refresh();
    }

    @FXML public void handleAddResidentInfoBtnOnAction(ActionEvent event) throws  IOException{
        if(selectedRoom.getType().equals("Simplex")) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerSubPage/add_resident_to_simplex.fxml")

            );
            stage.setScene(new Scene(loader.load(), 750, 550));
            AddResidentToSimplexController of = loader.getController();
            stage.setTitle("Confirm");
            stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            of.setRoom(selectedRoom);
            of.setTheme(getTheme());
            stage.showAndWait();
            if(of.finish()){
                buildingsData.setBuildingData(building);
                roomTable.refresh();
                clearSelected();
            }
        }
        else if(selectedRoom.getType().equals("Duplex")){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerSubPage/add_resident_to_duplex.fxml")

            );
            stage.setScene(new Scene(loader.load(), 750, 550));
            AddResidentToDuplexController of = loader.getController();
            stage.setTitle("Confirm");
            stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            of.setRoom(selectedRoom);
            of.setTheme(getTheme());
            stage.showAndWait();
            if(of.finish()){
                buildingsData.setBuildingData(building);
                roomTable.refresh();
                clearSelected();
            }
        }
    }

    @FXML public void handleEditInfoBtnOnAction(ActionEvent event) throws IOException{
        if(selectedRoom.getType().equals("Simplex")) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerSubPage/edit_simplex_info.fxml")

            );
            stage.setScene(new Scene(loader.load(), 697, 333));
            EditSimplexInfoController of = loader.getController();
            stage.setTitle("Edit Info");
            stage.centerOnScreen();
            stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            of.setRoom(selectedRoom);
            of.setTheme(getTheme());
            stage.showAndWait();
            if(of.finish()){
                buildingsData.setBuildingData(building);
                roomTable.refresh();
                clearSelected();
            }
        }
        else if(selectedRoom.getType().equals("Duplex")){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerSubPage/edit_duplex_info.fxml")

            );
            stage.setScene(new Scene(loader.load(), 697, 442));
            EditDuplexInfoController of = loader.getController();
            stage.setTitle("Edit Info");
            stage.centerOnScreen();
            stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            of.setRoom(selectedRoom);
            of.setTheme(getTheme());
            stage.showAndWait();
            if(of.finish()){
                buildingsData.setBuildingData(building);
                roomTable.refresh();
                clearSelected();
            }
        }
    }

    @FXML public void handleCheckOutBtnOnAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerSubPage/confirm_check_out.fxml"));
        stage.setScene(new Scene(loader.load(), 400, 300));
        stage.setTitle("Confirm");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        ConfirmCheckOutController confirm = loader.getController();
        confirm.setTheme(getTheme());
        stage.showAndWait();
        if(confirm.finish()) {
            selectedRoom.checkOut();
            ResidentFileDataSource residentsData = new ResidentFileDataSource("data", "resident.csv");
            ResidentList residentsList = residentsData.getResidentsData();
            residentsList.deleteAccount(selectedRoom.getRoomNumber());
            residentsData.setData(residentsList);

            clearSelected();
            roomTable.refresh();
            buildingsData.setBuildingData(building);

        }
    }

    @FXML public void handleMenuBtnOnAction(ActionEvent event){
        menuPane.toFront();
    }

    @FXML public void handleMenuBtn2OnAction(ActionEvent event){
        workPane.toFront();
    }


}
