package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Purchases;
import ba.unsa.etf.rpr.mn.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addPurchaseController {
    private List<Users> users;
    private List<Media> media;
    public Label userLabel;
    public ChoiceBox userChoiceBox;
    public ChoiceBox mediaChoiceBox;
    public Button commitButton;
    public Label mediaLabel;
    private Map<Integer, String> mediaMap;
    private final UsersManager usersManager= new UsersManager();
    private final MediaManager mediaManager = new MediaManager();
    private final PurchasesManager purchasesManager = new PurchasesManager();

    @FXML
    public void initialize(){
        users = usersManager.getAll();
        Map<Integer, String> usersMap = new HashMap<>();
        for(Users user : users){
            usersMap.put(user.getIdUsers(), user.getUsername());
        }
        userChoiceBox.getItems().addAll(usersMap.values());
        userChoiceBox.getSelectionModel().selectFirst();
        //Filter out media that the user has already purchased
        media = mediaManager.getAll();

        mediaMap = new HashMap<>();
        for(Media m : media){
            mediaMap.put(m.getIdMedia(), m.getMediaName());
        }
        mediaChoiceBox.getItems().addAll(mediaMap.values());
        mediaChoiceBox.getSelectionModel().selectFirst();
        //When the user changes, update the media choice box to show only media that the user has not purchased with streams
        userChoiceBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            updateMediaChoiceBox();
        });
    }

    public void commitChanges(ActionEvent actionEvent) {
        //commit changes to the database
        if(userChoiceBox.getValue() == null || mediaChoiceBox.getValue() == null){
            //show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select a user and a media");
            alert.showAndWait();
        }
        else{
            List<Purchases> purchasesOfCurrentUser = purchasesManager.getAllPurchasesById(users.get(userChoiceBox.getSelectionModel().getSelectedIndex()).getIdUsers());
            //check if the user has already purchased the media he wants to purchase
            for(Purchases p : purchasesOfCurrentUser){
                if(p.getMediaId() == media.get(mediaChoiceBox.getSelectionModel().getSelectedIndex()).getIdMedia()){
                    //show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("The user has already purchased this media");
                    alert.showAndWait();
                    return;
                }
            }
            //commit changes to the database
            Purchases temp = new Purchases();
            temp.setUserId(users.get(userChoiceBox.getSelectionModel().getSelectedIndex()).getIdUsers());
            temp.setMediaId(media.get(mediaChoiceBox.getSelectionModel().getSelectedIndex()).getIdMedia());
            temp.setBoughtDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
            purchasesManager.add(temp);
            //update the media choice box
            updateMediaChoiceBox();
            //show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Purchase added successfully");
            alert.showAndWait();
        }
    }
    public void updateMediaChoiceBox(){
        mediaChoiceBox.getItems().clear();
        media = mediaManager.getAll();
        media.removeIf(m -> purchasesManager.getAllPurchasesById(users.get(userChoiceBox.getSelectionModel().getSelectedIndex()).getIdUsers()).stream().anyMatch(p -> p.getMediaId() == m.getIdMedia()));
        mediaMap.clear();
        for(Media m : media){
            mediaMap.put(m.getIdMedia(), m.getMediaName());
        }
        mediaChoiceBox.getItems().addAll(mediaMap.values());
        mediaChoiceBox.getSelectionModel().selectFirst();
    }
}
