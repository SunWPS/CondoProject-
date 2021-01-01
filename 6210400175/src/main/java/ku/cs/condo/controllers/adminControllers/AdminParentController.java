package ku.cs.condo.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.condo.controllers.etcController.HomeController;
import ku.cs.condo.controllers.etcController.LoginController;
import ku.cs.condo.models.account.AdminList;
import ku.cs.condo.services.accountDataSource.AdminFileDataSource;

import java.io.IOException;

public class AdminParentController {

    private AdminList admin;
    private String theme;

    @FXML public void handleAdminHomeBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/admin_page_home.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AdminPageHomeController ad = loader.getController();
        ad.setAdmin(admin);
        ad.setTheme(theme);
        stage.show();
    }

    @FXML public void handleAddOfficerBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/admin_add_officer.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AdminAddOfficerController ad = loader.getController();
        ad.setAdmin(admin);
        ad.setTheme(theme);
        stage.show();
    }

    @FXML public void handleShowOfficerBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/admin_show_officer.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AdminShowOfficerController ad = loader.getController();
        ad.setAdmin(admin);
        ad.setTheme(theme);
        stage.show();
    }

    @FXML public void handleRePasswordBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/adminPage/admin_re_password.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        AdminRePasswordController ad = loader.getController();
        ad.setAdmin(admin);
        ad.setTheme(theme);
        stage.show();
    }

    @FXML public void handleLogOutBtnOnAction(ActionEvent event) throws  IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ad = loader.getController();
        AdminFileDataSource adminData = new AdminFileDataSource("data", "admin.csv");
        AdminList admins = adminData.getAdminsData();
        ad.setAccount(admins);
        ad.setTheme(theme);
        ad.setAccountType("Admin");
        stage.show();
    }

    @FXML public void handleHomeBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/home.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        HomeController home = loader.getController();
        home.setTheme(theme);
        stage.show();
    }

    public void setAdmin(AdminList admin) {
        this.admin = admin;
    }

    public AdminList getAdmin() {
        return admin;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}
