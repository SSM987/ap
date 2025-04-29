package ap.exercises.ex4;

import java.util.ArrayList;

public class CashRegister {
    private ArrayList<Double> price;

    public CashRegister(){
        price=new ArrayList<>();
    }
    public void add(double prices){
        price.add(prices);
    }
    public void printReceipt() {
        String receipt= "Receipt:\t";
        double totalAmount=0;
        for (double prices : price) {
            receipt = receipt.concat(String.valueOf(prices)) + "\t";
            totalAmount += prices;
        }
        receipt+="\n"+"Total amount:"+ totalAmount;
        System.out.println(receipt);
    }
}
