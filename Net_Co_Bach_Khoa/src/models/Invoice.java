package models;

import java.time.LocalTime;

public class Invoice {
    private int id;
    private Com com;
    private double payment;
    private LocalTime time;
    private static int idIncrement = 1;

    public Invoice(Com com, double payment, LocalTime date) {
        this.id = idIncrement;
        this.com = com;
        this.payment = payment;
        this.time = date;
        idIncrement++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Com getCom() {
        return com;
    }

    public void setCom(Com com) {
        this.com = com;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public LocalTime getDate() {
        return time;
    }

    public void setDate(LocalTime date) {
        this.time = date;
    }

    @Override
    public String toString() {
        return "Invoice ID" + id + '\n'
                + "Máy: " + com + '\n'
                + "Thanh toán: " + payment + '\n'
                + "Giờ xuất hóa đơn: " + time + '\n';
    }
}
