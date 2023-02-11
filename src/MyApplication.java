import controllers.ProductController;
import controllers.UserController;
import entities.Buyer;
import entities.Product;
import entities.Seller;
import entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MyApplication {

    private final UserController userCtrl;
    private final ProductController productCtrl;
    private User user;
    private final Scanner scanner;
    private int option;

    public MyApplication(ProductController productCtrl, UserController userCtrl) {
        this.userCtrl = userCtrl;
        this.productCtrl = productCtrl;
        this.scanner = new Scanner(System.in);
    }

    private void optionInRange(int end){
        do{
            option = scanner.nextInt();
        }while (option < 1 && end < option);
    }

    public void start(){
        while (true) {
            System.out.println("""
                    Welcome to market
                    [1] Login
                    [2] Registration""");
            System.out.print("Choose option: ");
            optionInRange(2);

            try {
                switch (option) {
                    case 1 -> user = this.login();
                    case 2 -> {
                        this.register();
                        start();
                    }
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println();

            while (true){

                if (user.isRole()){
                    startSellerInterface();
                }
            }

        }


    }


    public User login(){
        System.out.print("\nEnter username: ");
        String username = scanner.next();

        User user = userCtrl.findUser(username);
        if (user == null) {
            return login();
        }

        System.out.print("Enter password: ");
        String password = scanner.next();

        if (Objects.equals(password, user.getPassword())){
            return user;
        }
        else {
            System.out.println("Wrong password");
            return login();
        }

    }

    public void register(){
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password1 = scanner.next();
        System.out.print("Enter password: ");
        String password2 = scanner.next();
        if (!Objects.equals(password1, password2)){
            System.out.println("passwords do not match");
            register();
        }
        System.out.print("""
                [1] seller
                [2] buyer
                Select role:\s""");

        optionInRange(2);

        User user = null;
        switch (option) {
            case 1 -> user = new Seller(username, password1, 0, true);
            case 2 -> user = new Buyer(username, password1, 0, false);
        }

        userCtrl.register(user);

    }

    public void startSellerInterface(){
        System.out.println("""
                [1] My account
                [2] Show my products
                [3] Add new product
                [4] Remove the product""");
        System.out.print("Choose option: ");
        optionInRange(2);
        System.out.println();

        switch (option) {
            case 1 -> System.out.println(user.toString());
            case 2 -> {
                List<Product> products = productCtrl.selectSellerProducts((Seller) user);
                products.forEach(product -> System.out.println(product.toString()));
            }
            case 3 -> {
                String name;
                do {
                    System.out.print("Enter product name: ");
                    name = scanner.next();
                    if (!productCtrl.productExists(name)){
                        break;
                    }
                    System.out.println("Product exists");
                }while (true);
                System.out.print("Enter product price: ");
                int price = scanner.nextInt();
                System.out.print("Enter product category: ");
                String category = scanner.next();
                System.out.print("Enter product quantity: ");
                int remained = scanner.nextInt();

                Product product = new Product( ((Seller) user).getId(), name, price, category, remained);

                System.out.println(productCtrl.addProduct(product));
            }
            case 4 -> {

            }
        }

        try {
            System.out.print("press any key to continue");
            System.in.read();
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
