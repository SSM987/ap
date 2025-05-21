package ap.exercises.midtermexam;

public class lapTop extends product {
    private int price;
    private String model;
    private int fps;
    private String cpu;
    public lapTop(int price,String model,int fps,String cpu){
        super(price,model);
        this.cpu=cpu;
        this.fps=fps;
    }

    @Override
    public String toString() {
        return "lapTop{" +
               super.toString()+
                ", fps=" + fps +
                ", cpu='" + cpu + '\'' +
                '}';
    }
}
