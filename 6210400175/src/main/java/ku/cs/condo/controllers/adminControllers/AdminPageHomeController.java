package ku.cs.condo.controllers.adminControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminPageHomeController extends AdminParentController{
    @FXML private Label usernameLabel;
    @FXML private Button homeBtn, addOfficerBtn, showOfficerBtn, rePasswordBtn, logOutBtn;
    @FXML private ImageView logoImage;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usernameLabel.setText(getAdmin().getCurrentAccount().getUsername());

                Scene thisScene= homeBtn.getScene();
                if(getTheme().equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                }
            }
        });
    }


}
