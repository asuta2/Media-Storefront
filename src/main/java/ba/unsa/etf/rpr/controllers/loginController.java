package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
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

public class loginController {
    public Hyperlink RegisterBtn;
    public TextField UsernameField;
    public PasswordField PasswordField;
    public Button loginButton;
    public Label errorLabel;
    private SimpleStringProperty email;
    private SimpleStringProperty pass;

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public loginController(){
        email = new SimpleStringProperty("");
        pass = new SimpleStringProperty("");
    }
    @FXML
    public void initialize(){
        UsernameField.textProperty().bindBidirectional(email);
        PasswordField.textProperty().bindBidirectional(pass);
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
        } else {
            UsersDao usersDao = new UsersDaoSQLImpl();
            if(usersDao.checkUser(UsernameField.getText(), PasswordField.getText())){
                System.out.println("Uspjesno ste se prijavili!" + UsernameField.getText() + " " + PasswordField.getText());
                errorLabel.setText("You have successfully signed in.");
                UsernameField.getStyleClass().remove("errorCode");
                PasswordField.getStyleClass().remove("errorCode");
                try {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                    stage.setTitle("Workshop");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Neispravni podaci!" + UsernameField.getText() + " " + PasswordField.getText());
                errorLabel.setText("Please check your password and account name and try again.");
                UsernameField.getStyleClass().add("errorCode");
                PasswordField.getStyleClass().add("errorCode");
            }
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
                stage.setTitle("Registration");
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

    }

    private void closeProgram(Stage stage) {
        stage.close();
    }
}
