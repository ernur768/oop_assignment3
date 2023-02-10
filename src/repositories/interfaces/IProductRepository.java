package repositories.interfaces;

import entities.Product;

public interface IProductRepository {

    Product findProduct(String productName);
    boolean buyProduct(Product product, int quantity);


}
