package ap.exercises.ex3;

import java.util.Scanner;

public class Main_EX3_LM_1_3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Student[] students = {
                new Student("Sepehr", "Afshar", 21552568, "computer engineering"),
                new Student("Ali", "Molaeei", 54156256, "mechanic engineering"),
                new Student("Mohammad", "Hasani", 12345678, "Mathematics"),
        };
        System.out.print("Enter a name to search(First letter should be capital) (Sepehr,Ali,Mohammad are available):");
        String n = s.next();
        Student name = SearchStudentName(students, n);
        if (name == null)
            System.out.println("Student does not exist");
        else
            name.DisplayStudentInfo();
    }
    public static Student SearchStudentName(Student[]students,String name){
        for(Student student:students){
            if (student.firstName.equals(name))
                return student;
        }
        return null;
    }
}
