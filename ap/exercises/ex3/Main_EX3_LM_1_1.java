package ap.exercises.ex3;
class Book {
    String bookName;
    String author;
    int numberOfPages;
    int year;

    public Book(String bookName, String author, int numberOFPages, int year) {
        this.bookName = bookName;
        this.author = author;
        this.numberOfPages = numberOFPages;
        this.year = year;
    }

    public void DisplayBookInfo() {
        System.out.println("bookname: " + bookName+"\t\t"+"author: " + author+"\t\t"+"number of pages: " + numberOfPages+"\t\t"+"year: " + year);
    }
    public void ChangeBookInfo(String bookName, String author, int numberOFPages, int year){
        this.bookName = bookName;
        this.author = author;
        this.numberOfPages = numberOFPages;
        this.year = year;
    }
}
class Student{
    String firstName;
    String lastname;
    int studentNumber;
    String fieldOfStudy;

    public Student(String firstName,String lastname,int studentNumber,String fieldOfStudy){
        this.fieldOfStudy=fieldOfStudy;
        this.firstName=firstName;
        this.lastname=lastname;
        this.studentNumber=studentNumber;
    }
    public void DisplayStudentInfo(){
        System.out.println("firstName: "+firstName+"\t\t"+"lastname: "+lastname+"\t\t"+"studentNumber: "+studentNumber+"\t\t"+"fieldOfStudy: "+fieldOfStudy);
    }
    public void ChangeStudentInfo(String firstName,String lastname,int studentNumber,String fieldOfStudy){
        this.fieldOfStudy=fieldOfStudy;
        this.firstName=firstName;
        this.lastname=lastname;
        this.studentNumber=studentNumber;
    }
}
public class Main_EX3_LM_1_1 {
    public static void main(String[] args) {
        Book book1= new Book("The selfish GENE","Richard dockins",253,1967);
        Book book2 = new Book("The big project","Steven Havking", 358,2010);
        Student student1= new Student("Sepehr","Afshar",21552568 ,"computer engineering");
        Student student2= new Student("Ali","Molaeei",54156256 ,"mechanic engineering");
        book1.DisplayBookInfo();
        book2.DisplayBookInfo();
        student1.DisplayStudentInfo();
        student2.DisplayStudentInfo();
    }
    }

