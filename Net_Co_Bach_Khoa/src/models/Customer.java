package models;

public class Customer {
    private int id;
    private String name;
    private String username;
    private String password;
    private double money;
    public Customer(String name, String username, String password, double money) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.money = money;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Khách ID:" + id + '\n' +
                "Tên: " + name + '\n' +
                "Tiền trong tài khoản: " + money;
    }
}
