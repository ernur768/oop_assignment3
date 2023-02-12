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

    public String register(User user){
        boolean created = repo.createUser(user);
        return (created ? "User created" : "User was not created");
    }
}
