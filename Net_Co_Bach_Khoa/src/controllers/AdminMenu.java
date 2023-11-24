package controllers;

import models.Com;
import services.ComManager;
import services.Exception;

import java.util.Scanner;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    private final String username = "admin";
    private final String password = "admin";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void mainMenu() {
        ComManager comManager = new ComManager();
        ProductMenu productMenu = new ProductMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        InvoiceMenu invoiceMenu = new InvoiceMenu();
        int choice;
        do {

            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟          NÉT CỎ BÁCH KHOA         ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("| 1. Hiển thị danh sách máy         |");
            System.out.println("| 2. Thêm máy mới                   |");
            System.out.println("| 3. Sửa đổi thông tin máy          |");
            System.out.println("| 4. Xóa máy khỏi danh sách         |");
            System.out.println("| 5. Quản lý dịch vụ                |");
            System.out.println("| 6. Tính tiền                      |");
            System.out.println("| 7. Quản lý tài khoản khách        |");
            System.out.println("| 8. Quản lý doanh thu              |");
            System.out.println("| 0. Thoát                          |");
            System.out.println("└———————————————————————————————————┘");
            System.out.print("Chọn đi bạn tôi từ 1 đến 8 thôi nhé: ");
            choice = Exception.choiceException();
            if (choice < 9) {
                switch (choice) {
                    case 1 -> comManager.showAll();
                    case 2 -> {
                        System.out.println("_____Thêm máy mới____\n" +
                                "Nhập tên máy: ");
                        String name = input.nextLine();
                        Com com = new Com(name);
                        comManager.add(com);
                        System.out.println("Thêm thành công");
                    }
                    case 3 -> {
                        System.out.print("Nhập ID máy muốn sửa: ");
                        int findId = Exception.choiceException();
                        comManager.update(findId);
                    }
                    case 4 -> {
                        System.out.println("Nhập ID máy muốn xóa");
                        int removeId = Exception.choiceException();
                        comManager.deleteById(removeId);
                        System.out.println("Xóa thành công");
                    }
                    case 5 -> productMenu.mainMenu();
                    case 6 -> comManager.cashRegister();
                    case 7 -> customerMenu.mainMenu();
                    case 8 -> invoiceMenu.mainMenu();
                }
            } else {
                System.out.println("Có 9 cái để chọn ngáo à");
            }
        } while (choice != 0);
    }
}
