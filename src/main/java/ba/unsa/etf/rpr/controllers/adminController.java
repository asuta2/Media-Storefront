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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class adminController {
    /**
     * adminController class is used to control the admin.fxml file. It is used to control the admin panel which has access to control and manipulate the values of users, purchases and or media.
     */
    public Button showUsers;
    public Button showPurchases;
    public Button showMedia;
    private final UsersManager usersManager = new UsersManager();
    private final PurchasesManager purchasesManager = new PurchasesManager();
    private final MediaManager mediaManager = new MediaManager();
    public BorderPane mainWindow;
    public Button addButton;
    public Button deleteButton;
    public Button editButton;

    @FXML
    public void initialize() {
       showAllUsers(null);
    }

    public void showAllUsers(ActionEvent actionEvent) {
        editButton.setVisible(false);
        ListView<Users> usersList=new ListView<>(FXCollections.observableList(usersManager.getAll()));
        mainWindow.centerProperty().setValue(usersList);
        //When clicked, show all users in the list

    }

    public void showAllPurchases(ActionEvent actionEvent) {
        //When clicked, show all purchases in the list
        editButton.setVisible(false);
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
        editButton.setVisible(true);
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
                            hBox.getChildren().addAll(new Label(media.toString()));
                            setGraphic(hBox);
                        } else setGraphic(null);
                    }
                };
            }
        });
    }


    public void addToDatabase(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.setResizable(false);
        if(mainWindow.centerProperty().getValue() instanceof ListView){
            if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Users){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addUser.fxml"));
                    Parent root = loader.load();
                    stage.setTitle("Add user");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Purchases){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addPurchase.fxml"));
                    Parent root = loader.load();
                    stage.setTitle("Add purchase");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Media){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addMedia.fxml"));
                    Parent root = loader.load();
                    stage.setTitle("Add media");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        stage.setOnCloseRequest(windowEvent -> {
            if(mainWindow.centerProperty().getValue() instanceof ListView){
                if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Users){
                    showAllUsers(null);
                }
                else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Purchases){
                    showAllPurchases(null);
                }
                else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Media){
                    showAllMedia(null);
                }
            }
        });
    }

    public void deleteFromDatabase(ActionEvent actionEvent) {
        //When clicked, get the selected item and delete it from database
        //Check which list is currently displayed
        //If when button clicked nothing is selected, throw error

        if(mainWindow.centerProperty().getValue() instanceof ListView){
            //Check if nothing is selected
            if(((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No item selected");
                alert.setContentText("Please select an item to delete");
                alert.showAndWait();
                return;
            }
            if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Users){
                Users user=(Users) ((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem();
                //Display confirmation alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete user");
                alert.setContentText("Are you sure you want to delete user " + user.getUsername() + "?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    usersManager.delete(user.getIdUsers());
                    ((ListView) mainWindow.centerProperty().getValue()).getItems().remove(user);
                }
            }
            else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Purchases){
                Purchases purchase=(Purchases) ((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem();
                //Display confirmation alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete purchase");
                alert.setContentText("Are you sure you want to delete purchase " + purchase.getPurchasesId() + "?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    purchasesManager.delete(purchase.getPurchasesId());
                    ((ListView) mainWindow.centerProperty().getValue()).getItems().remove(purchase);
                }
            }
            else if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Media){
                Media media=(Media) ((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem();
                //Display confirmation alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete media");
                alert.setContentText("Are you sure you want to delete media " + media.getMediaName() + "?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    mediaManager.delete(media.getIdMedia());
                    ((ListView) mainWindow.centerProperty().getValue()).getItems().remove(media);
                }
            }
        }


    }

    public void editButtonClicked(ActionEvent actionEvent) {
        if(mainWindow.centerProperty().getValue() instanceof ListView){
            //Check if nothing is selected
            if(((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No item selected");
                alert.setContentText("Please select an item to edit");
                alert.showAndWait();
                return;
            }
        Stage stage = new Stage();
        stage.setResizable(false);
        if(mainWindow.centerProperty().getValue() instanceof ListView){
             if(((ListView) mainWindow.centerProperty().getValue()).getItems().get(0) instanceof Media){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editMedia.fxml"));
                    Parent root = loader.load();
                    editMediaController controller = loader.getController();
                    controller.setMedia((Media) ((ListView) mainWindow.centerProperty().getValue()).getSelectionModel().getSelectedItem());
                    stage.setTitle("Edit media");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        stage.setOnCloseRequest(windowEvent -> {
            showAllMedia(null);
        });
    }
}}
