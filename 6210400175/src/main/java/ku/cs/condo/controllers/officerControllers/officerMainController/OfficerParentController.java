package ku.cs.condo.controllers.officerControllers.officerMainController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.condo.controllers.etcController.HomeController;
import ku.cs.condo.controllers.etcController.LoginController;
import ku.cs.condo.models.account.OfficerList;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;

import java.io.IOException;

public class OfficerParentController {

    private OfficerList officer;
    private String theme;

    @FXML public void handleOfficerHomeBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_home.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerHomeController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    @FXML public void handleShowRoomBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_show_room.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerShowRoomController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    @FXML public void handleShowItemBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_show_item.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerShowItemController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    @FXML public void handleRePassBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_re_password.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerRePasswordController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    @FXML public void handleItemOutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/officerPage/officerMainPage/officer_show_item_out.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        OfficerShowItemOutController of = loader.getController();
        of.setTheme(theme);
        of.setOfficer(officer);
        stage.show();
    }

    @FXML public void handleLogOutBtnOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/etcPage/login.fxml")
        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController of  = loader.getController();
        OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
        OfficerList officers = officerData.getOfficersData();
        of.setTheme(theme);
        of.setAccount(officers);
        of.setAccountType("Officer");
        stage.show();
    }

    @FXML public void handleHomeBtnOnAction(ActionEvent event) throws IOException  {
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

    public void setOfficer(OfficerList officer) {
        this.officer = officer;
    }

    public OfficerList getOfficer() {
        return officer;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }
}
