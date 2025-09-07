package ap.exercises.Finalexam;

public class Book extends Product {
    private String title;
    private String author;
    private String name;
    private int fee;
    public Book(String title, String author, String name, String fee){
        super(name,fee);
        this.author=author;
        this.title=title;
    }
    @Override
    public String toString() {
        return "Book: " + getName() + " - " + getFee() + " (" + title + ", " + author + ")";
    }
}
