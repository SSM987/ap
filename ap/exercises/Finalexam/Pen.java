package ap.exercises.Finalexam;

public class Pen extends Product{
    private String name;
    private int fee;
   private Color color;
    public Pen(String name, String fee, Color color){
        super(name,fee);
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Pen: " + getName() + " - " + getFee() + " (" + color + ")";
    }
}

