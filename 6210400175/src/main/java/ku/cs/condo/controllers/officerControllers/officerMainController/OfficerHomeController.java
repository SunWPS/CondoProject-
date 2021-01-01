package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.services.etcService.ImageService;


public class OfficerHomeController extends OfficerParentController{

    private ImageService imageService;

    @FXML private ImageView imageProfile, logoImage;
    @FXML private Label usernameLabel;
    @FXML private Button showRoomBtn, itemOutBtn, showItemBtn, rePassBtn, logoutBtn, homeBtn;


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
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                }
            }
        });
    }

}
