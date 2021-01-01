package ku.cs.condo.controllers.officerControllers.officerSubController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.condo.models.room.Room;

public class EditDuplexInfoController {

    private Room room;
    private boolean finishWork;
    private String theme;

    @FXML
    private TextField editField, editField2;
    @FXML private Button okBtn;
    @FXML private Label errorLabel;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                finishWork = false;
                editField.setText(room.getRoomOwner());
                editField2.setText(room.getCoOwner());

                Scene thisScene = editField.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }

    @FXML public void handleOkBtnOnAction(ActionEvent event){
        errorLabel.setText(null);
        if(!(editField.getText().trim().isEmpty())) {
            finishWork = true;
            room.setRoomOwner(editField.getText().trim());
            room.setCoOwner(editField2.getText().trim());
            Stage stage = (Stage) okBtn.getScene().getWindow();
            stage.close();
        }else{
            errorLabel.setText("Please input room owner's name!");
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
