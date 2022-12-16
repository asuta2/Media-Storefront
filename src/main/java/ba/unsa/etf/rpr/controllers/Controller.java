package ba.unsa.etf.rpr.controllers;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {
    public Hyperlink RegisterBtn;
    public TextField UsernameField;
    private SimpleStringProperty owo;
    //public PasswordField PasswordField;

    public Controller(){
        owo = new SimpleStringProperty("");
    }
    @FXML
    public void initialize(){
        UsernameField.textProperty().bindBidirectional(owo);
    }

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
        //if window is closed, go back to login window
        else{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
}
