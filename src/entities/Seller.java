package entities;


public class Seller extends User{


    public Seller(String username, String password, int balance, boolean role) {
        super(username, password, balance, role);
    }

    public Seller(int id, String username, String password, int balance, boolean role) {
        super(id, username, password, balance, role);
    }
}
