package ku.cs.condo.controllers.residentControllers;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.etcController.LoginController;
import ku.cs.condo.models.room.Building;
import ku.cs.condo.models.account.ResidentList;
import ku.cs.condo.services.etcService.BuildingFileDataSource;
import ku.cs.condo.services.accountDataSource.ResidentFileDataSource;

import java.io.IOException;

public class ResidentRegisterController {

    private ResidentFileDataSource residentsData;
    private ResidentList residentsList;
    private BuildingFileDataSource buildingsData;
    private Building building;
    private String theme;


    @FXML private TextField usernameField, nameField, roomNumField;
    @FXML private PasswordField passwordField;
    @FXML private Button backBtn, okBtn;
    @FXML private Label errorLabel;
    @FXML private ImageView backImage, logoImage;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                residentsData = new ResidentFileDataSource("data", "resident.csv");
                residentsList = residentsData.getResidentsData();
                buildingsData = new BuildingFileDataSource("data", "condo.csv");
                building = buildingsData.getBuildingData();

                Scene thisScene= backBtn.getScene();
                if(theme.equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    backImage.setImage(new Image("/IconAndLogo/abstract/absBack.png"));
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                }
                else if(theme.equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    backImage.setImage(new Image("/IconAndLogo/classic/back.png"));
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                }
            }
        });
    }


    @FXML public void handleOkBtnOnAction(ActionEvent event) throws IOException {

        if(!nameField.getText().trim().isEmpty() || !roomNumField.getText().trim().isEmpty()) {
            if (building.findResident(nameField.getText().trim(), roomNumField.getText().trim())) {

                if(residentsList.checkSameName(nameField.getText().trim())){
                    errorLabel.setText("This name is already registered !");
                }
                else if(residentsList.checkSameUsername(usernameField.getText().trim())){
                    errorLabel.setText("This username is already in use !");
                }
                else {
                    errorLabel.setText("");

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/residentPage/confirm_register.fxml"));
                    stage.setScene(new Scene(loader.load(), 400, 300));
                    stage.setTitle("Confirm");
                    stage.centerOnScreen();
                    stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    ConfirmRegisterContorller confirm = loader.getController();
                    confirm.setTheme(theme);
                    stage.showAndWait();
                    if (confirm.finish()) {
                        residentsData.appendResident(usernameField.getText().trim(), passwordField.getText().trim(), nameField.getText().trim(), roomNumField.getText().trim());

                        Button b = (Button) event.getSource();
                        Stage stageBack = (Stage) b.getScene().getWindow();
                        FXMLLoader loaderBack = new FXMLLoader(
                                getClass().getResource("/fxml/etcPage/login.fxml")

                        );
                        stageBack.setScene(new Scene(loaderBack.load(), 800, 600));
                        LoginController ac = loaderBack.getController();
                        ResidentFileDataSource residentData = new ResidentFileDataSource("data", "resident.csv");
                        ResidentList residents = residentData.getResidentsData();
                        ac.setAccount(residents);
                        ac.setTheme(theme);
                        ac.setAccountType("Resident");
                        stageBack.show();
                    }
                }

            } else {
                errorLabel.setText("We can't find your name or room number in the data!");
            }
        } else {
            errorLabel.setText("Please input all required fields!");
        }
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException {
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

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
