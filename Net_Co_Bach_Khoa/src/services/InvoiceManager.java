package services;

import models.Com;
import models.Customer;
import models.Invoice;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceManager implements GeneralManager<Invoice> {
    Scanner inputStr = new Scanner(System.in);
    Scanner inputNum = new Scanner(System.in);
    List<Invoice> invoiceList;

    public InvoiceManager() {
        this.invoiceList = readData();
    }

    @Override
    public void add(Invoice o) {
        invoiceList.add(o);
        writeData(invoiceList);
    }

    @Override
    public void showAll() {
        for (Invoice i: invoiceList) System.out.println(i);
    }

    @Override
    public Invoice findById(int id) {
        for (Invoice i: invoiceList){
            if (i.getId() == id) return i;
        }
        return null;
    }
    @Override
    public void deleteById(int id) {
        invoiceList.removeIf(i -> i.getId() == id);
        writeData(invoiceList);
    }

    @Override
    public void update(int id) {
        System.out.print("Nhập ID Hóa Đơn: ");
        int findId = inputNum.nextInt();
        Invoice invoice = findById(findId);
        if(invoice == null) {
            System.out.println("Không tìm thấy");
        } else {
            int choice;
            do {
                System.out.println("┌———————————————————————————————————┐");
                System.out.println("⎟      CHỌN THÔNG TIN MUỐN SỬA      ⎟");
                System.out.println("⎟———————————————————————————————————⎟");
                System.out.println("| 1. Số tiền                        |");
                System.out.println("| 2. Ngày xuất hóa đơn              |");
                System.out.println("| 0. Thoát                          |");
                System.out.println("└———————————————————————————————————┘");
                System.out.println("Chọn đi bạn");
                choice = Exception.choiceException();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhập số tiền muốn sửa:");
                        double payment = inputNum.nextDouble();
                        invoice.setPayment(payment);
                        System.out.println("Cập nhập thành công");
                    }
                    case 2 -> {
                        System.out.println("Nhập lại ngày mới");
                        String date = inputStr.nextLine();
                        invoice.setDate(LocalTime.parse(date));
                        System.out.println("Cập nhập thành công");
                    }
                }
            } while (choice != 0);
        }
        writeData(invoiceList);
    }
    public double sumPayment() {
        double sum = 0;
        for (Invoice i: invoiceList) {
            sum += i.getPayment();
        }
        return sum;
    }
    public void writeData(List<Invoice> invoiceList){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\invoiceData.csv"));
            String line = "ID, Máy, Tên Khách, Tiền, Giờ ra \n";
            for (Invoice i: invoiceList){
                line += i.getId() + ", "
                        + i.getCom().getName() + ", "
                        + i.getCom().getCustomer().getName() + ", "
                        + i.getPayment() + ", "
                        + i.getDate() + '\n';
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Invoice> readData() {
        ComManager comManager = new ComManager();
        List<Invoice> invoices = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\invoiceData.csv"));
            String content = bufferedReader.readLine();
            while ((content = bufferedReader.readLine()) != null) {
                String[] value = content.split(", ");
                int id = Integer.parseInt(value[0]);
                String comName = value[1];
                Com com = comManager.findByName(comName);
                double payment = Double.parseDouble(value[3]);
                LocalTime time = LocalTime.parse(value[4]);
                Invoice invoice = new Invoice(com, payment, time);
                invoice.setId(id);
                invoices.add(invoice);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return invoices;
    }
}
