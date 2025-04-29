package ap.exercises.ex4;

public class Letter {
    private String from;
    private String to;
    private String letter1;
    public Letter(String from,String to){
        this.from=from;
        this.to=to;
        this.letter1="";
    }
    public void addLine(String line){
        letter1=letter1.concat(line).concat("\n");
    }
    public String makeLetter(){
        String text="Dear "+to+":\n";
        text=text.concat(letter1);
        text=text.concat("\n Sincerely.\n");
        text=text.concat(from);
        return text;
    }
}
