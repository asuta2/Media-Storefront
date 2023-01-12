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

public class editMediaController {
    public Button addMediaButton;
    public TextArea descriptionBox;
    public TextField priceField;
    public Label slideValue;
    public Slider salesSlider;
    public ChoiceBox mediaTypeBox;
    public TextField mediaCreatorField;
    public TextField mediaNameField;
    private List<Types> types = new ArrayList<>();
    private final MediaManager mediaManager = new MediaManager();
    private final TypesManager typesManager = new TypesManager();
    private  int CurrentMediaId ;
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
            if(newText==null) descriptionBox.setText("");
            else if (newText.length() > 450) {
                descriptionBox.setText(newText.substring(0, 450));
            }
        });
    }
    public void setMedia(Media media){
        mediaNameField.setText(media.getMediaName());
        mediaCreatorField.setText(media.getMediaCreator());
        salesSlider.setValue(media.getSales_pct());
        mediaTypeBox.setValue(types.get(media.getTypeId()-1).getTypeName());
        priceField.setText(String.valueOf(media.getPrice()));
        descriptionBox.setText(media.getDescription());
        CurrentMediaId = media.getIdMedia();

    }
    public void addMediaToDatabase(ActionEvent actionEvent) {
        if(mediaNameField.getText().isEmpty() || mediaCreatorField.getText().isEmpty() || priceField.getText().isEmpty() || descriptionBox.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
            return;
        }
        Media media = new Media();
        media.setIdMedia(CurrentMediaId);
        media.setMediaName(mediaNameField.getText());
        media.setMediaCreator(mediaCreatorField.getText());
        media.setSales_pct(salesSlider.getValue());
        media.setTypeId(types.get(mediaTypeBox.getSelectionModel().getSelectedIndex()).getIdTypes());
        media.setPrice(Double.parseDouble(priceField.getText()));
        media.setDescription(descriptionBox.getText());
        mediaManager.update(media);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Media edited successfully!");
        alert.showAndWait();
        //Reset fields
        mediaNameField.setText("");
        mediaCreatorField.setText("");
        salesSlider.setValue(0);
        mediaTypeBox.setValue(null);
        priceField.setText("");
        descriptionBox.setText("");
    }
}
