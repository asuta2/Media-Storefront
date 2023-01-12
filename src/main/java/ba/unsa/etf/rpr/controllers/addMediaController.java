package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.business.TypesManager;
import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Types;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addMediaController {
    public TextField mediaNameField;
    public TextField mediaCreatorField;
    public ChoiceBox mediaTypeBox;
    public Slider salesSlider;
    public TextField priceField;
    public TextArea descriptionBox;
    public Button addMediaButton;
    public Label slideValue;
    private List<Types> types = new ArrayList<>();
    private final MediaManager mediaManager = new MediaManager();
    private final TypesManager typesManager = new TypesManager();

    @FXML
    public void initialize(){
        types = typesManager.getAll();
        Map<Integer,String> map = new HashMap<>();
        for(Types type : types){
            map.put(type.getIdTypes(),type.getTypeName());
        }
        mediaTypeBox.getItems().addAll(map.values());

        priceField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("/^(0|[1-9]\\d*)(\\.\\d+)?$/")) {
                priceField.setText(newText.replaceAll("[^/^(0|[1-9]\\d*)(\\.\\d+)?$/]", ""));
            }
        });
        salesSlider.valueProperty().addListener((obs, oldText, newText) -> {
            slideValue.setText("Sales Percentage: " + newText.intValue() + "%");
            slideValue.setVisible(true);
        });
        descriptionBox.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 450) {
                descriptionBox.setText(newText.substring(0, 450));
            }
        });
    }

    public void addMediaToDatabase(ActionEvent actionEvent) {
        if(mediaNameField.getText().trim().isEmpty() || mediaCreatorField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || descriptionBox.getText().trim().isEmpty() || mediaTypeBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all fields!");
            alert.showAndWait();
        }else if(Double.parseDouble(priceField.getText()) < 0 || Double.parseDouble(priceField.getText()) > 120){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Price can't be negative!");
            alert.showAndWait();
        }else {
            //add media to database
            Media media = new Media();
            media.setMediaName(mediaNameField.getText());
            media.setMediaCreator(mediaCreatorField.getText());
            media.setTypeId(types.get(mediaTypeBox.getSelectionModel().getSelectedIndex()).getIdTypes());
            media.setSales_pct(salesSlider.getValue());
            media.setPrice(Double.parseDouble(priceField.getText()));
            media.setDescription(descriptionBox.getText());
            mediaManager.add(media);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Media added successfully!");
            alert.showAndWait();
            System.out.println("Media added successfully!");
            //reset all fields
            mediaNameField.setText("");
            mediaCreatorField.setText("");
            salesSlider.setValue(0);
            priceField.setText("");
            descriptionBox.setText("");
        }
    }
}
