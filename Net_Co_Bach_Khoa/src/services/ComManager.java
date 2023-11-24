package services;

import models.Com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComManager implements GeneralManager<Com>{
    Scanner inputNum = new Scanner(System.in);
    Scanner inputStr = new Scanner(System.in);
    List<Com> comList;
    private static int idIncrement = 1;

    public ComManager() {
        this.comList = readData();
    }

    @Override
    public void add(Com o) {
        o.setId(idIncrement);
        idIncrement++;
        comList.add(o);
        writeData(comList);
    }

    @Override
    public void showAll() {
        for (Com c: comList) {
            System.out.println(c);
        }
    }

    @Override
    public Com findById(int id) {
        for (int i = 0; i < comList.size(); i++) {
            if (comList.get(i).getId() == id) return comList.get(i);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Com c: comList) {
            if (c.getId() == id) comList.remove(c);
        }
        writeData(comList);
    }

    @Override
    public void update(int id) {
        System.out.println("Nhập ID máy: ");
        int findId = inputNum.nextInt();
        Com com = findById(findId);
        if (com == null) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Cập nhập thông tin");
            System.out.println("Nhập tên máy mới:");
            String name = inputStr.nextLine();
            com.setName(name);
            System.out.println("Cập nhập thành công");
        }
        writeData(comList);
    }
    public void turnOnCom(){}
    public void cashRegister(){}
    public void writeData(List<Com> comList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\comData.csv"));
            String line = "ID, Tên máy, Khách\n";
            for (Com c: comList) {
                line += c.getId() + ", "
                        + c.getName() + ", "
                        + c.getCustomer() + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Com> readData() {
        List<Com> coms = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\CodeGym\\MD2\\MD2_CaseStudy\\Net_Co_Bach_Khoa\\src\\data\\comData.csv"));
            String content = bufferedReader.readLine();
            while ((content = bufferedReader.readLine()) != null) {
                String[] value = content.split(", ");
                String name = value[1];
                Com com = new Com(name);
                coms.add(com);
                com.setId(idIncrement);
                idIncrement++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return coms;
    }
}
