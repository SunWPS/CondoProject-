package ku.cs.condo.controllers.residentControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmRegisterContorller {

    @FXML private Button confirmBtn, finishBtn;
    @FXML private Label confirmLabel, finishLabel;

    private boolean confirm;
    private String theme;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                confirm = false;
                finishLabel.setVisible(false);
                finishBtn.setVisible(false);
                finishBtn.setDisable(true);

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
        finishLabel.setVisible(true);
        finishBtn.setVisible(true);
        finishBtn.setDisable(false);

        confirmLabel.setVisible(false);
        confirmBtn.setVisible(false);
        confirmBtn.setDisable(true);
    }

    @FXML public void handleFinishBtnOnAction(ActionEvent event){
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
