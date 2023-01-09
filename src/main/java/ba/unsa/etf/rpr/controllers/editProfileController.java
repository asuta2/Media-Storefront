package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.util.regex.Pattern;

public class editProfileController {
    public Button confirmButton;
    public Label errorLabel4;
    public PasswordField pass;
    public Label errorLabel2;
    public TextField emailField;
    public Label errorLabel1;
    public TextField usernameField;
    private final UsersManager usersManager = new UsersManager();


    public void initialize(){
        usernameField.setText(UsersManager.getCurrentUser().getUsername());
        emailField.setText(UsersManager.getCurrentUser().getEmail());
        pass.setText(UsersManager.getCurrentUser().getPassword());
        //Listener for username field
        usernameField.textProperty().addListener((obs, oldIme, newIme) -> {
            if(newIme.length() < 3 || newIme.length() > 20){
                errorLabel1.setText("Username must be between 3 and 20 characters long!");
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }else if(!Pattern.matches("^[a-zA-Z0-9]+$", newIme)){
                errorLabel1.setText("Username can only contain letters and numbers!");
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }else if(usersManager.checkUsername(newIme)){
                errorLabel1.setText("Username already exists!");
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }
            else{
                usernameField.getStyleClass().removeAll("errorCode");
                usernameField.getStyleClass().add("successCode");
                errorLabel1.setText("");
                confirmButton.setDisable(false);
            }
        });
        //Listener for email field
        emailField.textProperty().addListener((obs, oldIme, newIme) -> {
            if(!patternMatches(emailField.getText(), "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")){
                errorLabel2.setText("Email is not valid!");
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }else if(usersManager.checkEmail(newIme)){
                errorLabel2.setText("Email already exists!");
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }
            else{
                emailField.getStyleClass().add("successCode");
                emailField.getStyleClass().removeAll("errorCode");
                errorLabel2.setText("");
                confirmButton.setDisable(false);
            }
        });
        //Listener for password field
        pass.textProperty().addListener((obs, oldIme, newIme) -> {
            if(newIme.length() < 8){
                errorLabel4.setText("Password must be at least 8 characters long!");
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }else if(newIme.length() > 20){
                errorLabel4.setText("Password must be at most 20 characters long!");
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                confirmButton.setDisable(true);
            }
            else{
                pass.getStyleClass().add("successCode");
                pass.getStyleClass().removeAll("errorCode");
                errorLabel4.setText("");
                confirmButton.setDisable(false);
            }
        });

    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void confirmEdit(ActionEvent actionEvent) {
        UsersManager.getCurrentUser().setUsername(usernameField.getText());
        UsersManager.getCurrentUser().setEmail(emailField.getText());
        UsersManager.getCurrentUser().setPassword(pass.getText());
        usersManager.editProfile(UsersManager.getCurrentUser());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();

    }
}
