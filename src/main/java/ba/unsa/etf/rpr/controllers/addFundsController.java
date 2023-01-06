package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UsersManager;
import javafx.event.ActionEvent;

public class addFundsController {
    UsersManager usersManager = new UsersManager();
    public javafx.scene.control.Label welcome;

    public void Add5(ActionEvent actionEvent) {
        //Adds 5$ to the balance of the user
        usersManager.addFunds(5);
        usersManager.update(UsersManager.getCurrentUser());
        welcome.setText("Welcome " + UsersManager.getCurrentUser().getUsername() + " your balance is " + UsersManager.getCurrentUser().getBalance() + "$");

    }

    public void Add10(ActionEvent actionEvent) {
        //Adds 10$ to the balance of the user
        usersManager.addFunds(10);
        usersManager.update(UsersManager.getCurrentUser());
        welcome.setText("Welcome " + UsersManager.getCurrentUser().getUsername() + " your balance is " + UsersManager.getCurrentUser().getBalance() + "$");

    }

    public void Add30(ActionEvent actionEvent) {
        //Adds 30$ to the balance of the user
        usersManager.addFunds(30);
        usersManager.update(UsersManager.getCurrentUser());
        welcome.setText("Welcome " + UsersManager.getCurrentUser().getUsername() + " your balance is " + UsersManager.getCurrentUser().getBalance() + "$");

    }

    public void Add50(ActionEvent actionEvent) {
        //Adds 50$ to the balance of the user
        usersManager.addFunds(50);
        usersManager.update(UsersManager.getCurrentUser());
        welcome.setText("Welcome " + UsersManager.getCurrentUser().getUsername() + " your balance is " + UsersManager.getCurrentUser().getBalance() + "$");
    }
}
