package entities;

import java.sql.*;

public class User {
    private int id;
    private String name;
    private String password;
    private String role = "buyer";
    private int balance;

    static Connection connection = null;
    static PreparedStatement ps =null;
    static ResultSet rs = null;
    private static User currentUser = null;

    public User() {}
    public User(int id, String name, String role, int balance){
        this.id = id;
        this.name = name;
        this.role = role;
        this.balance = balance;
    }

    public User(String role, String name, String password, int balance){
        this.role = role;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
