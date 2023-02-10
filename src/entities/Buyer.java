package entities;

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
}
