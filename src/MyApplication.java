import authorization.Authorization;
import controllers.CartController;
import controllers.ProductController;
import controllers.UserController;
import entities.*;

import java.io.IOException;
import java.util.*;

public class MyApplication {

    private final UserController userCtrl;
    private final ProductController productCtrl;
    private final Authorization authorization;
    private User user;
    private List<Product> cart;
    private final CartController cartCtrl;
    private final Scanner scanner;
    private int option;

    public MyApplication(ProductController productCtrl, UserController userCtrl) {
        this.userCtrl = userCtrl;
        this.productCtrl = productCtrl;
        this.cartCtrl = new CartController(productCtrl, userCtrl);
        this.authorization = new Authorization(userCtrl);
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
                    case 1 -> user = authorization.login();
                    case 2 -> {
                        authorization.register();
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


    public void startSellerInterface(){
        System.out.println("""
                [1] My account
                [2] Show my products
                [3] Add new product
                [4] Remove the product
                [5] Top up the balance
                [6] Exit""");
        System.out.print("Choose option: ");
        optionInRange(6);
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

                Product product = new Product(user.getId(), name, price,category, remained);

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
            case 6 -> {
                System.out.println();
                start();
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
                [2] Search product
                [3] Get all product list
                [4] View products in the cart
                [5] Top up the balance
                [6] Exit
                """);
        System.out.print("Choose option: ");
        optionInRange(6);
        System.out.println();

        switch (option){
            case 1 -> System.out.println(user.toString());
            case 2 -> {
                System.out.print("""
                [1] By name
                [2] By category
                """);
                System.out.print("Choose option: ");
                optionInRange(2);
                System.out.println();
                switch (option) {
                    case 1 -> {
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
                    case 2 -> {
                        List<Product> products;
                        do {
                            System.out.print("Enter product category: ");
                            String category = scanner.next();
                            products = productCtrl.getProductsByCategory(category);
                        } while (products == null);
                        System.out.println();
                        for (Product p : products) {
                            System.out.println(("id: " + p.getId() + "\t|\tname: " + p.getName() + "\t|\tprice: " + p.getPrice() + "\t|\tremained: " + p.getRemained() + "\t|\tcategory: " + p.getCategory()));
                        }
                    }
                }
            }
            case 3 -> {
                System.out.println("All products:");
                System.out.println("ID: " + " Product name: " + " Price: " + " Remained: ");
                for (Product p : productCtrl.getAllProducts()){
                    System.out.println(p.getId() + " \t " + p.getName() + " \t\t\t" + p.getPrice() + " \t" + p.getRemained());
                }
            }

            case 4 -> {
                if (cart.size() == 0){
                    System.out.println("Cart is empty");
                    break;
                }
                System.out.println("Cart:");

                for(Product product : cart) {
                    System.out.println("id: " + product.getId() + "\t|\tname: " + product.getName() + "\t\t|\tprice: " + product.getPrice() + "\t\t|\tquantity: " + product.getQuantityInCart());
                }


                System.out.println("""
                        Buy products in the cart ?
                        [1] Yes
                        [2] No""");
                System.out.print("Choose option: ");
                optionInRange(2);

                if (option == 1){
                    cartCtrl.buyProducts(user, cart);
                    cart.clear();
                }
            }

            case 5 -> {
                System.out.print("Enter the balance: ");
                int getBalance = scanner.nextInt();

                System.out.println(userCtrl.createBalance(user, getBalance));
            }

            case 6 -> {
                System.out.println();
                start();
            }
        }

        System.out.println();
    }
}


