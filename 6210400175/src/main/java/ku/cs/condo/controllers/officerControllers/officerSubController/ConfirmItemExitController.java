package ku.cs.condo.controllers.officerControllers.officerSubController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirmItemExitController {

    private boolean confirm;
    private String residentGet;
    private String theme;

    @FXML private Button confirmBtn;
    @FXML private TextField residentGetField;
    @FXML private Label alertLabel;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                confirm = false;
                residentGet = "";

                Scene thisScene = confirmBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event){
        if(!residentGetField.getText().trim().equals("")) {
            confirm = true;
            residentGet = residentGetField.getText().trim();
            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            stage.close();
        }else{
            alertLabel.setText("กรุณากรอกข้อมูลให้ครบ");
        }
    }

    public String getResidentGet() {
        return residentGet;
    }

    public boolean finish(){
        return confirm;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
