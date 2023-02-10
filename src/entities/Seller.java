package entities;

import data.PostgresDB;
import repositories.ProductRepository;

import java.sql.SQLException;
import java.sql.Connection;

public class Seller {
    private int id;
    private String name;
    private int balance;

    public Seller(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
