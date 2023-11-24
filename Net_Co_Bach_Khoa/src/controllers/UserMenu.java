package controllers;

import models.Com;
import models.Customer;
import models.Product;
import services.ComManager;
import services.CustomerManager;
import services.Exception;
import services.ProductManager;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class UserMenu {
    Scanner inputStr = new Scanner(System.in);
    public void mainMenu() {
        ComManager comManager = new ComManager();
        CustomerManager customerManager = new CustomerManager();
        ProductManager productManager = new ProductManager();
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
            String name = inputStr.nextLine();
            System.out.print("Password: ");
            String password = inputStr.nextLine();
            for (Customer c: customerManager.getCustomerList()) {
                if (name.equals(c.getName()) && password.equals(c.getPassword())){
                    com.setCustomer(c);
                    System.out.println(com);
                    LocalTime statTime = LocalTime.now();
                    int choice;
                    do {
                        System.out.println("┌———————————————————————————————————┐");
                        System.out.println("⎟          MÁY " + com.getName() + "                ⎟");
                        System.out.println("⎟———————————————————————————————————⎟");
                        System.out.println("| 1. Thêm dịch vụ                   |");
                        System.out.println("| 2. Thanh toán                     |");
                        System.out.println("| 0. Thoát                          |");
                        System.out.println("└———————————————————————————————————┘");
                        System.out.print("Thích gì chọn đi: ");
                        choice = Exception.choiceException();
                        switch (choice){
                            case 1 -> {
                                System.out.println("THích gì chọn đi em");
                                productManager.showAll();
                                System.out.print("Chọn đồ gì: ");
                                int productId = Exception.choiceException();
                                System.out.print("Nhập số lượng: ");
                                int byQuantity = Exception.choiceException();
                                Product product = productManager.findById(productId);
                                com.setPayment(product.getPrice() * byQuantity);
                                System.out.println("OK bấy bề ship luôn");
                            }
                            case 2 -> {
                                LocalTime endTime = LocalTime.now();
                                Duration duration = Duration.between(statTime, endTime);
                                double paymentCom = duration.getSeconds() * com.getPrice();
                                System.out.println("Chơi hết " + duration.getSeconds() + " giờ thành tiên " + paymentCom);
                                com.setPayment(com.getPayment() + paymentCom);
                                System.out.println("Của cháu hết " +  com.getPayment() + " nhé");
                                mainMenu();
                                return;
                            }
                        }
                    }while (choice != 0);
                }
            }
        } else {
            System.out.println("Làm đ có máy đấy");
            mainMenu();
        }
    }

    public static void main(String[] args) {
        UserMenu userMenu = new UserMenu();
        userMenu.mainMenu();
    }
}
