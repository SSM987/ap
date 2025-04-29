package ap.exercises.ex4;

public class Main_EX4_E3_10 {
    public static void main(String[] args) {
        CashRegister cashRegister= new CashRegister();
        cashRegister.add(20);
        cashRegister.add(12.6);
        cashRegister.add(88.9);
        cashRegister.add(54.6);
        cashRegister.add(34.7);
        cashRegister.printReceipt();
    }
}
