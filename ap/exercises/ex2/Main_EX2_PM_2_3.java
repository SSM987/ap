package ap.exercises.ex2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_3 {
     public static int e = 0;
     public static String fileName = "pack_man_save.txt";
     public static int k;
      public static char [][]arr;
   public static int c;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        String ch;
        File saveFile= new File(fileName);
        long start = System.currentTimeMillis();
        if(saveFile.exists()){
            loadGame();
        }
        else {
            System.out.print("Please enter a number:");
            k = s.nextInt();
            while (true) {
                System.out.print("Please enter c:");
                c = s.nextInt();
                if (c > k * k)
                    System.out.println("Please enter c correctly.");
                else
                    break;
            }
            FillMatrix();
            int z = 0;
            while (z < c) {
                int r = random.nextInt(arr.length);
                int co = random.nextInt(arr[0].length);
                if (arr[r][co] == ' ') {
                    arr[r][co] = '.';
                    z++;
                }
            }
        }
        PrintMatrix();
        System.out.println(" choose one of these character to move X('w' to up,'d' to right,'s' to down,'a' to left,'1' to save game,'2' to exit)");
        while (true) {
            System.out.print(" Please Enter your character:");
            ch = s.next();
            switch (ch) {
                case "w":
                    System.out.println("UP");
                    break;
                case "d":
                    System.out.println("RIGHT");
                    break;
                case "s":
                    System.out.println("DOWN");
                    break;
                case "a":
                    System.out.println("LEFT");
                    break;
                case "1":
                    SaveGame(k,c,arr);
                    System.out.println("Game saved successfully.");
                    break;
                case "2":
                    SaveGame(k,c,arr);
                    System.out.println("Exit from game.");
                    return;
            }
            if (MoveX(ch)) {
                PrintMatrix();
            }
            if (e == c) {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("The duration of the game:" + timeElapsed);
                System.out.println("Your score is:" + c);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        s.close();
    }

    public static void PrintMatrix() {
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    public static boolean MoveX( String ch) {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                if (arr[i][j] == 'X') {
                    switch (ch) {
                        case "w":
                            if (arr[i - 1][j] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                if(arr[i-1][j]=='.')
                                    e++;
                                arr[i - 1][j] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case "d":
                            if (arr[i][j + 1] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                if(arr[i][j+1]=='.')
                                    e++;
                                arr[i][j + 1] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case "s":
                            if (arr[i + 1][j] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                if(arr[i+1][j]=='.')
                                    e++;
                                arr[i + 1][j] = 'X';
                                arr[i][j] = ' ';
                                return true;
                            }
                            return false;
                        case "a":
                            if (arr[i][j - 1] == '*') {
                                System.out.println("hitting the game wall");
                            } else {
                                if(arr[i][j-1]=='.')
                                    e++;
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
    public static void SaveGame(int k, int c, char[][] arr) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(k + "\n");
            writer.write(c + "\n");
            writer.write(e + "\n");
            for (int i = 0; i < k + 2; i++) {
                for (int j = 0; j < k + 2; j++) {
                    writer.write(arr[i][j]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
             k = Integer.parseInt(reader.readLine().trim());
             c = Integer.parseInt(reader.readLine().trim());
            e = Integer.parseInt(reader.readLine().trim());
            arr = new char[k + 2][k + 2];
            for (int i = 0; i < k + 2; i++) {
                String line = reader.readLine();
                for (int j = 0; j < k + 2; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }
            System.out.println("Game loaded!");
            PrintMatrix();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
    public static void FillMatrix(){
        arr=new char[k+2][k+2];
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
    }
}


