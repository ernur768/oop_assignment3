package entities;

public class Product {

    private int id;
    private String name;
    private int price;
    private int remained;


    public Product(int id, String name, int price, int remained){
        setId(id);
        setName(name);
        setPrice(price);
        setRemained(remained);
    }

    public boolean isAvailable(){
        return getRemained() > 0;
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

    public int getRemained() {
        return remained;
    }
}
