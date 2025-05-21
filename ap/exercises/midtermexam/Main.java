package ap.exercises.midtermexam;

public class Main {
    public static void main(String[] args) {
Shop shop = new Shop();
    lapTop l1= new lapTop(1,"2",3,"4");
    lapTop l2= new lapTop(5,"6",7,"8");
    Case case1= new Case(9,"10","11",12);
    Case case2= new Case(13,"14","15",16);
    shop.addCase(case1);
    shop.addCase(case2);
    shop.addLaptop(l2);
    shop.addLaptop(l1);
    shop.showInfo();
    }
}
