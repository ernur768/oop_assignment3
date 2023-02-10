import data.PostgresDB;
import entities.User;

import java.sql.*;
import java.util.Scanner;

public class LogIn extends User {
    private static Statement statement= null;
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
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()){
            if ((name.equals(rs.getString("username"))) && pass.equals(rs.getString("password"))){
                role = rs.getString("role");
                User.setCurrentUser( new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"), rs.getInt("balance")));
            }
        }
        if(role==null){
            System.out.println("There is no such user such called " + rs.getString("username") + "/ the wrong password!");
            Main.Menu.loginMethod();
        }
        successfulLogin(role);
    }
    public static void successfulLogin(String role) throws SQLException, ClassNotFoundException {
        if (role.equals("seller")) {
//            Main.Menu.forTheSeller();
            return;
        } else if (role.equals("buyer")) {
            Main.Menu.forTheBuyer();
        }
    }
}