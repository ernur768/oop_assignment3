package repositories.interfaces;

import entities.User;

public interface IUserRepository {

    User findUser(String username);
    boolean decreaseBalance(User user, int quantity);
    boolean increaseSellerBalance(int userId, int quantity);
    boolean createUser(User user);
    boolean topUpBalance(User user, int balance);
}
