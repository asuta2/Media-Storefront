package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Purchases;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class mainController {

    public Button myLibraryButton;
    public Button addFundsButton;
    public ListView<Media> mediaList;
    public Button usernameButton;
    private final MediaManager mediaManager = new MediaManager();
    @FXML
    public void initialize() {
        try{
            refreshList();
            mediaList.getSelectionModel().selectedItemProperty().addListener((obs, oldMedia, newMedia) -> {
                if (newMedia != null) {
                    System.out.println(newMedia.getMediaName());
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshList() {
        try {
            mediaList.setItems(FXCollections.observableList(mediaManager.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void libraryButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/library.fxml"));
            Parent root = loader.load();
            libraryController nv = loader.getController();
            nv.welcomeLabel.setText("Welcome to your Library, " + usernameButton.getText());
            stage.setTitle("Library");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
