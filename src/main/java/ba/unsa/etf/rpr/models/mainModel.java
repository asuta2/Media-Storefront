package ba.unsa.etf.rpr.models;

import ba.unsa.etf.rpr.mn.Media;
import ba.unsa.etf.rpr.mn.Users;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mainModel {
    private ObservableList<Media> mediji = FXCollections.observableArrayList();
    private ObservableList<String> chbox = FXCollections.observableArrayList();
    private SimpleObjectProperty<Media> currMedia = new SimpleObjectProperty<>();
    private ObservableList<Media> mediaops = FXCollections.observableArrayList();
    private SimpleObjectProperty<Users> currUser = new SimpleObjectProperty<>();

    public void fill(){
        chbox.add("Media Ascending");
        chbox.add("Media Descending");
        chbox.add("Media By Type Ascending");
        chbox.add("Media By Type Descending");
    }
    public ObservableList<String> getOrders(){
        return chbox;
    }

    public Users getCurrUser() {
        return currUser.get();
    }

    public void setCurrUser(Users currUser) {
        this.currUser.set(currUser);
    }
}
