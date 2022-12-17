package ba.unsa.etf.rpr.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registerController {
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField emailField;
    public TextField emailConfirmField;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty email;
    private SimpleStringProperty emailConfirm;

    registerController(){
        username = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        emailConfirm = new SimpleStringProperty("");
    }
    @FXML
    public void initialize(){
        usernameField.textProperty().bindBidirectional(username);
        passwordField.textProperty().bindBidirectional(password);
        emailField.textProperty().bindBidirectional(email);
        emailConfirmField.textProperty().bindBidirectional(emailConfirm);
    }



}
