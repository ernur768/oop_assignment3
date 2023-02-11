package entities.interfaces;

import entities.Product;

import java.util.List;

public interface IBuyer {
    void addProductToCart(Product product);
    List<Product> getCart();
}
