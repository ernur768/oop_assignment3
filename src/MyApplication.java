import controllers.ProductController;
import controllers.UserController;
import entities.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class MyApplication {

    private final UserController userCtrl;
    private final ProductController productCtrl;
    private User user;
    private List<Product> cart;
    private final Scanner scanner;
    private int option;

    public MyApplication(ProductController productCtrl, UserController userCtrl) {
        this.userCtrl = userCtrl;
        this.productCtrl = productCtrl;
        this.scanner = new Scanner(System.in);
        this.cart = new LinkedList<>();
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
            System.out.println();

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
                else {
                    startBuyerInterface();
                }
            }

        }


    }


    public User login(){
        System.out.print("Enter username: ");
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
        System.out.print("Confirm password: ");
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
            case 1 -> user = new User(username, password1, 0, true);
            case 2 -> user = new User(username, password1, 0, false);
        }

        System.out.println(userCtrl.register(user));
        System.out.println();
    }

    public void startSellerInterface(){
        System.out.println("""
                [1] My account
                [2] Show my products
                [3] Add new product
                [4] Remove the product
                [5] Top up the balance
                [6] Exit""");
        System.out.print("Choose option: ");
        optionInRange(4);
        System.out.println();

        switch (option) {
            case 1 -> System.out.println(user.toString());
            case 2 -> {
                List<Product> products = productCtrl.selectSellerProducts(user);
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

                Product product = new Product(user.getId(), name, price, category, remained);

                System.out.println(productCtrl.addProduct(product));
            }
            case 4 -> {
                System.out.print("Enter product id: ");
                int productId = scanner.nextInt();

                System.out.println(productCtrl.removeProduct(productId));
            }
            case 5 -> {
                System.out.print("Enter the balance: ");
                int getBalance = scanner.nextInt();

                System.out.println(userCtrl.createBalance(user, getBalance));
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

    public void startBuyerInterface (){
        System.out.print("""
                [1] My account
                [2] Search product by name
                [3] View products in the cart
                [4] Top up the balance
                [5] Exit
                """);
        System.out.print("Choose option: ");
        optionInRange(3);
        System.out.println();

        switch (option){
            case 1 -> System.out.println(user.toString());
            case 2 -> {
                Product product;
                do {
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    product = productCtrl.findProduct(name);
                }while (product == null);
                System.out.println(product.toString());
                System.out.println("""
                        [1] Add to cart
                        [2] Close""");
                System.out.print("Choose option: ");
                optionInRange(2);

                switch (option){
                    case 1 -> {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (product.getRemained() < quantity){
                            System.out.println("Only " + product.getRemained() + " pieces left");
                            break;
                        }
                        product.setQuantityInCart(quantity + product.getQuantityInCart());

                        cart.add(product);

                        for (int i = 0; i < cart.size(); i++) {
                            for (int j = i + 1; j < cart.size() ; j++) {
                                if (cart.get(i).getId() == cart.get(j).getId()) {
                                    cart.get(i).setQuantityInCart(cart.get(i).getQuantityInCart() + cart.get(j).getQuantityInCart());
                                    cart.remove(j);
                                    j--;
                                }
                            }
                        }
                    }
                    case 2 -> {
                        break;
                    }

                }
            }
            case 3 -> {
                System.out.println("Cart:");

                for(Product product : cart) {
                    System.out.println("id: " + product.getId() + "\t|\tname: " + product.getName() + "\t\t|\tprice: " + product.getPrice() + "\t\t|\tquantity: " + product.getQuantityInCart());
                }

            }

            case 4 -> {
                System.out.print("Enter the balance: ");
                int getBalance = scanner.nextInt();

                System.out.println(userCtrl.createBalance(user, getBalance));
            }

            case 5 -> {
                
            }
        }

        System.out.println();
    }
}
