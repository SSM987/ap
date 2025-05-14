package ap.exercises.MidTerm;
import java.io.*;
import java.util.*;

class Book {
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }

    public String getTitle() {
        return title;
    }
}


    class Student {
 private String firstName;
 private String lastName;
 private String studentId;
 private String major;
 private String membershipDate;

    public Student(String firstName, String lastName, String studentId, String major, String membershipDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.major = major;
        this.membershipDate =membershipDate;
    }

        @Override
        public String toString() {
            return "Student{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", studentId='" + studentId + '\'' +
                    ", major='" + major + '\'' +
                    ", membershipDate='" + membershipDate + '\'' +
                    '}';
        }
    }

class Librarian  {
   private String firstName;
private String lastName;
private String employeeId;

    public Librarian(String firstName, String lastName, String employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}

class Manager {
    private String firstName;
    private String lastName;
   private String educationLevel;

    public Manager(String firstName, String lastName, String educationLevel) {
        this.lastName = lastName;
        this.educationLevel = educationLevel;
        this.firstName=firstName;
    }
}

class Library {
  private String name;
  private List<Book> bookList= new ArrayList<>();
  private List<Student>studentList=new ArrayList<>();
  private List<Librarian> librarianList=new ArrayList<>();
  private Manager manager;
    public Library(String name, Manager manager, List<Librarian> librarianList) {
        this.name = name;
        this.manager = manager;
        this.librarianList.addAll(librarianList);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }
public List<Book>searchBook(String password){
        List<Book> result=new ArrayList<>();
    for (Book book : bookList) {
        if (book.getTitle().toLowerCase().contains(password.toLowerCase()))
            result.add(book);
    }
    return result;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
class saveToFile{
    public void saveStudent(List<Student>students) {
        try {
            PrintWriter printWriter = new PrintWriter("Student.txt");
            for (Student student : students)
                printWriter.println(student);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not exist.");
        }
    }
    public void saveBook(List<Book> books){
        try{
            PrintWriter printWriter= new PrintWriter("Books.txt");
            for (Book book : books) {
                printWriter.println(book);
                printWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exist.");
        }
    }
}
class HandleInput {
    public String getInput(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
    class Menu{
        private HandleInput input;

        public Menu(HandleInput input){
            this.input=input;
        }
        public void showMenu(Library library,Scanner scanner){
            while (true){
                System.out.println("1-register");
                System.out.println("2-Search book");
                System.out.println("3-add book");
                System.out.println("4-exit");
                String choice= input.getInput(scanner,"choice: ");
                switch (choice){
                    case "1":
                        String f=input.getInput(scanner,"name:");
                        String l=input.getInput(scanner,"lastname:");
                        String Id=input.getInput(scanner,"ID:");
                        String major=input.getInput(scanner,"major:");
                        String date=input.getInput(scanner,"date:");
                        Student student = new Student(f,l,Id,major,date);
                        System.out.println(" register successfully");
                        break;
                    case "2":
                        String password=input.getInput(scanner,"book name: ");
                        List<Book> books=library.searchBook(password);
                        if(books.isEmpty())
                            System.out.println("null");
                        else {
                            for (Book book : books) {
                                System.out.println(book);
                            }
                        }
                        break;
                    case "3":
                        String title= input.getInput(scanner,"title: ");
                        String author= input.getInput(scanner,"author: ");
                        int yaer= Integer.parseInt(input.getInput(scanner,"year: "));
                        int page=Integer.parseInt(input.getInput(scanner,"pages: "));
                        Book book= new Book(title,author,yaer,page);
                        library.addBook(book);
                        System.out.println("book added");
                    case "4":
                        saveToFile save= new saveToFile();
                        try{
                            save.saveStudent(library.getStudentList());
                            save.saveBook(library.getBookList());
                            System.out.println("save successfully");
                        } catch (Exception e){
                            System.out.println("Error in save to file");;
                        }
                        return;
                }
            }
        }
    }

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
     Manager manager= new Manager("sepehr","mousavi","computer engeneering");
     Librarian librarian= new Librarian("ali","ahmadi","1234");
     Librarian librarian1= new Librarian("mohammad","heydari","12345");
     Library library= new Library("library",manager,Arrays.asList(librarian1,librarian));
     HandleInput handleInput= new HandleInput();
        Menu menu= new Menu(handleInput);
        System.out.println("welcome to library");
        System.out.println("Enter your role:");
        System.out.println("1- student");
        String string= handleInput.getInput(scanner,"Enter your choice: ");
        if (string.equals("1"))
            menu.showMenu(library,scanner);
        scanner.close();
    }
}