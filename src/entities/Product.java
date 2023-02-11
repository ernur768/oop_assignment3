package entities;

public class Product {

    private int id;
    private int sellerId;
    private String name;
    private int price;
    private String category;
    private int remained;


    public Product(int sellerId,String name, int price, String category, int remained) {
        this.sellerId = sellerId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.remained = remained;
    }

    public Product(int id, int sellerId, String name, int price, String category, int remained) {
        this.sellerId = sellerId;
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.remained = remained;
    }

    public boolean isAvailable(){
        return getRemained() > 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRemained() {
        return remained;
    }

    public void setRemained(int remained) {
        this.remained = remained;
    }

    @Override
    public String toString() {
        return  "id: " + getId() + "\t|\tname: " + getName() +
                "\t\t|\tprice: " + getPrice() + "\t\t|\tremained: " + getRemained();
    }
}
