package ku.cs.condo.controllers.adminControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.controllers.etcController.ConfirmRePasswordController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import ku.cs.condo.exception.BothPassIsSameException;
import ku.cs.condo.exception.BothPassNotMatchException;
import ku.cs.condo.exception.WrongCurrentPasswordException;
import ku.cs.condo.services.accountDataSource.AdminFileDataSource;

import java.io.IOException;

public class AdminRePasswordController extends AdminParentController{
    @FXML private Button homeBtn, adminHomeBtn, addOfficerBtn, showOfficerBtn, logOutBtn, okBtn;
    @FXML private Label usernameLabel, wrongCurrentPasswordLabel, wrongConNewPassLabel, wrongNewPasswordLabel;
    @FXML private PasswordField currentPasswordField, newPasswordField, confirmNewPasswordField;
    @FXML private ImageView logoImage, rePassImage;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usernameLabel.setText(getAdmin().getCurrentAccount().getUsername());

                Scene thisScene= homeBtn.getScene();
                if(getTheme().equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                    rePassImage.setImage(new Image("/IconAndLogo/abstract/absRePass.png"));
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                    rePassImage.setImage(new Image("/IconAndLogo/classic/rePassword.png"));
                }
            }
        });
    }

    @FXML public void handleOkBtnOnAction(ActionEvent event) throws IOException{
        String currentPass = currentPasswordField.getText().trim();
        String newPass = newPasswordField.getText().trim();
        String confirmNewPass = confirmNewPasswordField.getText().trim();

        if(!(currentPass.isEmpty() || newPass.isEmpty() || confirmNewPass.isEmpty())){
            try {
                int check = getAdmin().checkInputRePassword(currentPass, newPass, confirmNewPass);
                if (check == 1) {
                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/fxml/etcPage/confirm_re_password.fxml")
                    );
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    ConfirmRePasswordController con = loader.getController();
                    con.setAccount(getAdmin());
                    con.setCheck(1);
                    con.setAccountsData(new AdminFileDataSource("data", "admin.csv"));
                    con.setNewPassword(newPass);
                    con.setTheme(getTheme());
                    stage.show();
                }
                else{
                    clearAlertLabel();
                    wrongCurrentPasswordLabel.setText("Sorry, Your password is incorrect");
                    wrongConNewPassLabel.setText("Your input doesn't match new password");
                }
            } catch (BothPassIsSameException e) {
                clearAlertLabel();
                wrongNewPasswordLabel.setText(e.getMessage());
            } catch (BothPassNotMatchException e) {
                clearAlertLabel();
                wrongConNewPassLabel.setText(e.getMessage());
            } catch (WrongCurrentPasswordException e) {
                clearAlertLabel();
                wrongCurrentPasswordLabel.setText(e.getMessage());
            }
        }
        else{
            clearAlertLabel();
            wrongConNewPassLabel.setText("Please input all required fields!");
        }

    }

    private void clearAlertLabel(){
        wrongCurrentPasswordLabel.setText(null);
        wrongConNewPassLabel.setText(null);
        wrongNewPasswordLabel.setText(null);
    }


}
