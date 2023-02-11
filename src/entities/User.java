package entities;

public abstract class User {
    private int id;
    private String username;
    private String password;
    private int balance;
    private boolean role;

    public User(String name, String password, int balance, boolean role) {
        this.username = name;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public User(int id, String name, String password, int balance, boolean role) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getRole() {
        return (role ? "seller" : "buyer");
    }

    public boolean isRole(){return role;}

    public void setRole(boolean role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return  "id: " + getId() + "\n" +
                "username: " + getUsername() + "\n" +
                "balance: " + getBalance() + "\n" +
                "role: " + getRole() + "\n";
    }
}
