package ap.exercises.ex2;
import java.util.Random;
import java.util.Scanner;

public class EX2_PM_1_3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        int c;
        System.out.print("Please enter a number:");
        int k = s.nextInt();
        while (true) {
            System.out.print("Please enter c:");
            c = s.nextInt();
            if (c > k * k)
                System.out.println("Please enter c correctly.");
            else
                break;
        }
        char[][] arr = new char[k + 2][k + 2];
        arr[0][0] = '*';
        arr[k + 1][k + 1] = '*';
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                if ((i == 0 && j > 0) || (i == k + 1 && j < k + 1))
                    arr[i][j] = '*';
                else if ((j > 0 && j < k + 1) && i < k + 1)
                    arr[i][j] = ' ';
                else
                    arr[i][j] = '*';
            }
        }
        int z = 0;
        while (z < c) {
            int r = random.nextInt(arr.length);
            int co = random.nextInt(arr[0].length);
            if (arr[r][co] == ' ') {
                arr[r][co] = '.';
                z++;
            }
        }
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
        s.close();
    }
}

