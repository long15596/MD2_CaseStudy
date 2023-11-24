package services;

import models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements GeneralManager<Product>{
    Scanner inputNum = new Scanner(System.in);
    Scanner inputStr = new Scanner(System.in);
    List<Product> products;
    private static int idIncrement = 1;

    public ProductManager() {
        this.products = readData();
    }

    @Override
    public void add(Product o) {
        o.setId(idIncrement);
        idIncrement++;
        products.add(o);
        writeData(products);
    }

    @Override
    public void showAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    @Override
    public Product findById(int id) {
        for (Product p: products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Product p: products) {
            if (p.getId() == id) products.remove(p);
        }
        writeData(products);
    }

    @Override
    public void update(int id) {
        System.out.println("Nhập Id dịch vụ: ");
        int findId = inputNum.nextInt();
        Product product = findById(findId);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm");
        } else {
            int choice;
            do {
                System.out.println("┌———————————————————————————————————┐");
                System.out.println("⎟      CHỌN THÔNG TIN MUỐN SỬA      ⎟");
                System.out.println("⎟———————————————————————————————————⎟");
                System.out.println("| 1. Tên                            |");
                System.out.println("| 2. Giá                            |");
                System.out.println("| 3. Số lượng                       |");
                System.out.println("| 0. Thoát                          |");
                System.out.println("└———————————————————————————————————┘");
                System.out.print("Nhập vào lựa chọn: ");
                choice = Exception.choiceException();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Nhập Tên Mới:");
                        String newName = inputStr.nextLine();
                        product.setName(newName);
                        System.out.println("Cập nhập thành công");
                    }
                    case 2 -> {
                        System.out.print("Nhập Giá Mới: ");
                        double newPrice = inputNum.nextDouble();
                        product.setPrice(newPrice);
                        System.out.println("Cập nhập thành công");
                    }
                    case 3 -> {
                        System.out.print("Nhập Số Lượng Mới: ");
                        int newQuantity = inputNum.nextInt();
                        product.setQuantity(newQuantity);
                        System.out.println("Cập nhập thành công");
                    }
                }
            } while (choice != 0);
        }
        writeData(products);
    }
    public double productPayment(int byQuantity){
        showAll();
        System.out.print("Nhập ID dịch vụ: ");
        int findID = Exception.choiceException();
        Product product = findById(findID);
        return product.getPrice() * byQuantity;
    }
    public void writeData(List<Product> productList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\productData.csv"));
            String line = "ID, Tên, Giá, Số lượng\n";
            for (Product p: productList) {
                line += p.getId() + ", "
                        + p.getName() + ", "
                        + p.getPrice() + ", " +
                        + p.getQuantity() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Product> readData() {
        List<Product> productList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\productData.csv"));
            String content = bufferedReader.readLine();
            while ((content = bufferedReader.readLine()) != null) {
                String[] value = content.split(", ");
                String name = value[1];
                double price = Double.parseDouble(value[2]);
                int quantity = Integer.parseInt(value[3]);
                Product product = new Product(name, price, quantity);
                productList.add(product);
                product.setId(idIncrement);
                idIncrement++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
}
