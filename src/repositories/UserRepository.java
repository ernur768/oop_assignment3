package repositories;

import data.interfaces.IDB;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public User findUser(String username) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from users where username=?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"), rs.getInt("balance"),
                        rs.getBoolean("role"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e){
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }

        return null;
    }


    public boolean decreaseBalance(User user, int quantity){
        Connection connection = null;

        if (user.getBalance() < quantity){
            return false;
        }

        try{
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("update users set balance=? where id=?");
            statement.setInt(1,user.getBalance() - quantity);
            statement.setInt(2, user.getId());
            statement.execute();

            user.setBalance(user.getBalance() - quantity);

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean increaseSellerBalance(int userId, int quantity) {
        Connection connection = null;

        try{
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET balance = balance + ? WHERE id=?");
            statement.setInt(1, quantity);
            statement.setInt(2, userId);
            statement.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public boolean createUser(User user) {
        Connection connection = null;

        try{
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("insert into users (username, password, balance, role) values (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, 0);
            statement.setBoolean(4, user.isRole());
            statement.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean topUpBalance(User user, int balance) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("update users set balance=? where id=?");
            statement.setInt(1,user.getBalance() + balance);
            statement.setInt(2, user.getId());
            statement.execute();

            user.setBalance(user.getBalance() + balance);

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }
    }

}