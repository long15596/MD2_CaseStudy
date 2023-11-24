package services;

import java.util.Scanner;

public class Exception {
    static Scanner sc = new Scanner(System.in);
   public static int choiceException() {
       int choice = -1;
       do {
           try {
               choice = sc.nextInt();
               if (choice < 0) {
                   choice = -1;
                   System.out.println("Nhập số phải lớn hơn không chứ");
               }
           } catch (NumberFormatException exception) {
               System.out.println("Nhập số nhé bạn ơi");
           }
       } while (choice == -1);
       return choice;
   }
}
