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

}