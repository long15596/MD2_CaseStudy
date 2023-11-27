package models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private static int idIncrement = 1;

    public Product(String name, double price, int quantity) {
        this.id = idIncrement;
        idIncrement++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + '\n' +
                "Tên: " + name + '\n' +
                "Giá: " + price + '\n' +
                "Số lượng: " + quantity + '\n';
    }
}
