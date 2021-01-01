package ku.cs.condo.controllers.etcController;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ku.cs.condo.controllers.adminControllers.AdminPageHomeController;
import ku.cs.condo.controllers.officerControllers.officerMainController.OfficerHomeController;
import ku.cs.condo.controllers.residentControllers.ResidentRegisterController;
import ku.cs.condo.controllers.residentControllers.ResidentShowItemController;
import ku.cs.condo.exception.DeactivatedAccountException;
import ku.cs.condo.exception.NoAccountException;
import ku.cs.condo.exception.WrongCurrentPasswordException;
import ku.cs.condo.exception.WrongUsernameOrPasswordException;
import ku.cs.condo.models.account.AccountList;
import ku.cs.condo.models.account.AdminList;
import ku.cs.condo.models.account.OfficerList;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController {
    @FXML private Button backBtn, homeBtn, loginBtn, registerBtn;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label incorrectLabel, topicLabel;
    @FXML private Pane pane1, pane2;
    @FXML private ImageView backImage, logoImage;

    private String theme, accountType;
    private AccountList account;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (accountType) {
                    case "Admin":
                        topicLabel.setText("Admin Login");
                        if(theme.equals("classic")) {
                            pane1.setStyle("-fx-background-color: #9C2344; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color: #9C2344; -fx-background-radius: 300 300 300 300;");
                        }
                        else if(theme.equals("abstract")){
                            pane1.setStyle("-fx-background-color: #EF5859; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color: #EF5859; -fx-background-radius: 300 300 300 300;");
                        }
                        registerBtn.setDisable(true);
                        registerBtn.setVisible(false);
                        break;
                    case "Officer":
                        topicLabel.setText("Officer Login");
                        if(theme.equals("classic")) {
                            pane1.setStyle("-fx-background-color:  #18626C; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color:  #18626C; -fx-background-radius: 300 300 300 300;");
                        }
                        else if(theme.equals("abstract")){
                            pane1.setStyle("-fx-background-color: #1ED760; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color: #1ED760; -fx-background-radius: 300 300 300 300;");
                        }
                        registerBtn.setDisable(true);
                        registerBtn.setVisible(false);
                        break;
                    case "Resident":
                        topicLabel.setText("Resident Login");
                        if(theme.equals("classic")) {
                            pane1.setStyle("-fx-background-color: #64197E; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color: #64197E; -fx-background-radius: 300 300 300 300;");
                        }
                        else if(theme.equals("abstract")){
                            pane1.setStyle("-fx-background-color: #F37235; -fx-background-radius: 200 200 200 200;");
                            pane2.setStyle("-fx-background-color: #F37235; -fx-background-radius: 300 300 300 300;");
                        }
                        break;
                }

                Scene thisScene= backBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    backImage.setImage(new Image("/IconAndLogo/abstract/absBack.png"));
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    topicLabel.setFont(Font.font("Bell MT", 46));
                    backImage.setImage(new Image("/IconAndLogo/classic/back.png"));
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                }
            }
        });
    }

    @FXML public void handleRegisterBtnOnAction(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/residentPage/resident_register.fxml")

        );
        stage.setScene(new Scene(loader.load(), 800, 600));
        ResidentRegisterController register = loader.getController();
        register.setTheme(theme);
        stage.show();
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException {
        if(!(usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty())) {
            try{
                int check = account.loginAccount(usernameField.getText().trim(), passwordField.getText().trim());
                if (check == 1) {
                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    switch (accountType) {
                        case "Admin": {
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/fxml/adminPage/admin_page_home.fxml")
                            );
                            stage.setScene(new Scene(loader.load(), 800, 600));
                            AdminPageHomeController ad = loader.getController();
                            ad.setAdmin((((AdminList) account)));
                            ad.setTheme(theme);
                            stage.show();
                            break;
                        }
                        case "Officer": {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = new Date();
                            ((OfficerList) account).getCurrentAccount().setDateAndTime(dateFormat.format(date));
                            ((OfficerList) account).updateOfficerAccount(((OfficerList) account).getCurrentAccount());
                            OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
                            officerData.setData(account);
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/fxml/officerPage/officerMainPage/officer_home.fxml")
                            );
                            stage.setScene(new Scene(loader.load(), 800, 600));
                            OfficerHomeController of = loader.getController();
                            of.setOfficer(((OfficerList) account));
                            of.setTheme(theme);
                            stage.show();
                            break;
                        }
                        case "Resident":
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/fxml/residentPage/resident_show_item.fxml")
                            );
                            stage.setScene(new Scene(loader.load(), 800, 600));
                            ResidentShowItemController re = loader.getController();
                            ResidentList resident = (ResidentList) account;
                            re.setResident(resident);
                            re.setTheme(theme);
                            stage.show();
                            break;
                    }
                }
            }catch (DeactivatedAccountException e) {
                OfficerFileDataSource officerData = new OfficerFileDataSource("data", "officer.csv");
                officerData.setData(account);
                incorrectLabel.setText(e.getMessage());
            }catch (WrongUsernameOrPasswordException | WrongCurrentPasswordException | NoAccountException e){
                incorrectLabel.setText(e.getMessage());
            }

        }else{
            incorrectLabel.setText("Please input all required fields");
        }

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
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

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccount(AccountList account) {
        this.account = account;
    }
}
