package ku.cs.condo.controllers.officerControllers.officerSubController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmAddItemController {
    @FXML
    private Button confirmBtn;

    private boolean confirm;
    private String theme;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                confirm = false;

                Scene thisScene = confirmBtn.getScene();
                if(theme.equals("abstract")) {
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event) throws IOException {
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
