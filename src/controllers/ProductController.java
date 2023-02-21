package controllers;

import entities.Product;
import entities.User;
import repositories.interfaces.IProductRepository;

import java.util.List;

public class ProductController {

    private final IProductRepository repo;


    public ProductController(IProductRepository repo) {
        this.repo = repo;
    }

    public Product findProduct(String productName){
        Product product = repo.findProduct(productName);

        if (product == null){
            System.out.println("Product was not found!");
            return null;
        }

        return product;
    }

    public String addProduct(Product product){
        boolean created = repo.createProduct(product);

        return (created ? "Product was added to market" : "Product was not added to market");
    }

    public String removeProduct(int productId){
        boolean removed = repo.deleteProduct(productId);

        return (removed ? "Product was removed from market" : "Product was not removed from market");
    }

    public List<Product> getAllProducts(){
        List<Product> products = repo.getAllProducts();

        if (products == null){
            System.out.println("Something wrong from db...");
            return null;
        }

        return products;
    }

    public List<Product> getProductsByCategory(String category){
        List<Product> products = repo.getProductsByCategory(category);

        if (products == null){
            System.out.println("We cannot find a product in this category.");
            return null;
        }

        return products;
    }

    public List<Product> selectSellerProducts(User seller){
        List<Product> products = repo.getProductsBySellerId(seller);
        if (products.isEmpty()){
            System.out.println("You do not have any product in market");
        }

        return products;
    }

    public boolean productExists(String productName){
        return repo.productExists(productName);
    }

}