package auth;

import data.PostgresDB;
import entities.User;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LogIn extends User{
    static Connection connection = null;
    static Scanner in = new Scanner(System.in);
    public LogIn(String username, String password, String role, int balance) {
        super(username, password, role, balance);
    }

    public static void login() throws SQLException, ClassNotFoundException {
        System.out.println("Write username: ");
        String username = in.next();
        System.out.println("Write password: ");
        String pass = in.next();
        searchUser(username, pass);
    }

    public static void searchUser(String name, String pass) throws SQLException, ClassNotFoundException {
        connection = PostgresDB.getConnection();
        String role = null;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()){
            if ((name.equals(rs.getString("username"))) && pass.equals(rs.getString("password"))){
                role = rs.getString("role");
                User.setCurrentUser( new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"), rs.getInt("balance")));
            }
        }
        if(role==null){
            System.out.println("There is no such user or the wrong password!");
//            Menu.loginMethod();
        }
        successfulLogin(role);
    }

    public static void successfulLogin(String role) throws SQLException {
        if (role.equals("seller")) {
//            Menu.forTheSeller();
            System.out.println("Seller menu in process...");
        } else if (role.equals("buyer")) {
//            Menu.forTheBuyer();
            System.out.println("Buyer menu in process...");
        }
    }
}