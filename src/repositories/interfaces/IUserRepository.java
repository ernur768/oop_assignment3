package repositories.interfaces;

import entities.User;

public interface IUserRepository {

    User findUser(String username);
    boolean createUser(User user);
    boolean topUpBalance(User user, int balance);
}
