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
            connection = db.getConnection();
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
    public boolean buyProduct(Product product) {
//        Connection connection = null;
//
//        try {
//            connection = db.getConnection();
//            PreparedStatement statement = connection.prepareStatement("select * from products where name=?");
//            statement.setString(1, product.getName());
//
//            ResultSet rs = statement.executeQuery();
//
//            if (rs.next()){
//
//            }
//        } catch (SQLException e) {
//            System.out.println("SQLException");
//            System.out.println(e.getMessage());
//        }
        return false;
    }
}
