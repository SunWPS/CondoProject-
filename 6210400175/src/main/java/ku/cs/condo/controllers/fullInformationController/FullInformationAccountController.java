package ku.cs.condo.controllers.fullInformationController;


import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.models.account.OfficerAccount;
import ku.cs.condo.services.etcService.ImageService;


public class FullInformationAccountController {

    private OfficerAccount officer;
    private ImageService imageService;
    private String theme;

    @FXML private Label ttlLabel, ttl2Label, nameLabel, dateTimeLabel, usernameLabel, statusLabel, tryToLoginLabel;
    @FXML private ImageView profileImage;

    @FXML public void initialize() {


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();
                usernameLabel.setText(officer.getUsername());
                nameLabel.setText(officer.getName());
                dateTimeLabel.setText(officer.getDateAndTime());
                statusLabel.setText(officer.getStatus());
                if(officer.getStatus().equals("activate")){
                    ttlLabel.setText("");
                    ttl2Label.setText("");
                }else{
                    tryToLoginLabel.setText(Integer.toString(officer.getTryToLogin()));
                }
                try {
                   profileImage.setImage(new Image(imageService.getImage("officerPicture", officer.getImagePath())));
                }
                catch(NullPointerException ignored) { }

                Scene thisScene= profileImage.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }

        });

    }

    public void setOfficer(OfficerAccount officer) {
        this.officer = officer;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
