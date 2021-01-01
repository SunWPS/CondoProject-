package ku.cs.condo.controllers.etcController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ku.cs.condo.models.account.AdminList;
import ku.cs.condo.models.account.OfficerList;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.services.accountDataSource.AdminFileDataSource;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;
import ku.cs.condo.services.accountDataSource.ResidentFileDataSource;

import java.io.IOException;

public class HomeController {
    @FXML private Button adminBtn, officerBtn, residentBtn, informationBtn;
    @FXML private ImageView logoImage, infoImage;
    @FXML private Pane pane1, pane2;

    private String theme = "classic";

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showTheme();
            }
        });
    }

    @FXML public void handleInformationBtnOnAction(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/information_and_setting.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        InformationAndSettingController info = loader.getController();
        info.setTheme(theme);
        stage.show();
    }

    @FXML public void handleAdminBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ac = loader.getController();
        AdminFileDataSource adminData = new AdminFileDataSource("data", "admin.csv");
        AdminList admins = adminData.getAdminsData();
        ac.setTheme(theme);
        ac.setAccount(admins);
        ac.setAccountType("Admin");
        stage.show();
    }

    @FXML public void handleOfficerBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ac = loader.getController();
        OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
        OfficerList officers = officerData.getOfficersData();
        ac.setTheme(theme);
        ac.setAccount(officers);
        ac.setAccountType("Officer");
        stage.show();
    }

    @FXML public void handleResidentBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ac = loader.getController();
        ResidentFileDataSource residentData = new ResidentFileDataSource("data", "resident.csv");
        ResidentList residents = residentData.getResidentsData();
        ac.setTheme(theme);
        ac.setAccount(residents);
        ac.setAccountType("Resident");
        stage.show();
    }

    private void showTheme(){
        Scene thisScene= pane1.getScene();
        if(theme.equals("abstract")){
            thisScene.getStylesheets().add("/StyleSheet/abstract.css");
            logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
            infoImage.setImage(new Image("/IconAndLogo/abstract/absInfoBtn.png"));
            pane1.setVisible(false);
            pane2.setVisible(false);
        }
        else if(theme.equals("classic")){
            thisScene.getStylesheets().add("/StyleSheet/classic.css");
            logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
            infoImage.setImage(new Image("/IconAndLogo/classic/info.png"));
            pane1.setVisible(true);
            pane2.setVisible(true);
        }
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}












