package repositories.interfaces;

import entities.Product;
import entities.User;

import java.util.List;

public interface IProductRepository {

    Product findProduct(String productName);
    boolean decreaseQuantity(Product product, int quantity);
    boolean createProduct(Product product);
    boolean deleteProduct(int productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsBySellerId(User seller);
    boolean isAvailable(String name);
//    Product getProduct(int productId);
    String UpdateAmount(String name,int quantity);
    boolean productExists(String productName);
}
