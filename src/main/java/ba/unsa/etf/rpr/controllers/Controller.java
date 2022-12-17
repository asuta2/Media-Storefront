package ba.unsa.etf.rpr.controllers;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class Controller  {
    public Hyperlink RegisterBtn;
    public TextField UsernameField;
    public PasswordField PasswordField;
    public Button loginButton;
    public Label errorLabel;
    private SimpleStringProperty owo;

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public Controller(){
        owo = new SimpleStringProperty("");
    }
    @FXML
    public void initialize(){
        UsernameField.textProperty().bindBidirectional(owo);
    }
    public void checkSignIn(ActionEvent actionEvent) {
        if(UsernameField.getText().isEmpty() || PasswordField.getText().isEmpty()){
            System.out.println("Niste unijeli sve podatke!");
            errorLabel.setText("Please check your password and account name and try again.");
            UsernameField.getStyleClass().add("errorCode");
            PasswordField.getStyleClass().add("errorCode");
        }
        else if(!patternMatches(UsernameField.getText(), "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")){
            System.out.println("Neispravan format email adrese!");
            errorLabel.setText("Please check your password and account name and try again.");
            UsernameField.getStyleClass().add("errorCode");
            PasswordField.getStyleClass().add("errorCode");
        } else if (PasswordField.getText().length() < 8){
            System.out.println("Lozinka mora imati najmanje 8 znakova!");
            errorLabel.setText("Please check your password and account name and try again.");
            UsernameField.getStyleClass().add("errorCode");
            PasswordField.getStyleClass().add("errorCode");
        } else {
            UsernameField.getStyleClass().remove("errorCode");
            PasswordField.getStyleClass().remove("errorCode");
            errorLabel.setText("");
            System.out.println("Uspješno ste se prijavili!");
        }
    }

    public void hyperClick(ActionEvent actionEvent) {
        //if hypelink is clicked then open register window
        if(actionEvent.getSource() == RegisterBtn){
            try {
                Stage startStage = (Stage) RegisterBtn.getScene().getWindow();
                startStage.hide();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Register");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                stage.setOnCloseRequest(e -> {
                    e.consume();
                    System.out.println("Closing registration");
                    closeProgram(stage);
                    startStage.show();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //if window is closed, go back to login window






    }

    private void closeProgram(Stage stage) {
        stage.close();
    }
}
