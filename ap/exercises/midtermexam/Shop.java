package ap.exercises.midtermexam;

import java.util.ArrayList;

public class Shop {
    private  ArrayList<lapTop>l1=new ArrayList<>();
    private  ArrayList<Case>c1=new ArrayList<>();
  public void addCase(Case ca1){
      c1.add(ca1);
  }
  public void addLaptop(lapTop lapTop){
      l1.add(lapTop);
  }
    public void showInfo(){
        for(lapTop lapTop : l1)
            System.out.println(lapTop);
        for(Case ca : c1)
            System.out.println(ca);
    }
}
