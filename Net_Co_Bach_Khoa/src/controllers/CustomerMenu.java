package controllers;

import models.Customer;
import services.CustomerManager;
import services.Exception;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    Scanner inputNum = new Scanner(System.in);
    Scanner inputStr = new Scanner(System.in);
    public void mainMenu() {
        CustomerManager customerManager = new CustomerManager();
        int choice;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟        QUẢN LÝ KHÁCH HÀNG         ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("| 1. Hiển thị danh sách             |");
            System.out.println("| 2. Thêm tài khoản mới             |");
            System.out.println("| 3. Sửa đổi thông tin              |");
            System.out.println("| 4. Xóa khỏi danh sách             |");
            System.out.println("| 0. Thoát                          |");
            System.out.println("└———————————————————————————————————┘");
            System.out.print("Chọn đi từ 1 đến 4 thôi: ");
            choice = Exception.choiceException();
            if (choice < 5) {
                switch (choice) {
                    case 1 -> customerManager.showAll();
                    case 2 -> {
                        System.out.println("_____Tạo tài khoản mới____\n" +
                                "Nhập tên khách hàng");
                        String name = inputStr.nextLine();
                        System.out.println("Nhập Username");
                        String username = inputStr.nextLine();
                        System.out.println("Nhập mật khẩu");
                        String password = inputStr.nextLine();
                        System.out.println("Nạp tiền nào:");
                        double money = inputNum.nextDouble();
                        Customer customer = new Customer(name, username, password, money);
                        customerManager.add(customer);
                        System.out.println(customer);
                        System.out.println("Tạo thành công");
                    }
                    case 3 -> {
                        System.out.println("Nhập ID tài khoản");
                        int findId = Exception.choiceException();
                        customerManager.update(findId);
                    }
                    case 4 -> {
                        System.out.println("Nhập ID tài khoản");
                        int removeId = Exception.choiceException();
                        customerManager.deleteById(removeId);
                        System.out.println("Xóa thành công");
                    }
                }
            } else System.out.println("Có 4 cái thôi ông ơi");
        } while (choice != 0);
    }
}
