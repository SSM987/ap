package ap.exercises.ex2;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class PacmanEngine {
     int e;
    int k;
    int c;
long start;
char[][] arr;
String fileName;
 final int maxTime=10000;
    public PacmanEngine(int k, int c) {
        this.k = k;
        this.c=c;
        this.e=0;
        this.start=System.currentTimeMillis();
        this.arr=new char[k+2][k+2];
        Fillmatrix();
        this.fileName="save_game.txt";
    }
    public void Fillmatrix(){
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
        int z = 0;
        Random random= new Random();
        while (z < c) {
            int r = random.nextInt(arr.length);
            int co = random.nextInt(arr[0].length);
            if (arr[r][co] == ' ') {
                arr[r][co] = '.';
                z++;
            }
        }
    }
    public void printMatrix() {
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }

    public void printScore() {
        System.out.println("your score is: "+e);
    }

    public void printRemainTime() {
        long elapsedTime = System.currentTimeMillis()-start;
        long finish = maxTime - elapsedTime;
        if (finish>0) {
            System.out.println("remain game:" + finish);
        }
        else
            System.out.println("time is up");
    }

     public void move(int direction) {
         for (int i = 0; i < k + 2; i++) {
             for (int j = 0; j < k + 2; j++) {
                 if (arr[i][j] == 'X') {
                     switch (direction) {
                         case 0:
                             if (arr[i - 1][j] == '*') {
                                 System.out.println("hitting the game wall");
                             } else {
                                 if(arr[i-1][j]=='.')
                                     e++;
                                 arr[i - 1][j] = 'X';
                                 arr[i][j] = ' ';
                             }
                             return;
                         case 1:
                             if (arr[i][j + 1] == '*') {
                                 System.out.println("hitting the game wall");
                             } else {
                                 if(arr[i][j+1]=='.')
                                     e++;
                                 arr[i][j + 1] = 'X';
                                 arr[i][j] = ' ';
                             }
                             return;
                         case 2:
                             if (arr[i + 1][j] == '*') {
                                 System.out.println("hitting the game wall");
                             } else {
                                 if(arr[i+1][j]=='.')
                                     e++;
                                 arr[i + 1][j] = 'X';
                                 arr[i][j] = ' ';
                             }
                             return;
                         case 3:
                             if (arr[i][j - 1] == '*') {
                                 System.out.println("hitting the game wall");
                             } else {
                                 if(arr[i][j-1]=='.')
                                     e++;
                                 arr[i][j - 1] = 'X';
                                 arr[i][j] = ' ';
                             }
                             return;
                     }
                 }

             }
         }
     }

     public void save() {
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
             System.out.println("game saved.");
         } catch (IOException e) {
             System.out.println("Error saving game: " + e.getMessage());
         }
     }
 }

    public class EX2_PM_2_4 {
        public static char[][]arr;

            public static void main(String[] args) {

                int k=9; //عدد مربوط به تمرین EX2_PM_1_1 و EX2_PM_1_2
                int c=15; //عدد مربوط به تمرین EX2_PM_1_3

                Random rnd = new Random();

                PacmanEngine pacmanEngine = new PacmanEngine(k,c);
                while(true) {
                    pacmanEngine.printMatrix(); // مربوط به بخش آخر تمرین EX2_PM_1_3
                    pacmanEngine.printScore(); // امتیاز تمرین EX2_PM_2_2
                    pacmanEngine.printRemainTime(); // زمان تمرین EX2_PM_2_2

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {}

                    int direction=rnd.nextInt(4);
                    pacmanEngine.move(direction);// حرکت نقطه خوار مربوط به تمرین EX2-PM.1.5
                    pacmanEngine.save();
                }

            }
        }
