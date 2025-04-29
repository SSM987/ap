package ap.exercises.ex4;

public class Main_EX4_E3_15 {
    public static void main(String[] args) {
        Letter letter = new Letter("Marry", "John");
        letter.addLine("I am so sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.makeLetter());
    }
}
