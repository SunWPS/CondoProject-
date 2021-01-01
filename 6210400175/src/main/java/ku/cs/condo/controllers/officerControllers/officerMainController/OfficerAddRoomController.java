package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.stage.Modality;
import ku.cs.condo.controllers.officerControllers.officerSubController.ConfirmAddRoomController;
import ku.cs.condo.exception.WrongAddRoomInfoException;
import ku.cs.condo.models.room.Building;
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
import javafx.stage.Stage;
import ku.cs.condo.models.room.Room;
import ku.cs.condo.services.etcService.BuildingFileDataSource;

import java.io.IOException;

public class OfficerAddRoomController extends OfficerParentController{

    private BuildingFileDataSource buildingsData;
    private Building building;
    private Room room;
    private boolean finishWork;
    private String theme;

    @FXML private Label allError, roomNumError;
    @FXML private Button addBtn;
    @FXML private ComboBox<String> buildingCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField roomNumField, floorField;


    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                buildingsData = new BuildingFileDataSource("data", "condo.csv");
                finishWork = false;

                ObservableList<String> roomType = FXCollections.observableArrayList("Simplex", "Duplex");
                typeCombo.setItems(roomType);
                ObservableList<String> buildingName = FXCollections.observableArrayList("A", "B");
                buildingCombo.setItems(buildingName);

                Scene thisScene = addBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }

    @FXML public void handleAddBtnOnAction(ActionEvent event) throws IOException{
        clearErrorLabel();

        try {
            String buildingStr = buildingCombo.getSelectionModel().getSelectedItem();
            String typeStr = typeCombo.getSelectionModel().getSelectedItem();

            if(!floorField.getText().trim().isEmpty() && !roomNumField.getText().trim().isEmpty()){
                try {
                    boolean check = building.checkNewRoom(buildingStr, floorField.getText().trim(), roomNumField.getText().trim());
                    if(check){
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/officerPage/officerSubPage/confirm_add_room.fxml"));
                        stage.setScene(new Scene(loader.load(), 400, 300));
                        stage.setTitle("Confirm");
                        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
                        stage.centerOnScreen();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setResizable(false);
                        ConfirmAddRoomController confirm = loader.getController();
                        confirm.setTheme(theme);
                        stage.showAndWait();
                        if (confirm.finish()) {
                            finishWork = true;
                            buildingsData.appendRoom(buildingStr, floorField.getText().trim(), roomNumField.getText().trim(), typeStr);
                            if(typeStr.equals("Simplex")){
                                room = new Room(buildingStr,floorField.getText().trim(),roomNumField.getText().trim(),"Available", "");
                                building.add(room);
                            }
                            else if(typeStr.equals("Duplex")) {
                                room = new Room(buildingStr, floorField.getText().trim(), roomNumField.getText().trim(), "Available", "", "");
                                building.add(room);
                            }
                            Button b = (Button) event.getSource();
                            Stage stageNow = (Stage) b.getScene().getWindow();
                            stageNow.close();
                        }
                    }
                }catch (WrongAddRoomInfoException e){
                    roomNumError.setText(e.getMessage());
                }

            }else{
                allError.setText("Please input all required fields!");
            }

        }catch (IndexOutOfBoundsException e){
            allError.setText("Please input all required fields!");
        }

    }

    private void clearErrorLabel(){
        roomNumError.setText(null);
        allError.setText(null);
    }

    public Room getRoom() {
        return room;
    }

    public boolean finish(){
        return finishWork;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
