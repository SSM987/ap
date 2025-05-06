package ap.exercises;

import java.util.ArrayList;

class Book{
    private String name;
    private  int price;
    private int off;
    public Book(String name,int price,int off){
        this.name=name;
        this.price=price;
        this.off=off;
    }
    public void ShowBookInfo(){
        System.out.println(this.name+"\t\t"+(this.price-(this.price/off)));
    }
}
class Pen{
    private String color;
    private int price;
    private String brand;
    private int off;
    public Pen(String color,int price,String brand,int off){
        this.brand=brand;
        this.price=price;
        this.color=color;
        this.off=off;
    }
    public void ShowPenInfo(){
        System.out.println(this.brand+"\t\t"+this.color+"\t\t"+(this.price-(this.price/off)));
    }
}

public class Main {
    public static void main(String[] args) {
        Book book1= new Book("Hello",234,10);
        Book book2= new Book("bye",2654,20);
        Pen pen1= new Pen("Red",1234,"kavir",12);
        Pen pen2= new Pen("Blue",1276,"mountain",30);
        ArrayList<Pen>penlist=new ArrayList<>();
        ArrayList<Book>bookList= new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        penlist.add(pen1);
        penlist.add(pen2);
        for (Pen pen:penlist)
            pen.ShowPenInfo();
        for (Book book:bookList)
            book.ShowBookInfo();
    }
}
