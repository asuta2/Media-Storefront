package ba.unsa.etf.rpr.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {
    public Hyperlink RegisterBtn;

    public void hyperClick(ActionEvent actionEvent) {
        //if hypelink is clicked then open register window
        if(actionEvent.getSource() == RegisterBtn){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Register");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
}
