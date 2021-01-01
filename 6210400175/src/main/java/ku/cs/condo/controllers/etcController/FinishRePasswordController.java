package ku.cs.condo.controllers.etcController;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.condo.controllers.adminControllers.AdminRePasswordController;
import ku.cs.condo.controllers.officerControllers.officerMainController.OfficerRePasswordController;
import ku.cs.condo.controllers.residentControllers.ResidentRePasswordController;
import ku.cs.condo.models.account.AdminList;
import ku.cs.condo.models.account.OfficerList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.models.account.AccountList;
import ku.cs.condo.services.accountDataSource.AdminFileDataSource;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;
import ku.cs.condo.services.accountDataSource.ResidentFileDataSource;

import java.io.IOException;

public class FinishRePasswordController {
    @FXML private Button logoutBtn, backBtn;
    @FXML private ImageView finishImage;

    private AccountList account;
    private String theme;
    private int check;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Scene thisScene= backBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    finishImage.setImage(new Image("/IconAndLogo/abstract/absFinish.png"));
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    finishImage.setImage(new Image("/IconAndLogo/classic/finish.png"));
                }
            }
        });
    }

    @FXML public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ac = loader.getController();

        if(check == 1) {
            AdminFileDataSource adminData = new AdminFileDataSource("data", "admin.csv");
            AdminList admins = adminData.getAdminsData();
            ac.setAccount(admins);
            ac.setTheme(theme);
            ac.setAccountType("Admin");
        }else if(check == 2){
            OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
            OfficerList officers = officerData.getOfficersData();
            ac.setAccount(officers);
            ac.setAccountType("Officer");
            ac.setTheme(theme);
        }else if(check == 3){
            ResidentFileDataSource residentData = new ResidentFileDataSource("data", "resident.csv");
            ResidentList residents = residentData.getResidentsData();
            ac.setAccount(residents);
            ac.setAccountType("Resident");
            ac.setTheme(theme);
        }
        stage.show();

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        if(check == 1){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/adminPage/admin_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            AdminRePasswordController ar = loader.getController();
            ar.setAdmin(((AdminList)account));
            ar.setTheme(theme);
        }
        else if(check == 2){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerMainPage/officer_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            OfficerRePasswordController of = loader.getController();
            of.setOfficer(((OfficerList)account));
            of.setTheme(theme);
        }
        else if(check == 3){
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/residentPage/resident_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            ResidentRePasswordController re = loader.getController();
            re.setResident(((ResidentList)account));
            re.setTheme(theme);
        }
        stage.show();
    }

    public void setAccount(AccountList account) {
        this.account = account;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setCheck(int check) {
        this.check = check;
    }

}
