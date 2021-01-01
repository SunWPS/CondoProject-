package ku.cs.condo.controllers.adminControllers;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.models.account.AdminList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddOfficerFinishController {
    @FXML private Button backBtn;
    @FXML private ImageView finishImage;

    private AdminList admin;
    private String theme;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Scene thisScene = backBtn.getScene();
                if (theme.equals("abstract")) {
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    finishImage.setImage(new Image("/IconAndLogo/abstract/absFinish.png"));
                } else if (theme.equals("classic")) {
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    finishImage.setImage(new Image("/IconAndLogo/classic/finish.png"));
                }
            }
        });
    }


    @FXML
    public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/admin_add_officer.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AdminAddOfficerController ao = loader.getController();
        ao.setAdmin(admin);
        ao.setTheme(theme);
        stage.show();
    }

    public void setAdmin(AdminList admin) {
        this.admin = admin;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
