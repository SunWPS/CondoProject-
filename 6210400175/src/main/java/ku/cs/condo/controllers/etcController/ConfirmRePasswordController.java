package ku.cs.condo.controllers.etcController;

import javafx.application.Platform;
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
import ku.cs.condo.services.accountDataSource.AccountsFileDataSource;

import java.io.IOException;


public class ConfirmRePasswordController {

    @FXML private Button cancelBtn, confirmBtn;

    private AccountsFileDataSource accountsData;
    private AccountList account;
    private String newPassword;
    private int check;
    private String theme;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Scene thisScene= confirmBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                }
            }
        });
    }

    @FXML public void handleCancelBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        if(check == 1) {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/adminPage/admin_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            AdminRePasswordController ar = loader.getController();
            ar.setAdmin(((AdminList)account));
            ar.setTheme(theme);
            stage.show();
        }
        else if(check == 2) {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/officerPage/officerMainPage/officer_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            OfficerRePasswordController of = loader.getController();
            of.setOfficer(((OfficerList)account));
            of.setTheme(theme);
            stage.show();
        }
        else if(check == 3){;
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/residentPage/resident_re_password.fxml")
            );
            stage.setScene(new Scene(loader.load(), 800, 600));
            ResidentRePasswordController re = loader.getController();
            re.setResident(((ResidentList)account));
            re.setTheme(theme);
            stage.show();
        }
    }

    @FXML public void handleConfirmBtnOnAction(ActionEvent event) throws IOException {
        account.applyRePassword(newPassword);
        accountsData.setData(account);
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/finish_re_password.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        FinishRePasswordController fn = loader.getController();
        fn.setTheme(theme);
        fn.setCheck(check);
        fn.setAccount(account);
        stage.show();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public void setAccountsData(AccountsFileDataSource accountsData) {
        this.accountsData = accountsData;
    }

    public void setAccount(AccountList account) {
        this.account = account;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
