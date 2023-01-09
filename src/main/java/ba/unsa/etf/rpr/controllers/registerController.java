package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
import ba.unsa.etf.rpr.mn.Users;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class registerController {
    public TextField usernameField;
    public PasswordField pass;
    public TextField emailField;
    public TextField emailConfirmField;
    public Label errorLabel1;
    public Label errorLabel2;
    public Label errorLabel3;
    public Label errorLabel4;
    public CheckBox checker;
    public Button signupButton;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty email;
    private SimpleStringProperty emailConfirm;
    private final UsersManager usersManager = new UsersManager();

    public registerController(){
        username = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        emailConfirm = new SimpleStringProperty("");
    }
    @FXML
    public void initialize(){
        usernameField.textProperty().bindBidirectional(username);
        pass.textProperty().bindBidirectional(password);
        emailField.textProperty().bindBidirectional(email);
        emailConfirmField.textProperty().bindBidirectional(emailConfirm);
        signupButton.setDisable(true);

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(usernameField.getText().length() > 20){
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                errorLabel1.setText("Username is too long.");
                signupButton.setDisable(true);
            }
            else if(usernameField.getText().trim().isEmpty()){
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                errorLabel1.setText("Username is empty.");
                signupButton.setDisable(true);
            }else if(usersManager.checkUsername(usernameField.getText())){
                usernameField.getStyleClass().removeAll("successCode");
                usernameField.getStyleClass().add("errorCode");
                errorLabel1.setText("Username is taken.");
                signupButton.setDisable(true);
            }
            else{
                usernameField.getStyleClass().removeAll("errorCode");
                errorLabel1.setText("");
                usernameField.getStyleClass().add("successCode");
                signupButton.setDisable(false);
            }
        });
        pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if(pass.getText().length() > 20){
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                errorLabel4.setText("Password is too long.");
                signupButton.setDisable(true);
            }else if(pass.getText().length() < 8){
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                errorLabel4.setText("Password is too short.");
                signupButton.setDisable(true);
            }else if(pass.getText().trim().isEmpty()){
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                errorLabel4.setText("Password is empty.");
                signupButton.setDisable(true);
            }
            else{
                pass.getStyleClass().remove("errorCode");
                errorLabel4.setText("");
                pass.getStyleClass().add("successCode");
                signupButton.setDisable(false);
            }
        });
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(emailField.getText().length() > 50){
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                errorLabel2.setText("Email is too long.");
                signupButton.setDisable(true);
            }
            else if(emailField.getText().trim().isEmpty()){
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                errorLabel2.setText("Email is empty.");
                signupButton.setDisable(true);
            }else if(!patternMatches(emailField.getText(), "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")){
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                errorLabel2.setText("Email is not valid.");
                signupButton.setDisable(true);
            }else if(usersManager.checkEmail(emailField.getText())){
                emailField.getStyleClass().removeAll("successCode");
                emailField.getStyleClass().add("errorCode");
                errorLabel2.setText("Email is taken.");
                signupButton.setDisable(true);
            }else{
                emailField.getStyleClass().removeAll("errorCode");
                emailField.getStyleClass().add("successCode");
                errorLabel2.setText("");
                signupButton.setDisable(false);
            }
        });
        emailConfirmField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(emailConfirmField.getText().equals(emailField.getText())){
                emailConfirmField.getStyleClass().removeAll("errorCode");
                errorLabel3.setText("");
                emailConfirmField.getStyleClass().add("successCode");
                signupButton.setDisable(false);
            }else if(emailConfirmField.getText().trim().isEmpty()) {
                emailConfirmField.getStyleClass().removeAll("successCode");
                emailConfirmField.getStyleClass().add("errorCode");
                errorLabel3.setText("Email is empty.");
                signupButton.setDisable(true);
            }
            else{
                emailConfirmField.getStyleClass().removeAll("successCode");
                emailConfirmField.getStyleClass().add("errorCode");
                errorLabel3.setText("Emails don't match.");
                signupButton.setDisable(true);
            }
        });
    }
    public void checkRegistration(ActionEvent actionEvent) {
        if(!checker.isSelected())
        {
            checker.getStyleClass().add("errorCode");
            return;
        }
        else{
            checker.getStyleClass().removeAll("errorCode");
            signupButton.setDisable(false);
        }
        if(usernameField.getStyleClass().contains("successCode") && pass.getStyleClass().contains("successCode") && emailField.getStyleClass().contains("successCode") && emailConfirmField.getStyleClass().contains("successCode") && checker.isSelected()){
            Users temp = new Users();
            temp.setEmail(emailField.getText());
            temp.setUsername(usernameField.getText());
            temp.setPassword(pass.getText());
            temp.setPrivilegeLevel("user");
            temp.setBalance(0.0);
            usersManager.add(temp);
            System.out.println("User added" + temp.getUsername() );

            //close window and go back to login
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
            //temp solution
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
                Parent root = loader.load();
                Stage stage1 = new Stage();
                stage1.setTitle("Login");
                stage1.setScene(new Scene(root));
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


}
