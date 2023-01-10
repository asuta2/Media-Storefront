package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Purchases;
import ba.unsa.etf.rpr.mn.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class adminController {
    public Button showUsers;
    public Button showPurchases;
    public Button showMedia;
    private final UsersManager usersManager = new UsersManager();
    private final PurchasesManager purchasesManager = new PurchasesManager();
    private final MediaManager mediaManager = new MediaManager();
    public BorderPane mainWindow;
    public Button addButton;
    public Button deleteButton;

    @FXML
    public void initialize() {

    }

    public void showAllUsers(ActionEvent actionEvent) {
        ListView<Users> usersList=new ListView<>(FXCollections.observableList(usersManager.getAll()));
        mainWindow.centerProperty().setValue(usersList);
        //When clicked, show all users in the list

    }

    public void showAllPurchases(ActionEvent actionEvent) {
        //When clicked, show all purchases in the list
        ListView<Purchases> purchasesList=new ListView<>(FXCollections.observableList(purchasesManager.getAll()));
        mainWindow.centerProperty().setValue(purchasesList);
        purchasesList.setCellFactory(new Callback<ListView<Purchases>, ListCell<Purchases>>() {
            @Override
            public ListCell<Purchases> call(ListView<Purchases> purchasesListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Purchases purchases, boolean b) {
                        super.updateItem(purchases, b);
                        if (purchases != null) {
                            HBox hBox = new HBox();
                            hBox.setSpacing(10);
                            hBox.getChildren().addAll(new Label(usersManager.getUserById(purchases.getUserId()).getUsername()),new Label(mediaManager.getMediaById(purchases.getMediaId()).getMediaName()), new Label(purchases.getBoughtDate().toString()));
                            setGraphic(hBox);
                        } else setGraphic(null);
                    }
                };
            }
        });
    }

    public void showAllMedia(ActionEvent actionEvent) {
        //When clicked, show all media in the list
        ListView<Media> mediaList=new ListView<>(FXCollections.observableList(mediaManager.getAll()));
        mainWindow.centerProperty().setValue(mediaList);
        //Delete previous CellFactory of other type
        mediaList.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>() {
            @Override
            public ListCell<Media> call(ListView<Media> mediaListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Media media, boolean b) {
                        super.updateItem(media, b);
                        if (media != null) {
                            HBox hBox = new HBox();
                            hBox.setSpacing(10);
                            hBox.getChildren().addAll(new Label(media.getMediaName()), new Label(media.getDescription()), new Label(media.getMediaCreator()), new Label(media.getPrice().toString()));
                            setGraphic(hBox);
                        } else setGraphic(null);
                    }
                };
            }
        });
    }


    public void addToDatabase(ActionEvent actionEvent) {
    }

    public void deleteFromDatabase(ActionEvent actionEvent) {
    }
}
