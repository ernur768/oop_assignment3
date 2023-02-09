package MEA;

public class Product {

    private int id;
    private String name;
    private int price;
    private String expirationDate;
    private int remained;
    private boolean isAvailable;

    public Product(int id, String name, int price, String expiration_date, int remained){
        setId(id);
        setName(name);
        setPrice(price);
        setExpirationDate(expiration_date);
        setRemained(remained);
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRemained(int remained) {
        this.remained = remained;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getRemained() {
        return remained;
    }
}
