package entities;


import entities.interfaces.IBuyer;

import java.util.List;

public class Buyer extends User implements IBuyer {

    private List<Product> cart;


    public Buyer(String username, String password, int balance, boolean role) {
        super(username, password, balance, role);
    }

    public Buyer(int id, String username, String password, int balance, boolean role) {
        super(id, username, password, balance, role);
    }


    public void addProductToCart(Product product){
        cart.add(product);
    }

    public List<Product> getCart() {
        List<Product> clone = cart;
        return clone;
    }

}
