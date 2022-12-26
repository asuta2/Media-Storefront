package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Purchases;
import ba.unsa.etf.rpr.models.mainModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {

    public Button myLibraryButton;
    public Button addFundsButton;
    public ListView<Media> mediaList;
    public Button usernameButton;
    private final MediaManager mediaManager = new MediaManager();
    public Button addButton;
    public Button shoppingButton;
    private ObservableList<Media> cart;
    private mainModel model = new mainModel();
    public ChoiceBox orderByBox;
    public mainController(){
        cart = FXCollections.observableArrayList();
        model.fill();
    }

    @FXML
    public void initialize() {
        try{
            orderByBox.setItems(model.getOrders());
            refreshList();
            mediaList.getSelectionModel().selectedItemProperty().addListener((obs, oldMedia, newMedia) -> {
                if (newMedia != null) {
                    System.out.println(newMedia.getMediaName());
                }
            });
            orderByBox.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
                if(n!=null){
                    System.out.println(n);
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

    public void addToCart(ActionEvent actionEvent) {
        cart.add(mediaList.getSelectionModel().getSelectedItem());
    }
    public void checkCart(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart.fxml"));
        Parent root = loader.load();
        cartController nv = loader.getController();
        stage.setTitle("Your Shopping Cart");
        stage.setScene(new Scene(root));
        nv.itemView.setItems(cart);
        stage.show();
    }
}
