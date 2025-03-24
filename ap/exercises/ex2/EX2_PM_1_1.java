package ap.exercises.ex2;

import java.util.Scanner;

public class EX2_PM_1_1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter a number:");
        int k = s.nextInt();
        for(int i = 0; i<k+2; i++)
            System.out.print('*');
        System.out.println();
        for(int i=0;i<k;i++) {
            for (int j = 0; j < k + 3; j++) {
                if (j == 0 || j == k + 2)
                    System.out.print('*');
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        for(int i = 0; i<k+2; i++)
            System.out.print('*');
        s.close();
    }
}
