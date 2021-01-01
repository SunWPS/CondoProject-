package ku.cs.condo.controllers.officerControllers.officerSubController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.models.room.Room;

import java.io.IOException;


public class AddResidentToSimplexController {

    private Room room;
    private boolean finishWork;
    private String theme;

    @FXML private Label buildingLabel, floorLabel, typeLabel, roomNumLabel, errorLabel;
    @FXML private Button addBtn;
    @FXML private TextField nameField;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                buildingLabel.setText(room.getBuilding());
                floorLabel.setText(room.getFloor());
                typeLabel.setText(room.getType());
                roomNumLabel.setText(room.getRoomNumber());
                finishWork = false;

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
        errorLabel.setText(null);
        if(nameField.getText().trim().isEmpty()){
            errorLabel.setText("Please input room owner's name!");
        }
        else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerSubPage/confirm_add_resident.fxml")
            );
            stage.setScene(new Scene(loader.load(), 400, 300));
            ConfirmAddResidentController of = loader.getController();
            of.setTheme(theme);
            stage.setTitle("Confirm");
            stage.centerOnScreen();
            stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            if(of.finish()){
                room.setStatus("Taken");
                room.setRoomOwner(nameField.getText().trim());
                finishWork = true;
                Stage nowStage = (Stage) addBtn.getScene().getWindow();
                nowStage.close();
            }
        }
    }

    public boolean finish(){
        return finishWork;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
