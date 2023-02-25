package controllers;

import entities.Product;
import entities.User;

import java.util.List;

public class CartController {
    private final ProductController productCtrl;
    private final UserController userCtrl;


    public CartController(ProductController productCtrl, UserController userCtrl) {
        this.productCtrl = productCtrl;
        this.userCtrl = userCtrl;
    }


    public void buyProducts(User user, List<Product> cart){
        for (Product p : cart){
            System.out.println(productCtrl.buyProduct(p));
            userCtrl.decreaseBalance(user, p.getQuantityInCart() * p.getPrice());
            userCtrl.increaseSellerBalance(p.getSellerId(), p.getQuantityInCart() * p.getPrice());
        }
    }
}
