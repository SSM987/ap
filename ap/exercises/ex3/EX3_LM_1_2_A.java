package ap.exercises.ex3;

import java.io.FileWriter;
import java.io.IOException;

public class EX3_LM_1_2_A {
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
