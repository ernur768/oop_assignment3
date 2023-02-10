package repositories;

import data.interfaces.IDB;
import entities.Product;
import repositories.interfaces.IProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository implements IProductRepository {

    private final IDB db;

    public ProductRepository(IDB db) {
        this.db = db;
    }


    @Override
    public Product findProduct(String productName) {
        Connection connection = null;

        try {
            connection = IDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from products where name=?");
            statement.setString(1, productName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                return new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("price"), rs.getInt("remained"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            try {
                assert connection != null;
                connection.close();
            }
            catch (SQLException e){
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
            catch (NullPointerException e){
                System.out.println("NullPointerException");
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public boolean buyProduct(Product product, int quantity) {
        if (product.getRemained() - quantity < 0){
            System.out.println("There are only" + product.getRemained() + " products left");
            return false;
        }

        Connection connection;
        try {
            connection = IDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("update products set remained=? where id=?");
            statement.setInt(1,product.getRemained() - quantity);
            statement.setInt(2, product.getId());
            statement.execute();

            product.setRemained(product.getRemained() - quantity);

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addProduct(Product product, int quantity) {
        Connection connection;
        try {
            connection = IDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into products(id, name, price, remainded) values (?,?,?,?)");
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getRemained());
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        return false;
    }


    public boolean deleteProduct(int productId) {
        Connection connection;

        try {
            connection = IDB.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from products where id=?");
            statement.setInt(1, productId);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        return false;
    }
}

