package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.PurchasesManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class libraryController {
    private final PurchasesManager purchasesManager = new PurchasesManager();
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
            listLibrary.setItems(FXCollections.observableList(purchasesManager.getAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
