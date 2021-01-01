package ku.cs.condo.controllers.adminControllers;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.condo.exception.IllegalArgumentForRegisterException;
import ku.cs.condo.models.account.OfficerList;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AdminAddOfficerController extends AdminParentController{

    private OfficerFileDataSource officerData;
    private OfficerList officer;
    private File image;

    @FXML private Button homeBtn, adminHomeBtn, rePasswordBtn, showOfficerBtn,logOutBtn, browseBtn, addBtn;
    @FXML private Label usernameLabel, imagePathLabel, errorUsername, errorName, errorAll;
    @FXML private TextField usernameField, nameField;
    @FXML private PasswordField passwordField;
    @FXML private ImageView profileImageView, logoImage, addImage;

    @FXML public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                officerData = new OfficerFileDataSource("data", "officer.csv");
                officer = officerData.getOfficersData();

                usernameLabel.setText(getAdmin().getCurrentAccount().getUsername());

                Scene thisScene= homeBtn.getScene();
                if(getTheme().equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                    addImage.setImage(new Image("/IconAndLogo/abstract/absAddOfficer.png"));
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                    addImage.setImage(new Image("/IconAndLogo/classic/AddAccount.png"));
                }
            }
        });
    }

    @FXML public void handleBrowseBtnOnAction(ActionEvent event){
        try {
            FileChooser fc = new FileChooser();
            File jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
            fc.setInitialDirectory(new File(jarDir.getParentFile().toString() + File.separator + "imageForTest"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fc.showOpenDialog(null);
            imagePathLabel.setText(selectedFile.getName());
            this.image = selectedFile;
            try {
                profileImageView.setImage(new Image(selectedFile.toURI().toString()));
            }catch (NullPointerException ignored){}
        }catch (NullPointerException | URISyntaxException ignored){}
    }

    @FXML public void handleAddBtnOnAction(ActionEvent event) throws IOException{
        clearErrorLabel();

        String usernameInput = usernameField.getText().trim(), passwordInput = passwordField.getText()
                ,nameInput = nameField.getText().trim(), imagePathInput = imagePathLabel.getText();

        if(!(usernameInput.isEmpty() || passwordInput.isEmpty() || nameInput.isEmpty() || imagePathInput.isEmpty())) {
            try {
                int check = officer.checkOfficerAccount(usernameInput, nameInput);
                if(check == 1){
                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/fxml/adminPage/confirm_add_officer.fxml")
                    );
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    ConfirmAddOfficerController con = loader.getController();
                    con.setAdmin(getAdmin());
                    con.setUsername(usernameInput);
                    con.setPassword(passwordInput);
                    con.setName(nameInput);
                    con.setImage(image);
                    con.setTheme(getTheme());
                    stage.show();
                }else{
                    errorUsername.setText("This username is already taken");
                    errorName.setText("This name is already exists");
                }
            }catch (IllegalArgumentForRegisterException e){
                switch (e.getMessage()){
                    case "This username is already taken":
                        errorUsername.setText("This username is already taken");
                        break;
                    case "This name is already exists":
                        errorName.setText("This name is already exists");
                        break;
                }
            }
        } else {
            errorAll.setText("Please input all required fields");
        }
    }

    public void clearErrorLabel(){
        errorUsername.setText(null);
        errorName.setText(null);
        errorAll.setText(null);
    }


}


