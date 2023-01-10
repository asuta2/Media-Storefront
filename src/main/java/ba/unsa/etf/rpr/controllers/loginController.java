package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
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

import static javafx.scene.control.ButtonBar.setButtonUniformSize;

public class loginController {
    public Hyperlink RegisterBtn;
    public TextField UsernameField;
    public PasswordField PasswordField;
    public Button loginButton;
    public Label errorLabel;
    private final SimpleStringProperty email;
    private final SimpleStringProperty pass;
    private final UsersManager usersManager = new UsersManager();
    //private final UsersDao usersDao = new UsersDaoSQLImpl();

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

            if(usersManager.checkUser(UsernameField.getText(), PasswordField.getText())){
                System.out.println("Uspjesno ste se prijavili!" + UsernameField.getText() + " " + PasswordField.getText());
                errorLabel.setText("You have successfully signed in.");
                UsernameField.getStyleClass().removeAll("errorCode");
                PasswordField.getStyleClass().removeAll("errorCode");
                UsernameField.getStyleClass().add("successCode");
                PasswordField.getStyleClass().add("successCode");
                UsersManager.setCurrentUser(usersManager.getUserByEmail(UsernameField.getText()));
                if(UsersManager.getCurrentUser().getPrivilegeLevel().equals("admin")){
                    try {
                        Stage prim = (Stage) loginButton.getScene().getWindow();
                        Stage stage=new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
                        stage.setTitle("Admin");
                        stage.setScene(new Scene(root, 600, 400));
                        stage.show();
                        prim.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Stage prim = (Stage) loginButton.getScene().getWindow();
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                        Parent root = loader.load();
                        mainController nv = loader.getController();
                        nv.usernameButton.setText(UsersManager.getCurrentUser().getUsername());
                        stage.setTitle("Media Library");
                        stage.setScene(new Scene(root,700,500));
                        stage.setMinHeight(700);
                        stage.setMinWidth(500);
                        stage.setResizable(true);
                        setButtonUniformSize(nv.orderByBox,false);
                        setButtonUniformSize(nv.usernameButton,false);
                        setButtonUniformSize(nv.addFundsButton,false);
                        nv.usernameButton.setWrapText(false);

                        stage.show();
                        prim.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
