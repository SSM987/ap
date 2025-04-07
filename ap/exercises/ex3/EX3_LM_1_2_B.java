package ap.exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EX3_LM_1_2_B {
        public static Student[] readStudentsFromFile(){
        Student[] students = new Student[3];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Students.txt"))){
            String s;
            int i=0;
            while ((s=bufferedReader.readLine())!=null){
               String[] strings=s.split(" {4}");
               students[i]= new Student(strings[0],strings[1],Integer.parseInt(strings[2]),strings[3]);
               i++;
            }
        } catch (IOException e){e.printStackTrace();}
        return students;
        }
        public static Book[] readBookFromFile(){
        Book[] books = new Book[4];
        try(BufferedReader reader = new BufferedReader(new FileReader("Books.txt") )){
            String strings;
            int i=0;
            while ((strings=reader.readLine())!=null) {
                String[] s = strings.split(" {4}");
                books[i] = new Book(s[0], s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
                i++;
            }
            } catch (IOException e){e.printStackTrace();}
            return books;
        }
    }
