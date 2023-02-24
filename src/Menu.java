//import entities.Buyer;
//import entities.Seller;
//import entities.User;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Menu {
//    static Scanner in = new Scanner(System.in);
//    public static void loginMethod() throws SQLException, ClassNotFoundException {
//        System.out.println("""
//                    1) Log in
//                    2) Registration(In process)
//                    """);
//        int menu = in.nextInt();
//        switch (menu){
//            case 1 -> auth.LogIn.login();
//            case 2 -> Registration.inProcess();
//        }
//    }
//    public static void forTheSeller() throws SQLException {
//        System.out.println("""
//                    1) Add a product
//                    2) List of products (In process)
//                    """);
//        int menu = in.nextInt();
//        switch (menu){
//            case 1 -> Seller.addProduct();
////            case 2 -> Seller.dataSeller();
//        }
//        System.out.println("\n");
//        forTheSeller();
//    }
//    public static void forTheBuyer() throws SQLException, ClassNotFoundException {
//        System.out.println("""
//                    1) List of products
//                    2) Buy products
//                    3) Exit
//                    """);
//        int menu = in.nextInt();
//        switch (menu){
//            case 1 -> Buyer.printListOfProducts();
//                1 buyer
//                    insert into buyer
//                2 seller
//                    insert into seller
////            case 2 -> Buyer.buyProductInMenu();
//            case 3 -> {
//                User.setCurrentUser(null);
//                Menu.loginMethod();
//            }
//        }
//
//        forTheBuyer();
//    }
//}


