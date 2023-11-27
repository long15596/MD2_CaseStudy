package controllers;

import models.Com;
import models.Customer;
import models.Invoice;
import models.Product;
import services.*;
import services.Exception;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class UserMenu {
    Scanner inputStr = new Scanner(System.in);
    Scanner inputNum = new Scanner(System.in);
    ComManager comManager = new ComManager();
    ChatSocket chatSocket = new ChatSocket();
    CustomerManager customerManager = new CustomerManager();
    ProductManager productManager = new ProductManager();
    InvoiceManager invoiceManager = new InvoiceManager();
    public void mainMenu() {
        while (true){
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟          NÉT CỎ BÁCH KHOA         ⎟");
            System.out.println("|___________________________________|");
            comManager.showAll();
            System.out.println("Nhập ID máy muốn vào: ");
            int findId = Exception.choiceException();
            Com com = comManager.findById(findId);
            if (com != null) {
                System.out.println("┌———————————————————————————————————┐");
                System.out.println("⎟        ĐĂNG NHẬP TÀI KHOẢN        ⎟");
                System.out.println("|___________________________________|");
                System.out.print("Username: ");
                String username = inputStr.nextLine();
                System.out.print("Password: ");
                String password = inputStr.nextLine();
                for (Customer c: customerManager.readData()) {
                    if (c.getUsername().equals(username) && c.getPassword().equals(password)){
                        com.setCustomer(c);
                        System.out.println(com);
                        LocalTime statTime = LocalTime.now();
                        int choice;
                        do {
                            System.out.println("┌———————————————————————————————————┐");
                            System.out.println("⎟          MÁY " + com.getName() + "                ⎟");
                            System.out.println("⎟———————————————————————————————————⎟");
                            System.out.println("| 1. Thêm dịch vụ                   |");
                            System.out.println("| 2. Tâm sự với chủ quán            |");
                            System.out.println("| 3. Thanh toán                     |");
                            System.out.println("| 0. Thoát                          |");
                            System.out.println("└———————————————————————————————————┘");
                            System.out.println("Chú em còn: " + c.getMoney() + " trong ví");
                            System.out.print("Thích gì chọn đi: ");
                            choice = Exception.choiceException();
                            switch (choice){
                                case 1 -> {
                                    System.out.println("THích gì chọn đi em");
                                    productManager.showAll();
                                    System.out.print("Chọn đồ gì: ");
                                    int productId = Exception.choiceException();
                                    Product product = productManager.findById(productId);
                                    System.out.print("Nhập số lượng: ");
                                    int buyQuantity = Exception.choiceException();
                                    if (product.getQuantity() > buyQuantity) {
                                        com.setPayment(product.getPrice() * buyQuantity);
                                        product.setQuantity(product.getQuantity() - buyQuantity);
                                        System.out.println("OK bấy bề ship luôn");
                                    } else {
                                        System.out.println("Mua đ lắm thế");
                                    }
                                }
                                case 2 -> chatSocket.client();
                                case 3 -> {
                                    LocalTime endTime = LocalTime.now();
                                    Duration duration = Duration.between(statTime, endTime);
                                    double paymentCom = duration.getSeconds() * com.getPrice();
                                    double totalPayment = com.getPayment() + paymentCom;
                                    System.out.println("Chơi hết " + duration.getSeconds() + " giờ thành tiên " + paymentCom);
                                    if (c.getMoney() < totalPayment) {
                                        System.out.println("Tài khoản đ đủ tiền rồi cháu ơi nạp thêm tiền đi");
                                        double money = inputNum.nextDouble();
                                        c.setMoney(money);
                                        System.out.println("Nạp thành công");
                                    } else {
                                        com.setPayment(totalPayment);
                                        c.setMoney(c.getMoney() - totalPayment);
                                        customerManager.writeData(customerManager.getCustomerList());
                                        Invoice invoice = new Invoice(com, totalPayment, endTime);
                                        invoiceManager.add(invoice);
                                        System.out.println(invoice);
                                        mainMenu();
                                    }
                                }
                            }
                        }while (choice != 0);
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        UserMenu userMenu = new UserMenu();
        userMenu.mainMenu();
    }
}
