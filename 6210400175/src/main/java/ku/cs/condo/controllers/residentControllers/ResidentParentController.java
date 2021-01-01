package ku.cs.condo.controllers.residentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.condo.controllers.etcController.HomeController;
import ku.cs.condo.controllers.etcController.LoginController;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.services.accountDataSource.ResidentFileDataSource;

import java.io.IOException;

public class ResidentParentController {

    private ResidentList resident;
    private String theme;

    @FXML public void handleRePassBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/residentPage/resident_re_password.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ResidentRePasswordController res = loader.getController();
        res.setTheme(theme);
        res.setResident(resident);
        stage.show();
    }

    @FXML public void handleShowItemBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/residentPage/resident_show_item.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ResidentShowItemController res = loader.getController();
        res.setResident(resident);
        res.setTheme(theme);
        stage.show();
    }

    @FXML public void handleShowItemOutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/residentPage/resident_show_item_out.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ResidentShowItemOutController res = loader.getController();
        res.setResident(resident);
        res.setTheme(theme);
        stage.show();
    }

    @FXML public void handleLogoutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController ac = loader.getController();
        ResidentFileDataSource residentData = new ResidentFileDataSource("data", "resident.csv");
        ResidentList residents = residentData.getResidentsData();
        ac.setAccount(residents);
        ac.setTheme(theme);
        ac.setAccountType("Resident");
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

    public ResidentList getResident() {
        return resident;
    }

    public void setResident(ResidentList resident) {
        this.resident = resident;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}
