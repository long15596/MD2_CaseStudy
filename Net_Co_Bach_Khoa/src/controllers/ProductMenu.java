package controllers;

import models.Product;
import services.Exception;
import services.ProductManager;

import java.util.Scanner;

public class ProductMenu {
    Scanner inputStr = new Scanner(System.in);
    Scanner inputNum = new Scanner(System.in);
    public void mainMenu() {
        ProductManager productManager = new ProductManager();
        int choice;
        do {
            System.out.println("┌———————————————————————————————————┐");
            System.out.println("⎟          QUẢN LÝ DỊCH VỤ          ⎟");
            System.out.println("⎟———————————————————————————————————⎟");
            System.out.println("| 1. Hiển thị danh sách             |");
            System.out.println("| 2. Thêm dịch vụ mơi               |");
            System.out.println("| 3. Sửa đổi thông tin              |");
            System.out.println("| 4. Xóa máy khỏi danh sách         |");
            System.out.println("| 0. Thoát                          |");
            System.out.println("└———————————————————————————————————┘");
            System.out.print("Chọn đi từ 1 đến 4 thôi: ");
            choice = Exception.choiceException();
            if (choice < 5) {
                switch (choice) {
                    case 1 -> productManager.showAll();
                    case 2 -> {
                        System.out.println("_____Thêm dịch vụ mới____\n" +
                                "Nhập tên dịch vụ: ");
                        String name = inputStr.nextLine();
                        System.out.println("Nhập Giá: ");
                        double price = inputNum.nextDouble();
                        System.out.println("Nhập số lượng");
                        int quantity = inputNum.nextInt();
                        productManager.add(new Product(name, price, quantity));
                        System.out.println("Thêm thành công");
                    }
                    case 3 -> {
                        System.out.println("Nhập ID dịch vụ:");
                        int findId = Exception.choiceException();
                        productManager.update(findId);
                    }
                    case 4 -> {
                        System.out.println("Nhâp ID dịch vụ:");
                        int removeId = Exception.choiceException();
                        productManager.deleteById(removeId);
                        System.out.println("Xóa thành công");
                    }
                }
            } else System.out.println("Có 4 cái thôi ông ơi");
        } while (choice != 0);
    }
}
