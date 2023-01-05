package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.PurchasesManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Purchases;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class libraryController {
    private final PurchasesManager purchasesManager = new PurchasesManager();
    private final MediaManager mediaManager = new MediaManager();
    public ListView listLibrary;
    public Label welcomeLabel;

    @FXML
    public void initialize() {
        try{
            refreshList();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshList() {
        try {
            listLibrary.setItems(FXCollections.observableList(purchasesManager.getAllPurchasesById(UsersManager.getCurrentUser().getIdUsers())));
            listLibrary.setCellFactory(new Callback<ListView<Purchases>, ListCell<Purchases>>() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
