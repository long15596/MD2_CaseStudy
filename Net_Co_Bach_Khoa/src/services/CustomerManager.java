package services;

import models.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager implements GeneralManager<Customer>{
    Scanner inputNum = new Scanner(System.in);
    Scanner inputStr = new Scanner(System.in);
    List<Customer> customerList;

    public CustomerManager() {
        this.customerList = readData();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public void add(Customer o) {
        customerList.add(o);
        writeData(customerList);
    }

    @Override
    public void showAll() {
        for (Customer c: customerList) {
            System.out.println(c);
        }
    }

    @Override
    public Customer findById(int id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) return customerList.get(i);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) customerList.remove(customerList.get(i));
        }
        writeData(customerList);
    }
    public Customer findByName(String name){
        for (Customer c: customerList){
            if (c.getName().contains(name)) return c;
        }
        return null;
    }
    @Override
    public void update(int id) {
        System.out.print("Nhập ID khách hàng: ");
        int findId = inputNum.nextInt();
        Customer customer = findById(findId);
        if(customer == null) {
            System.out.println("Không tìm thấy");
        } else {
            int choice;
            do {
                System.out.println("┌———————————————————————————————————┐");
                System.out.println("⎟      CHỌN THÔNG TIN MUỐN SỬA      ⎟");
                System.out.println("⎟———————————————————————————————————⎟");
                System.out.println("| 1. Tên                            |");
                System.out.println("| 2. Username                       |");
                System.out.println("| 3. Password                       |");
                System.out.println("| 4. Nạp thêm tiền                  |");
                System.out.println("| 0. Thoát                          |");
                System.out.println("└———————————————————————————————————┘");
                System.out.println("Chọn đi bạn");
                choice = Exception.choiceException();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhập tên mới:");
                        String newName = inputStr.nextLine();
                        customer.setName(newName);
                        System.out.println("Cập nhập thành công");
                    }
                     case 2 -> {
                         System.out.println("Nhập Username mới");
                         String username = inputStr.nextLine();
                         customer.setUsername(username);
                         System.out.println("Cập nhập thành công");
                    }
                    case 3 -> {
                        System.out.println("Nhập Password mới");
                        String password = inputStr.nextLine();
                        customer.setPassword(password);
                        System.out.println("Cập nhập thành công");
                    }
                    case 4 -> {
                        System.out.println("Nhập Số Tiền");
                        double money = inputNum.nextDouble();
                        customer.setMoney(money);
                        System.out.println("Cập nhập thành công");
                    }
                }
            } while (choice != 0);
        }
        writeData(customerList);
    }
    public void writeData(List<Customer> customerList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\customerData.csv"));
            String line = "ID, Tên, Username, Password, Tiền\n";
            for (Customer c: customerList) {
                line += c.getId() + ", "
                        + c.getName() + ", "
                        + c.getUsername() + ", "
                        + c.getPassword() + ", " +
                        + c.getMoney() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Customer> readData() {
        List<Customer> customers = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\customerData.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String content = bufferedReader.readLine();
            while ((content = bufferedReader.readLine()) != null) {
                String[] value = content.split(", ");
                int id = Integer.parseInt(value[0]);
                String name = value[1];
                String username = value[2];
                String password = value[3];
                double money = Double.parseDouble(value[4]);
                Customer customer = new Customer(name, username, password, money);
                customer.setId(id);
                customers.add(customer);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
