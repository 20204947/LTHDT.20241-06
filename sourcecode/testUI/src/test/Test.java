package test;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

   //     System.out.println("Nhập ký tự bất kỳ để tạo số ngẫu nhiên. Nhập 'exit' để dừng chương trình.");

 //       while (true) {
  //          String input = scanner.nextLine();

            // Kiểm tra nếu người dùng nhập "exit" thì thoát chương trình
//            if ("exit".equalsIgnoreCase(input)) {
//                System.out.println("Đã dừng chương trình.");
//                scheduler.shutdown();
//                break;
//            }

            // Đặt tác vụ để chạy sau 3 giây
        scheduler.schedule(() -> {
            int randomNumber1 = random.nextInt(5);
            int randomNumber2 = random.nextInt(1);
            System.out.println("Số ngẫu nhiên 1: " + randomNumber1);
            System.out.println("Số ngẫu nhiên 2: " + randomNumber2);
        }, 3, TimeUnit.SECONDS);
 //       }
    }
}
