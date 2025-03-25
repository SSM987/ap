package ap.exercises.ex2;

import java.util.Scanner;

public class EX2_PM_1_4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a character:");
    while (true){
        String c = s.next();
        switch (c){
            case "w":
                System.out.println("UP");
                break;
            case "a":
                System.out.println("LEFT");
                break;
            case "s":
                System.out.println("DOWN");
                break;
            case "d":
                System.out.println("RIGHT");
                break;
            case "q":
                System.out.println("EXIT");
                break;
            default:
                System.out.println("WARNING");
        }
        }
    }
}
