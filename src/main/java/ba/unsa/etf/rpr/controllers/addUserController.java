package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Pattern;

public class addUserController {
    public Label mainText;
    public TextField usernameField;
    public Label errorLabel1;
    public TextField emailField;
    public Label errorLabel2;
    public TextField emailConfirmField;
    public Label errorLabel3;
    public PasswordField pass;
    public Label errorLabel4;
    public Button signupButton;
    public ComboBox comboBoxPrivilege;
    private final UsersManager usersManager = new UsersManager();

    @FXML
    public void initialize(){
        comboBoxPrivilege.getItems().addAll("Admin", "User");
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
            }
            else{
                usernameField.getStyleClass().removeAll("errorCode");
                usernameField.getStyleClass().add("successCode");
                errorLabel1.setText("");
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
                errorLabel2.setText("Email is already taken.");
                signupButton.setDisable(true);
            }
            else{
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
            }
            else if(pass.getText().trim().isEmpty()){
                pass.getStyleClass().removeAll("successCode");
                pass.getStyleClass().add("errorCode");
                errorLabel4.setText("Password is empty.");
                signupButton.setDisable(true);
            }
            else{
                pass.getStyleClass().removeAll("errorCode");
                pass.getStyleClass().add("successCode");
                errorLabel4.setText("");
                signupButton.setDisable(false);
            }
        });
    }

    public void checkRegistration(ActionEvent actionEvent) {
        if(usernameField.getStyleClass().contains("successCode") && emailField.getStyleClass().contains("successCode") && emailConfirmField.getStyleClass().contains("successCode") && pass.getStyleClass().contains("successCode")){
            if(comboBoxPrivilege.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please select privilege.");
                alert.showAndWait();
            }else{
                String privilege = comboBoxPrivilege.getSelectionModel().getSelectedItem().toString();
                Users user = new Users();
                user.setUsername(usernameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(pass.getText());
                user.setPrivilegeLevel(privilege.toLowerCase());
                user.setBalance(0.0);
                usersManager.add(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("User successfully added.");
                alert.showAndWait();
                //Reset all styles
                usernameField.getStyleClass().removeAll("successCode", "errorCode");
                emailField.getStyleClass().removeAll("successCode", "errorCode");
                emailConfirmField.getStyleClass().removeAll("successCode", "errorCode");
                pass.getStyleClass().removeAll("successCode", "errorCode");
                //Reset all fields
                usernameField.setText("");
                emailField.setText("");
                emailConfirmField.setText("");
                pass.setText("");
                comboBoxPrivilege.getSelectionModel().clearSelection();
            }
        }
    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
