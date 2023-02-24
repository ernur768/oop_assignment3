package authorization.interfaces;

import entities.User;

public interface IAuthorization {
    User login();
    void register();
}
