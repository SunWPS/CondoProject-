package ku.cs.condo.controllers.adminControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ku.cs.condo.controllers.fullInformationController.FullInformationAccountController;
import ku.cs.condo.models.account.OfficerAccount;
import ku.cs.condo.models.account.OfficerList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.condo.services.etcService.ImageService;
import ku.cs.condo.services.accountDataSource.OfficerFileDataSource;

import java.io.IOException;

public class AdminShowOfficerController extends AdminParentController{

    private OfficerFileDataSource officerData;
    private OfficerList officers;
    private OfficerAccount selectedOfficer;
    private ImageService imageService;
    ObservableList<OfficerAccount> officerList;

    @FXML private Button homeBtn, adminHomeBtn, addOfficerBtn, rePasswordBtn,logOutBtn, menuBtn, menuBtn2, changeStatusBtn, fullInfoBtn;
    @FXML private Pane menuPane, workPane;
    @FXML private Label usernameLabel, officerNameLabel;
    @FXML private ImageView imageView, humanImage, logoImage, menuImage1, menuImage2;
    @FXML private VBox vBoxMenu;
    @FXML private TableView<OfficerAccount> officerTable;
    @FXML private TableColumn<OfficerAccount, String> usernameCol;
    @FXML private TableColumn<OfficerAccount, String> nameCol;
    @FXML private TableColumn<OfficerAccount, String> dateTimeCol;
    @FXML private TableColumn<OfficerAccount, String> statusCol;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usernameLabel.setText(getAdmin().getCurrentAccount().getUsername());

                Scene thisScene= homeBtn.getScene();
                if(getTheme().equals("abstract")){
                    thisScene.getStylesheets().add("/StyleSheet/abstract.css");
                    logoImage.setImage(new Image("/IconAndLogo/abstract/absLogo.png"));
                    humanImage.setImage(new Image("/IconAndLogo/abstract/absOfficerImage.png"));
                    menuImage1.setImage(new Image("/IconAndLogo/abstract/absMenuIcon.png"));
                    menuImage2.setImage(new Image("/IconAndLogo/abstract/absMenuIcon.png"));
                }
                else if(getTheme().equals("classic")){
                    thisScene.getStylesheets().add("/StyleSheet/classic.css");
                    logoImage.setImage(new Image("/IconAndLogo/classic/Logo.png"));
                    humanImage.setImage(new Image("/IconAndLogo/classic/officer2.png"));
                    menuImage1.setImage(new Image("/IconAndLogo/classic/56763.png"));
                    menuImage2.setImage(new Image("/IconAndLogo/classic/56763.png"));
                }
            }
        });
        imageService = new ImageService();
        changeStatusBtn.setDisable(true);
        fullInfoBtn.setDisable(true);
        officerData = new OfficerFileDataSource("data", "officer.csv");
        officers = officerData.getOfficersData();

        showData();

        officerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue != null){
                selectedOfficer(newValue);
            }
        });
    }

    private void showData(){
        officerList = FXCollections.observableList(officers.sortAccount());

        usernameCol.setCellValueFactory(new PropertyValueFactory<OfficerAccount,String>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<OfficerAccount,String>("name"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<OfficerAccount,String>("dateAndTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory<OfficerAccount,String>("status"));

        officerTable.setItems(officerList);
    }

    private void selectedOfficer(OfficerAccount officer){
        selectedOfficer = officer;
        officerNameLabel.setText(selectedOfficer.getName());
        imageView.setImage(new Image(imageService.getImage("officerPicture", officer.getImagePath())));
        changeStatusBtn.setDisable(false);
        fullInfoBtn.setDisable(false);
    }

    @FXML public void handleChangeStatusBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminPage/confirm_change_status.fxml"));
        stage.setScene(new Scene(loader.load(), 400, 300));
        stage.setTitle("Confirm");
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        ConfirmChangeStatusController confirm = loader.getController();
        confirm.setTheme(getTheme());
        stage.showAndWait();
        if(confirm.finish()){
            selectedOfficer.changeStatus();
            officerData.setData(officers);
            officerTable.refresh();
        }
    }

    @FXML public void handleFullInfoBtnOnAction(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/fullInformationPage/full_information_account.fxml")

        );
        stage.setScene(new Scene(loader.load(), 700, 400));
        stage.setTitle("Full Officer Information");
        stage.getIcons().add(new Image("/IconAndLogo/etcImage/icon.png"));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        FullInformationAccountController full = loader.getController();
        full.setOfficer(selectedOfficer);
        full.setTheme(getTheme());
        stage.show();
    }


    @FXML public void handleMenuBtnOnAction(ActionEvent event){
        menuPane.toFront();
        vBoxMenu.toFront();
    }

    @FXML public void handleMenuBtn2OnAction(ActionEvent event){
        workPane.toFront();
    }

}
