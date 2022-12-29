package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Purchases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class mainController {

    public Button myLibraryButton;
    public Button addFundsButton;
    public ListView<Media> mediaList;
    public Button usernameButton;
    private final MediaManager mediaManager = new MediaManager();
    private final PurchasesManager purchasesManager = new PurchasesManager();
    private final UsersManager usersManager = new UsersManager();

    public Button addButton;
    public Button shoppingButton;
    private ObservableList<Media> cart;
    public ChoiceBox orderByBox;
    public mainController(){
        cart = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        try{
            orderByBox.setItems(FXCollections.observableArrayList("Name", "Price", "Date"));
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
            mediaList.setCellFactory(new Callback<ListView<Media>, ListCell<Media>>() {
                @Override
                public ListCell<Media> call(ListView<Media> mediaListView) {
                    return new ListCell<Media>() {
                        @Override
                        protected void updateItem(Media media, boolean b) {
                            super.updateItem(media, b);
                            if (media != null) {
                                HBox hBox = new HBox();
                                hBox.setSpacing(mediaList.getWidth() / 3);
                                hBox.getChildren().add(new Label(media.getMediaName()));
                                hBox.getChildren().add(new Label(String.valueOf(media.getPrice())));
                                hBox.getChildren().add(new Label(media.getDescription()));
                                setGraphic(hBox);
                            } else {
                                setText("");
                                setGraphic(null);
                            }
                        }
                    };
                }
            });

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
            nv.welcomeLabel.setText("Welcome to your Library, " + UsersManager.getCurrentUser().getUsername());
            stage.setTitle("Library");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToCart(ActionEvent actionEvent) {
        if(mediaList.getSelectionModel().getSelectedItem()!=null ){
        if(!cart.contains(mediaList.getSelectionModel().getSelectedItem())){
                cart.add(mediaList.getSelectionModel().getSelectedItem());
                System.out.println("Added to cart");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You have already added this media to your cart!");
            alert.showAndWait();
        }}else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You have to select a media first!");
            alert.showAndWait();
        }
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

    public void checkoutPressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to checkout?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            for(Media m: cart){
                Purchases p = new Purchases();
                p.setMediaId(m.getIdMedia());
                p.setUserId(UsersManager.getCurrentUser().getIdUsers());
                p.setBoughtDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
                purchasesManager.add(p);
            }
            System.out.println("Checkout successful!");
            cart.clear();
        }

    }
}
