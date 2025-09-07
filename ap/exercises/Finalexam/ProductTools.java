package ap.exercises.Finalexam;

import java.util.*;
import java.util.stream.Collectors;

public class ProductTools {
    public static void RemoveDuplicateAndPrintProductWithFee(List<Product>products){
        products.stream()
                .distinct()
                .sorted(
                        Comparator.comparingInt(Product::getFeeAsInt)
                                .thenComparing(p -> p.getClass().getSimpleName())
                )
                .forEach(System.out::println);
    }
    public static Product[] MostExpensivePenAndBook(List<Product>products){
        Product mostExpensiveBook = products.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Book"))
                .max(Comparator.comparingInt(p -> Integer.parseInt(p.getFee())))
                .orElse(null);

        Product mostExpensivePen = products.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Pen"))
                .max(Comparator.comparingInt(p -> Integer.parseInt(p.getFee())))
                .orElse(null);

        return new Product[]{mostExpensiveBook, mostExpensivePen};
    }
    public static Map<Color,Long> NumberOfPenByColor(List<Pen>pens){
        return pens.stream()
                .collect(Collectors.groupingBy(
                        Pen::getColor,
                        Collectors.counting()
                ));

    }
}
