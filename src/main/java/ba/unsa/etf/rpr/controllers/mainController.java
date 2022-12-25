package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.mn.Media;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class mainController {

    public Button myLibraryButton;
    public Button addFundsButton;
    public ListView<Media> mediaList;
    public Button usernameButton;
    private MediaManager mediaManager = new MediaManager();
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

}
