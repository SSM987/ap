package ap.exercises.midtermexam;

public class product {
    private int price;
    private String model;
    public product(int price,String model){
        this.model=model;
        this.price=price;
    }

    @Override
    public String toString() {
        return
                "price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}
