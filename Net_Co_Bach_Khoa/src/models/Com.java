package models;

public class Com {
    private int id;
    private String name;
    private double price = 5000;
    private double payment;
    private static int idIncrement = 1;
    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    private Customer customer;
    public Com(String name) {
        this.id = idIncrement;
        idIncrement++;
        this.name = name;
        this.customer = null;
    }

    public double getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "PC ID: " + id + '\n' +
                "Tên máy: " + name + '\n' +
                "Khách đang dùng: " + customer + '\n';
    }
}
