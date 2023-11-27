package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatSocket {
    public void server() {
        int portNum = 12345;
        boolean check = true;
        try(ServerSocket serverSocket = new ServerSocket(portNum)) {
            System.out.println("Luôn luôn lắng nghe lâu lâu mới hiểu");
            while (check){
                try(Socket clientSocket = serverSocket.accept();
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()) {
                    System.out.println("Có khứa muốn tâm sự: " + clientSocket.getInetAddress());
                    new Thread(()-> {
                        Scanner scanner = new Scanner(input);
                        while (scanner.hasNextLine()) {
                            String clientMess = scanner.nextLine();
                            System.out.println("Khứa: " + clientMess);
                        }
                    }).start();
                    Scanner consoleScanner = new Scanner(System.in);
                    while (check) {
                        String serverMess = consoleScanner.nextLine();
                        output.write((serverMess + "\n").getBytes());
                        if (serverMess.equals("exit")) {
                            check = false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void client() {
        String serverAddress = "localhost";
        int serverPost = 12345;
        try (Socket socket = new Socket(serverAddress, serverPost);
             InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            new Thread(() -> {
                Scanner scanner = new Scanner(input);
                while (scanner.hasNextLine()) {
                    String serverMess = scanner.nextLine();
                    System.out.println("Anh chủ quán: " + serverMess);
                }
            }).start();
            Scanner consoleScanner = new Scanner(System.in);
            while (true) {
                String clientMess = consoleScanner.nextLine();
                output.write((clientMess + '\n').getBytes());
                if (clientMess.equals("exit")) break;
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
