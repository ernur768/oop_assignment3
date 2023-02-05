import java.util.List;

public class User {

    private int id;
    private String name;
    private int balance;
    private List<Product> cart;

    public User(int id, String name, int balance) {
        setId(id);
        setName(name);
        setBalance(balance);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<Product> getCart() {
        return cart;
    }
}
