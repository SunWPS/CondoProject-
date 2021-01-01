package ku.cs.condo.controllers.adminControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmChangeStatusController {

    private boolean confirm;
    private String theme;

    @FXML private Button confirmBtn;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                confirm = false;

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
        confirm = true;
        Stage stage = (Stage) confirmBtn.getScene().getWindow();
        stage.close();
    }


    public boolean finish(){
        return confirm;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
