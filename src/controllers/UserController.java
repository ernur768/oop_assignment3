package controllers;

import entities.User;
import repositories.interfaces.IUserRepository;

public class UserController {

    private final IUserRepository repo;


    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public User findUser(String userName){
        User user = repo.findUser(userName);

        if (user == null){
            System.out.println("User was not found!");
            return null;
        }

        return user;
    }


    public String decreaseBalance(User user, int quantity){
        boolean toppedUp = repo.decreaseBalance(user, quantity);
        return (toppedUp ? "Balance decreased" : "Balance didn't decrease");
    }

    public String increaseSellerBalance(int userId, int quantity){
        boolean increased = repo.increaseSellerBalance(userId, quantity);

        return (increased ? "Seller balance was topped up." : "Seller balance was not topped up.");
    }


    public boolean userExists(String userName){
        User user = repo.findUser(userName);

        return user != null;
    }

    public String register(User user){
        boolean created = false;
        try {
            created = repo.createUser(user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return (created ? "User created" : "User was not created");
    }

    public String createBalance(User user, int balance) {
        boolean toppedUp = repo.topUpBalance(user, balance);
        return (toppedUp ? "Balance topped up" : "Balance didn't top up");
    }

}
