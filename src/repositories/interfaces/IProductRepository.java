package repositories.interfaces;

import entities.Buyer;
import entities.Product;
import entities.Seller;

import java.util.List;

public interface IProductRepository {

    Product findProduct(String productName);
    boolean buyProduct(Product product, int quantity);
    boolean createProduct(Product product);
    boolean deleteProduct(int productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsBySellerId(Seller seller);

    boolean productExists(String productName);
}
