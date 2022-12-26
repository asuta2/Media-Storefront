package ba.unsa.etf.rpr.models;

import ba.unsa.etf.rpr.mn.Media;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mainModel {
    private ObservableList<Media> mediji = FXCollections.observableArrayList();
    private ObservableList<String> chbox = FXCollections.observableArrayList();
    private SimpleObjectProperty<Media> currMedia = new SimpleObjectProperty<>();
    private ObservableList<Media> mediaops = FXCollections.observableArrayList();

    public void fill(){
        chbox.add("Media Ascending");
        chbox.add("Media Descending");
        chbox.add("Media By Type Ascending");
        chbox.add("Media By Type Descending");
    }
    public ObservableList<String> getOrders(){
        return chbox;
    }

}
