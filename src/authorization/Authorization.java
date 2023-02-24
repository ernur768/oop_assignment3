package authorization;

import authorization.interfaces.IAuthorization;
import controllers.UserController;
import entities.User;

import java.util.Objects;
import java.util.Scanner;

public class Authorization implements IAuthorization {

    Scanner scanner;

    UserController userCtrl;

    public Authorization(UserController userCtrl) {
        this.userCtrl = userCtrl;
        scanner = new Scanner(System.in);
    }

    @Override
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


    @Override
    public void register(){
        System.out.print("Enter username: ");
        String username = scanner.next();

        if (userCtrl.userExists(username)){
            System.out.println("User with this username already exists");
            register();
        }

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
        int option;
        do{
            option = scanner.nextInt();
        }while (option < 1 || option > 2);

        User user = null;
        switch (option) {
            case 1 -> user = new User(username, password1, 0, true);
            case 2 -> user = new User(username, password1, 0, false);
        }

        System.out.println(userCtrl.register(user));
        System.out.println();
    }
}
