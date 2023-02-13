import controllers.ProductController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import entities.Product;
import repositories.ProductRepository;
import repositories.UserRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB();

        IProductRepository productRepository = new ProductRepository(db);
        IUserRepository userRepository = new UserRepository(db);

        ProductController productController = new ProductController(productRepository);
        UserController userController = new UserController(userRepository);

        MyApplication app =new MyApplication(productController, userController);
        app.start();

    }
}