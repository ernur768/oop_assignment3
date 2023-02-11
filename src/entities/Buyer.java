package entities;

import data.PostgresDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Buyer extends User{
    private List<Product> cart;

    public Buyer(int id, String name, String role, int balance) {
        super(id, name, role, balance);
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public List<Product> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return  "id:" + getId() + "\n" +
                "name:" + getName() + "\n" +
                "id:" + getBalance() + "\n";
    }

    public static void printListOfProducts() throws SQLException, ClassNotFoundException {
        connection = PostgresDB.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ") " + rs.getString("name") + ". Price: " + rs.getInt("price") + "₸" + rs.getArray("category"));
        }
    }
}
