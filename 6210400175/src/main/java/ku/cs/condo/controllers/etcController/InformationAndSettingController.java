package ku.cs.condo.controllers.etcController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InformationAndSettingController {

    @FXML private ImageView backImage;
    @FXML private Button backBtn, okBtn, howToBtn;
    @FXML private ComboBox<String> themesCombo;

    private String theme;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showTheme();

                themesCombo.setPromptText(theme);

                ObservableList<String> themes = FXCollections.observableArrayList("abstract", "classic");
                themesCombo.setItems(themes);
            }
        });
    }

    private void showTheme(){
        Scene thisScene= backBtn.getScene();
        if(theme.equals("abstract")){
            thisScene.getStylesheets().add("/StyleSheet/abstract.css");
            backImage.setImage(new Image("/IconAndLogo/abstract/absBack.png"));
        }
        else if(theme.equals("classic")){
            thisScene.getStylesheets().add("/StyleSheet/classic.css");
            backImage.setImage(new Image("/IconAndLogo/classic/back.png"));
        }
    }

    @FXML public void handleOkBtnOnAction(ActionEvent event){
        if(themesCombo.getSelectionModel().getSelectedItem() != null){
            theme = themesCombo.getSelectionModel().getSelectedItem();
            Scene thisScene= backBtn.getScene();
            thisScene.getStylesheets().clear();
            showTheme();
        }
    }

    @FXML public void handleHowToBtnOnAction(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://drive.google.com/file/d/1OQztSpFGfuevIZ6vXUp7hcOtFNuGLhGq/view?usp=sharing"));
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


    public void setTheme(String theme) {
        this.theme = theme;
    }
}
