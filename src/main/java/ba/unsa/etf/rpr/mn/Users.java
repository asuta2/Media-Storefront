package ba.unsa.etf.rpr.mn;

import java.io.Serializable;

public class Users implements Serializable {
    private int idUsers;

    private String username;
    private String email;
    private String password;
    private String PrivilegeLevel;
    private Double Balance;

    public Users() {
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getPrivilegeLevel() {
        return PrivilegeLevel;
    }

    public void setPrivilegeLevel(String privilegeLevel) {
        PrivilegeLevel = privilegeLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return username + " " + email + " " + password + " " + PrivilegeLevel + " " + Balance;
    }
    }
