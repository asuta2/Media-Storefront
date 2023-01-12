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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class mainController {
    /**
     * This is the main controller for the application. It is responsible for the main window and all of its components.
     * It is also responsible for the communication between the other controllers and the main window.
     */

    public Button myLibraryButton;
    public Button addFundsButton;
    public ListView<Media> mediaList;
    public MenuButton usernameButton;
    private final MediaManager mediaManager = new MediaManager();
    private final PurchasesManager purchasesManager = new PurchasesManager();
    private final UsersManager usersManager = new UsersManager();
    private List<Purchases> allPurchasesOfCurrentUser = new ArrayList<>();

    public Button addButton;
    public Button shoppingButton;
    public Label costLabel;
    private ObservableList<Media> cart;
    public ChoiceBox orderByBox;
    public mainController(){
        cart = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        try{
            orderByBox.setItems(FXCollections.observableArrayList("On Sale", "Price: Low to High", "Price: High to Low", "Name: A to Z", "Name: Z to A"));
            refreshList();
            mediaList.getSelectionModel().selectedItemProperty().addListener((obs, oldMedia, newMedia) -> {
                if (newMedia != null) {
                    System.out.println(newMedia.getMediaName());
                }
            });
            orderByBox.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
                if(n!=null){
                    orderByChoice();
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshList() {
        try {
            //filter out media that the user already owns using streams
            allPurchasesOfCurrentUser = purchasesManager.getAllPurchasesById(UsersManager.getCurrentUser().getIdUsers());
            mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).toList()));
            mediaList.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Media> call(ListView<Media> mediaListView) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Media media, boolean b) {
                            super.updateItem(media, b);
                            if (media != null) {
                                HBox hBox = new HBox();
                                hBox.setSpacing(mediaList.getWidth() / 3);
                                hBox.getChildren().add(new Label(media.getMediaName()));
                                hBox.getChildren().add(new Label(Math.round((media.getPrice()-media.getPrice()*media.getSales_pct()/100.00)*100)/100.00 + "$"));
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
            nv.listLibrary.setItems(FXCollections.observableList(allPurchasesOfCurrentUser));
            nv.listLibrary.setCellFactory(new Callback<ListView<Purchases>, ListCell<Purchases>>() {
                @Override
                public ListCell<Purchases> call(ListView<Purchases> purchasesListView) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Purchases purchases, boolean b) {
                            super.updateItem(purchases, b);
                            if (purchases != null) {
                                HBox hBox = new HBox();
                                hBox.setSpacing(10);
                                hBox.getChildren().addAll(new Label(mediaManager.getMediaById(purchases.getMediaId()).getMediaName()), new Label(purchases.getBoughtDate().toString()));
                                setGraphic(hBox);
                            } else setGraphic(null);
                        }
                    };
                }
            });
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
                costLabel.setText("Current Cost: " + getTotalCost() + "$");
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

    private Double getTotalCost() {
        Double totalCost = 0.0;
        for(Media m : cart){
            totalCost+=Math.round((m.getPrice()-m.getPrice()*m.getSales_pct()/100.00)*100)/100.00;
        }
        return totalCost;
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
    public void addFunds(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/funds.fxml"));
            Parent root = loader.load();
            addFundsController nv = loader.getController();
            nv.welcome.setText("Welcome " + UsersManager.getCurrentUser().getUsername() + "! Your balance is " + UsersManager.getCurrentUser().getBalance() + "$");
            stage.setTitle("Add Funds");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void orderByChoice() {
        if(orderByBox.getValue()!=null){
            //sort by price low to high
            if(orderByBox.getValue().equals("Price: Low to High")){
                mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).sorted(Comparator.comparing(Media::getPrice)).toList()));
            }
            //sort by price high to low
            else if(orderByBox.getValue().equals("Price: High to Low")){
                mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).sorted(Comparator.comparing(Media::getPrice).reversed()).toList()));
            }
            //sort by name A to Z
            else if(orderByBox.getValue().equals("Name: A to Z")){
                mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).sorted(Comparator.comparing(Media::getMediaName)).toList()));
            }
            //sort by name Z to A
            else if(orderByBox.getValue().equals("Name: Z to A")){
                mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).sorted(Comparator.comparing(Media::getMediaName).reversed()).toList()));
            //sort by sales
            }else if(orderByBox.getValue().equals("On Sale")){
                mediaList.setItems(FXCollections.observableList(mediaManager.getAll().stream().filter(media -> allPurchasesOfCurrentUser.stream().noneMatch(purchases -> purchases.getMediaId() == media.getIdMedia())).filter(media -> media.getSales_pct() > 0).toList()));
            }
        }
    }

    public void checkoutPressed(ActionEvent actionEvent) {
        if(cart.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You have to add something to your cart first!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to checkout?");
        alert.showAndWait();

        if(alert.getResult().getText().equals("OK")){
            if(UsersManager.getCurrentUser().getBalance()>=getTotalCost()){
                UsersManager.getCurrentUser().setBalance(UsersManager.getCurrentUser().getBalance()-getTotalCost());
                for(Media m: cart){
                    Purchases p = new Purchases();
                    p.setMediaId(m.getIdMedia());
                    p.setUserId(UsersManager.getCurrentUser().getIdUsers());
                    p.setBoughtDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
                    purchasesManager.add(p);
                }
                usersManager.update(UsersManager.getCurrentUser());
                cart.clear();
                refreshList();
                costLabel.setText("Current Cost: " + getTotalCost() + "$");
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Success");
                alert1.setHeaderText("Success");
                alert1.setContentText("You have successfully checked out!");
                alert1.showAndWait();
            }else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error");
                alert1.setHeaderText("Error");
                alert1.setContentText("You don't have enough funds to checkout!");
                alert1.showAndWait();
            }
        }

    }

    public void editProfileOpen(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editProfile.fxml"));
            Parent root = loader.load();
            stage.setTitle("Edit Profile");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setOnCloseRequest(e->{
                e.consume();
                closeProgram(stage);
                usernameButton.setText(UsersManager.getCurrentUser().getUsername());
            });
            stage.setOnHidden(e->{
                e.consume();
                closeProgram(stage);
                usernameButton.setText(UsersManager.getCurrentUser().getUsername());
            });
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeProgram(Stage stage) {
        stage.close();
    }

    public void logOutButton(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            stage.setTitle("Login");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage mainWindow = (Stage) usernameButton.getScene().getWindow();
            mainWindow.close();
            System.out.println("Successfully logged out!");
            usersManager.clearCurrentUser();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
