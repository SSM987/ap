package ap.exercises.Finalexam;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Book("Java", "Smith", "JavaBook", "200"),
                new Pen("BluePen", "200", Color.BLUE),
                new Book("Python", "Smith", "Java","100"),
                new Pen("RedPen", "50", Color.RED)
        );
        List<Pen> pens = List.of(
                new Pen("BluePen1", "100", Color.BLUE),
                new Pen("BluePen2", "200", Color.BLUE),
                new Pen("RedPen", "150", Color.RED),
                new Pen("GreenPen", "120", Color.Green),
                new Pen("BlackPen", "180", Color.Black)
        );


        ProductTools.RemoveDuplicateAndPrintProductWithFee(products);
        Product[] result = ProductTools.MostExpensivePenAndBook(products);

        System.out.println("Most Expensive Book: " + result[0]);
        System.out.println("Most Expensive Pen: " + result[1]);
        Map<Color, Long> penCount = ProductTools.NumberOfPenByColor(pens);

        System.out.println(penCount);

    }
}
