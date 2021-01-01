package ku.cs.condo.controllers.adminControllers;

import ku.cs.condo.models.account.AdminList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;

import java.io.File;
import java.io.IOException;

public class ConfirmAddOfficerController {

    @FXML private Button cancelBtn, confirmBtn;
    @FXML private Label usernameLabel, nameLabel;
    @FXML private ImageView imageView;

    private AdminList admin;
    private String username,password,name;
    private File image;
    private ImageService imageService;
    private String theme;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageService = new ImageService();
                usernameLabel.setText(username);
                nameLabel.setText(name);
                try {
                    imageView.setImage(new Image(image.toURI().toString()));
                }catch(NullPointerException ignored){}

                Scene thisScene= imageView.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }


    @FXML
    public void handleCancelBtnOnAction(ActionEvent event) throws IOException {
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

    @FXML public void handleConfirmBtnOnAction(ActionEvent event) throws IOException {
        OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
        String fileName = imageService.copyImage("officerPicture", image);

        officerData.appendOfficer(username, password, name, fileName);
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/add_officer_finish.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AddOfficerFinishController adf = loader.getController();
        adf.setAdmin(admin);
        adf.setTheme(theme);
        stage.show();

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setAdmin(AdminList admin) {
        this.admin = admin;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
