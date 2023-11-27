package controllers;

import models.Com;
import models.Invoice;
import services.ComManager;
import services.CustomerManager;
import services.Exception;
import services.InvoiceManager;

import java.time.LocalTime;
import java.util.Scanner;

public class InvoiceMenu {
    Scanner inputNum = new Scanner(System.in);
    InvoiceManager invoiceManager = new InvoiceManager();
    ComManager comManager = new ComManager();
    public void mainMenu(){

        int choice;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟          QUẢN LÝ HÓA ĐƠN          ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("| 1. Hiển thị danh sách             |");
            System.out.println("| 2. Thêm hóa đơn mơi               |");
            System.out.println("| 3. Sửa đổi thông tin              |");
            System.out.println("| 4. Xóa khỏi danh sách             |");
            System.out.println("| 5. Tổng doanh thu                 |");
            System.out.println("| 0. Thoát                          |");
            System.out.println("└———————————————————————————————————┘");
            System.out.print("Chọn đi từ 1 đến 5 thôi: ");
            choice = Exception.choiceException();
            if (choice < 6) {
                switch (choice) {
                    case 1 -> invoiceManager.showAll();
                    case 2 -> NewInvoice();
                    case 3 -> {
                        System.out.println("Nhập ID hóa đơn:");
                        int findId = Exception.choiceException();
                        invoiceManager.update(findId);
                    }
                    case 4 -> {
                        System.out.println("Nhâp ID dịch vụ:");
                        int removeId = Exception.choiceException();
                        invoiceManager.deleteById(removeId);
                        System.out.println("Xóa thành công");
                    }
                    case 5 -> {
                        invoiceManager.showAll();
                        invoiceManager.sumPayment();
                    }
                }
            } else System.out.println("Có 4 cái thôi ông ơi");
        } while (choice != 0);
    }
    public void NewInvoice() {
        System.out.println("_____Thêm hóa đơn mơi____\n" +
                "Nhập ID máy muốn thanh toán: ");
        comManager.showAll();
        int comId = Exception.choiceException();
        Com com = comManager.findById(comId);
        LocalTime time = LocalTime.now();
        if (com != null){
            if (com.getCustomer() != null) {
                double totalPayment = com.getPayment() + (com.getPrice() * time.getSecond());
                if (com.getCustomer().getMoney() > totalPayment) {
                    Invoice invoice = new Invoice(com, totalPayment, time);
                    invoiceManager.add(invoice);
                    System.out.println(invoice);
                    System.out.println("Thêm thành công");
                } else {
                    System.out.println("Bắt nó nạp thêm tiền");
                    double money = inputNum.nextDouble();
                    com.getCustomer().setMoney(money);
                }
            } else {
                System.out.println("Máy chưa có người ngồi");
            }
        } else {
            System.out.println("Không tìm thấy máy");
        }
    }
}
