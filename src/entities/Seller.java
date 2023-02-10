package entities;

import data.PostgresDB;
import repositories.ProductRepository;

import java.sql.*;

public class Seller extends User{

    public Seller(String name, String password, String role, int balance) {
        super(name, password, role, balance);
    }

    public String dataSeller(){
        return "id:" + getId() + "\n" +
                "name:" + getName() + "\n" +
                "id:" + getBalance() + "\n";

    }
    public void addProduct(Product product, int quantity) throws SQLException{
        PostgresDB db = new PostgresDB();
        ProductRepository productRepository = new ProductRepository(db);
        productRepository.addProduct(product, quantity);
    }
    public void deleteProduct(int productId) throws SQLException{
        PostgresDB db = new PostgresDB();
        ProductRepository productRepository = new ProductRepository(db);
        productRepository.deleteProduct(productId);
    }



}
