package ap.exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Loan{
    String bookname;
    int loanDate;
    int studentNumber;
    public Loan(String bookname,int loanDate,int studentNumber){
        this.bookname=bookname;
        this.studentNumber=studentNumber;
        this.loanDate=loanDate;
    }
    public void DisplayLoanInfo(){
        System.out.println("Bookname:"+bookname+"    "+"Loan date:"+loanDate+"    "+"Student ID:"+studentNumber);
    }
}
public class EX3_LM_2_1 {
    public static void SaveTOFile(Loan[] loan){
        try(FileWriter fileWriter = new FileWriter("Loan.txt")){
            for(Loan loans:loan)
                fileWriter.write(loans.bookname+"    "+loans.loanDate+"    "+loans.studentNumber+"\n");
            System.out.println("Loans information saved to file successfully");
        }catch (IOException e){e.printStackTrace();}
    }
    public static Loan[] LoadFromFile(){
        Loan[]loans= new Loan[4];
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader("Loan.txt"))){
            String string;
            int i=0;
            while ((string=bufferedReader.readLine())!=null){
                String[] detail=string.split(" {4}");
                loans[i]= new Loan(detail[0],Integer.parseInt(detail[1]),Integer.parseInt(detail[2]));
                i++;
            }
        }catch (IOException e){e.printStackTrace();}
        return loans;
    }
    public static void main(String[] args) {
        Loan[] loan= new Loan[4];
        loan[0]=new Loan("Java",2020,23156458);
        loan[1]= new Loan("C++",2019,12345698);
        loan[2] = new Loan("Python",2018,15975326);
        loan[3]= new Loan("Html",2021,12378945);
        SaveTOFile(loan);
        System.out.println("Loans information:");
        Loan[] loan1=LoadFromFile();
        for(Loan loan2:loan1)
            loan2.DisplayLoanInfo();
    }
}
