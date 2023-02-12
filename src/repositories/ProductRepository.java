package repositories;

import data.interfaces.IDB;
import entities.Product;
import entities.User;
import repositories.interfaces.IProductRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    private final IDB db;

    public ProductRepository(IDB db) {
        this.db = db;
    }

    private List<Product> createProductList(ResultSet rs) throws SQLException {
        List<Product> products = new LinkedList<>();
        while (rs.next()){
            Product product = new Product(rs.getInt("id"), rs.getInt("sellerId"),
                    rs.getString("name"), rs.getInt("price"),
                    rs.getString("category"), rs.getInt("remained"));

            products.add(product);
        }

        return products;
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
                return new Product(rs.getInt("id"), rs.getInt("sellerId"),
                        rs.getString("name"), rs.getInt("price"),
                        rs.getString("category"), rs.getInt("remained"));
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
            return false;
        }

        Connection connection = null;
        try {
            connection = db.getConnection();
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
        finally {
            try{
                connection.close();
            }
            catch (NullPointerException e){
                System.out.println("NullPointerException");
                System.out.println(e.getMessage());
            }
            catch (SQLException e){
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean createProduct(Product product) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("insert into products(sellerId,name, price, category, remained) values (?, ?, ?, ?, ?)");
            statement.setInt(1, product.getSellerId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getCategory());
            statement.setInt(5, product.getRemained());
            statement.execute();

            return true;
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
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from products where id=?");
            statement.setInt(1, productId);
            statement.execute();

            return true;
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
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;

        try {
            connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");

            return createProductList(rs);
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQLException");
                System.out.println(e.getMessage());
            }

        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from products where category=?");
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();

            return createProductList(rs);
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Product> getProductsBySellerId(User seller) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from products where sellerId=?");
            statement.setInt(1, seller.getId());
            ResultSet rs = statement.executeQuery();

            return createProductList(rs);
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
            return null;
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
    public boolean productExists(String productName) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from products where name=?");
            statement.setString(1, productName);
            ResultSet rs = statement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.out.println("SQLException");
            System.out.println(e.getMessage());
        }
        return false;
    }


}

