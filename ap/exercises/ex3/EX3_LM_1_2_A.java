package ap.exercises.ex3;

import java.io.FileWriter;
import java.io.IOException;

public class EX3_LM_1_2_A {
    public static void main(String[] args) {
        Book[] books={
                new Book("The selfish GENE","Richard dockins",253,1967),
                new Book("The big project","Steven Havking", 358,2010),
                new Book("Effective Java","Joshua Bloch",416,2018),
                new Book("Clean code","Robert C.Martin",464,2008),
        };
        Student[] students={
                new Student("Sepehr","Afshar",21552568 ,"computer engineering"),
                new Student("Ali","Molaeei",54156256 ,"mechanic engineering"),
                new Student("Mohammad","Hasani",12345678,"Mathematics")
        };
        SaveBooksToFile(books);
        SaveStudentsToFile(students);
    }
    public static void SaveBooksToFile(Book[]books){
        try(FileWriter fileWriter=new FileWriter("Books.txt")) {
            for (Book book : books)
                fileWriter.write(book.bookName + "    " + book.author + "    " + book.numberOfPages + "    " + book.year + "    " + "\n");
            System.out.println("Books save to file successfully");
        } catch (IOException e){e.printStackTrace();}
        }
        public static void SaveStudentsToFile(Student[]students){
            try(FileWriter fileWriter=new FileWriter("Students.txt")) {
                for (Student student : students)
                    fileWriter.write(student.firstName + "    " + student.lastname + "    " + student.studentNumber + "    " + student.fieldOfStudy + "    " + "\n");
                System.out.println("Students save to file successfully");
            } catch (IOException e){e.printStackTrace();}
        }
    }
