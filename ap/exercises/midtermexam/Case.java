package ap.exercises.midtermexam;

public class Case extends product{
    private int price;
    private String model;
    private String color;
    private int year;
    public Case(int price,String model,String color,int year){
        super(price,model);
        this.color=color;
        this.year=year;
    }

    @Override
    public String toString() {
        return "Case{" +
              super.toString()+
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }
}
