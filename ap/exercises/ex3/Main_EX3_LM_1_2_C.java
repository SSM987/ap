package ap.exercises.ex3;

public class Main_EX3_LM_1_2_C {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("Sepehr", "Afshar", 21552568, "computer engineering");
        students[1] = new Student("Ali", "Molaeei", 54156256, "mechanic engineering");
        students[2] = new Student("Mohammad", "Hasani", 12345678, "Mathematics");
        Book[] books = new Book[4];
        books[0] = new Book("The selfish GENE", "Richard dockins", 253, 1967);
        books[1] = new Book("The big project", "Steven Havking", 358, 2010);
        books[2] = new Book("Effective Java", "Joshua Bloch", 416, 2018);
        books[3] = new Book("Clean code", "Robert C.Martin", 464, 2008);
        Main_EX3_LM_1_2_A.SaveStudentsToFile(students);
        Main_EX3_LM_1_2_A.SaveBooksToFile(books);
        Student[] showStudent = Main_EX3_LM_1_2_B.readStudentsFromFile();
        Book[] showBooks = Main_EX3_LM_1_2_B.readBookFromFile();
        for (Student student:showStudent)
            student.DisplayStudentInfo();
        for (Book book:showBooks)
            book.DisplayBookInfo();
    }
}
