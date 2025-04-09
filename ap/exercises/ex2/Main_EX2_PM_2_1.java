package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_2_1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter a number:");
        int k = s.nextInt();
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
        arr[1][k] = 'X';
        PrintMatrix(arr);
        System.out.println(" choose one of these character to move X('0' to up,'1' to right,'2' to down,'3' to left)");
        while (true) {
            System.out.print(" Please Enter your character:");
            byte number = s.nextByte();
            switch (number) {
                case 0:
                    System.out.println("UP");
                    break;
                case 1:
                    System.out.println("RIGHT");
                    break;
                case 2:
                    System.out.println("DOWN");
                    break;
                case 3:
                    System.out.println("LEFT");
                    break;
            }
           if (MoveX(arr, number, k)) {
               PrintMatrix(arr);
           }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void PrintMatrix(char[][] arr) {
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static boolean MoveX(char[][] arr, byte number, int k) {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                if (arr[i][j] == 'X') {
                    switch (number) {
                        case 0:
                            if (arr[i - 1][j] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                arr[i - 1][j] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case 1:
                            if (arr[i][j + 1] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                arr[i][j + 1] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case 2:
                            if (arr[i + 1][j] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                arr[i + 1][j] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case 3:
                            if (arr[i][j - 1] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                arr[i][j - 1] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                    }
                }

            }
        }
        return false;
    }
}
