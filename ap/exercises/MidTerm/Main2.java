package ap.exercises.MidTerm;
import java.io.*;
import java.util.*;

class Book2 {
    private String title, author;
    private int year, pages;
    private int borrowCount = 0;

    public Book2(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public void increaseBorrowCount() {
        borrowCount++;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public String getTitle() {
        return title;
    }

    public String toFileString() {
        return title + "," + author + "," + year + "," + pages + "," + borrowCount;
    }

    public String toString() {
        return title + " - " + author + " - " + year + " - " + pages + " pages - Borrowed: " + borrowCount;
    }
}

class Student2 {
    private String firstName, lastName, studentID, major, joinDate;

    public Student2(String fn, String ln, String id, String major, String date) {
        this.firstName = fn;
        this.lastName = ln;
        this.studentID = id;
        this.major = major;
        this.joinDate = date;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toFileString() {
        return firstName + "," + lastName + "," + studentID + "," + major + "," + joinDate;
    }

    public String toString() {
        return getFullName() + " (" + studentID + ") - " + major + " - Joined: " + joinDate;
    }
}

class Librarian2 {
    private String firstName;
    private String lastName;
     private String employeeID;

    public Librarian2(String fn, String ln, String id) {
        this.firstName = fn;
        this.lastName = ln;
        this.employeeID = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toFileString() {
        return firstName + "," + lastName + "," + employeeID;
    }

    public String toString() {
        return getFullName() + " (ID: " + employeeID + ")";
    }
}

class Borrow2 {
    private Book2 book;
    private Student2 student;
    private Librarian2 giver, taker;
    private String borrowDate, dueDate, returnDate;

    public Borrow2(Book2 b, Student2 s, Librarian2 g, String bd, String dd) {
        this.book = b;
        this.student = s;
        this.giver = g;
        this.borrowDate = bd;
        this.dueDate = dd;
        this.returnDate = null;
        this.book.increaseBorrowCount();
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public Librarian2 getGiver() {
        return giver;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Librarian2 getTaker() {
        return taker;
    }

    public void returnBook(String returnDate, Librarian2 taker) {
        this.returnDate = returnDate;
        this.taker = taker;
    }

    public boolean isLate() {
        return returnDate != null && returnDate.compareTo(dueDate) > 0;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public Student2 getStudent() {
        return student;
    }

    public Book2 getBook() {
        return book;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String toString() {
        String status = isReturned() ? ("Returned on " + returnDate) : "Not returned";
        return book.getTitle() + " borrowed by " + student.getFullName() + " (" + student.getStudentID() + "), Due: " + dueDate + ", " + status;
    }
}

class Manager2 {
    private String firstName, lastName, degree;

    public Manager2(String fn, String ln, String degree) {
        this.firstName = fn;
        this.lastName = ln;
        this.degree = degree;
    }
}

class Library2 {
    private String name;
    private Manager2 manager;
    private ArrayList<Book2> books = new ArrayList<>();
    private ArrayList<Student2> students = new ArrayList<>();
    private ArrayList<Librarian2> librarians = new ArrayList<>();
    private ArrayList<Borrow2> borrows = new ArrayList<>();

    public Library2(String name, Manager2 manager) {
        this.name = name;
        this.manager = manager;
    }

    public void addBook(Book2 b) {
        books.add(b);
    }

    public void addStudent(Student2 s) {
        students.add(s);
    }

    public void addLibrarian(Librarian2 l) {
        librarians.add(l);
    }

    public void addBorrow(Borrow2 b) {
        borrows.add(b);
    }

    public ArrayList<Book2> getBooks() {
        return books;
    }

    public ArrayList<Student2> getStudents() {
        return students;
    }

    public ArrayList<Librarian2> getLibrarians() {
        return librarians;
    }

    public ArrayList<Borrow2> getBorrows() {
        return borrows;
    }

    public Student2 findStudentByID(String id) {
        for (Student2 s : students) {
            if (s.getStudentID().equals(id)) return s;
        }
        return null;
    }

    public Book2 findBookByTitle(String title) {
        for (Book2 b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    public Librarian2 getFirstLibrarian() {
        if (librarians.isEmpty()) return null;
        return librarians.get(0);
    }
}

class FileHandler2 {
    private Library2 library;

    public FileHandler2(Library2 library) {
        this.library = library;
    }

    public void saveData() throws IOException {
        PrintWriter bookWriter = new PrintWriter("books.txt");
        for (Book2 book : library.getBooks()) {
            bookWriter.println(book.toFileString());
        }
        bookWriter.close();

        PrintWriter studentWriter = new PrintWriter("students.txt");
        for (Student2 student : library.getStudents()) {
            studentWriter.println(student.toFileString());
        }
        studentWriter.close();

        PrintWriter librarianWriter = new PrintWriter("librarians.txt");
        for (Librarian2 librarian : library.getLibrarians()) {
            librarianWriter.println(librarian.toFileString());
        }
        librarianWriter.close();

        PrintWriter borrowWriter = new PrintWriter("borrows.txt");
        for (Borrow2 borrow : library.getBorrows()) {
            String rt = borrow.getReturnDate() == null ? "-" : borrow.getReturnDate();
            String takerID = borrow.isReturned() ? borrow.getTaker().getEmployeeID() : "-";
            borrowWriter.println(
                    borrow.getBook().getTitle() + "," +
                            borrow.getStudent().getStudentID() + "," +
                            borrow.getGiver().getEmployeeID() + "," +
                            borrow.getBorrowDate() + "," +
                            borrow.getDueDate() + "," +
                            rt + "," +
                            takerID
            );
        }
        borrowWriter.close();
    }

    public void loadData() throws IOException {
        File bookFile = new File("books.txt");
        if (bookFile.exists()) {
            Scanner sc = new Scanner(bookFile);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length >= 5) {
                    Book2 b = new Book2(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    int count = Integer.parseInt(parts[4]);
                    for (int i = 0; i < count; i++) b.increaseBorrowCount();
                    library.addBook(b);
                }
            }
            sc.close();
        }

        File studentFile = new File("students.txt");
        if (studentFile.exists()) {
            Scanner sc = new Scanner(studentFile);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length >= 5) {
                    Student2 s = new Student2(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    library.addStudent(s);
                }
            }
            sc.close();
        }

        File librarianFile = new File("librarians.txt");
        if (librarianFile.exists()) {
            Scanner sc = new Scanner(librarianFile);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length >= 3) {
                    Librarian2 l = new Librarian2(parts[0], parts[1], parts[2]);
                    library.addLibrarian(l);
                }
            }
            sc.close();
        }

        File borrowFile = new File("borrows.txt");
        if (borrowFile.exists()) {
            Scanner sc = new Scanner(borrowFile);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length >= 7) {
                    Book2 book = library.findBookByTitle(parts[0]);
                    Student2 student = library.findStudentByID(parts[1]);
                    Librarian2 giver = null;
                    for (Librarian2 l : library.getLibrarians()) {
                        if (l.getEmployeeID().equals(parts[2])) {
                            giver = l;
                            break;
                        }
                    }
                    String borrowDate = parts[3];
                    String dueDate = parts[4];
                    String returnDate = parts[5].equals("-") ? null : parts[5];
                    String takerID = parts[6].equals("-") ? null : parts[6];
                    Librarian2 taker = null;
                    if (takerID != null) {
                        for (Librarian2 l : library.getLibrarians()) {
                            if (l.getEmployeeID().equals(takerID)) {
                                taker = l;
                                break;
                            }
                        }
                    }
                    if (book != null && student != null && giver != null) {
                        Borrow2 borrow = new Borrow2(book, student, giver, borrowDate, dueDate);
                        if (returnDate != null) {
                            borrow.returnBook(returnDate, taker);
                        }
                        library.addBorrow(borrow);
                    }
                }
            }
            sc.close();
        }
    }
}

class Menu2 {
    private Library2 library;
    private Scanner scanner;

    public Menu2(Library2 library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        System.out.println("Welcome to the Library System!");
        while (true) {
            System.out.print("Enter user type (student/manager) or 'exit' to quit: ");
            String userType = scanner.nextLine().trim().toLowerCase();
            if (userType.equals("exit")) break;

            if (userType.equals("student")) {
                studentMenu();
            } else if (userType.equals("manager")) {
                managerMenu();
            } else {
                System.out.println("Invalid user type.");
            }
        }
    }

    private void studentMenu() throws IOException {
        System.out.println("\n--- Student Menu ---");
        System.out.println("1. Register (Sign Up)");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Exit to main menu");
        while (true) {
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    studentRegister();
                    break;
                case "2":
                    studentBorrowBook();
                    break;
                case "3":
                    studentReturnBook();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void studentRegister() {
        System.out.println("Register new student:");
        System.out.print("First Name: ");
        String fn = scanner.nextLine();
        System.out.print("Last Name: ");
        String ln = scanner.nextLine();
        System.out.print("Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Major: ");
        String major = scanner.nextLine();
        System.out.print("Join Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        if (library.findStudentByID(id) != null) {
            System.out.println("Student ID already registered!");
            return;
        }
        Student2 s = new Student2(fn, ln, id, major, date);
        library.addStudent(s);
        System.out.println("Registration successful.");
    }

    private void studentBorrowBook() {
        System.out.print("Enter your Student ID: ");
        String studentID = scanner.nextLine();
        Student2 student = library.findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found, please register first.");
            return;
        }
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        Book2 book = library.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        Librarian2 librarian = library.getFirstLibrarian();
        if (librarian == null) {
            System.out.println("No librarian available.");
            return;
        }
        String today = "2025-05-19";
        String dueDate = "2025-06-19";
        Borrow2 borrow = new Borrow2(book, student, librarian, today, dueDate);
        library.addBorrow(borrow);
        System.out.println("Book borrowed successfully. Due date: " + dueDate);
    }

    private void studentReturnBook() {
        System.out.print("Enter your Student ID: ");
        String studentID = scanner.nextLine();
        Student2 student = library.findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        Borrow2 borrow = null;
        for (Borrow2 b : library.getBorrows()) {
            if (b.getStudent().getStudentID().equals(studentID) &&
                    b.getBook().getTitle().equalsIgnoreCase(title) && !b.isReturned()) {
                borrow = b;
                break;
            }
        }
        if (borrow == null) {
            System.out.println("No borrow record found for this book.");
            return;
        }
        Librarian2 librarian = library.getFirstLibrarian();
        if (librarian == null) {
            System.out.println("No librarian available.");
            return;
        }
        String today = "2025-05-19";
        borrow.returnBook(today, librarian);
        System.out.println("Book returned successfully.");
    }

    private void managerMenu() {
        System.out.println("\n--- Manager Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Add Librarian");
        System.out.println("3. List Books");
        System.out.println("4. List Students");
        System.out.println("5. List Librarians");
        System.out.println("6. List Late Borrows");
        System.out.println("7. Exit to main menu");
        while (true) {
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    addLibrarian();
                    break;
                case "3":
                    listBooks();
                    break;
                case "4":
                    listStudents();
                    break;
                case "5":
                    listLibrarians();
                    break;
                case "6":
                    listLateBorrows();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Pages: ");
        int pages = Integer.parseInt(scanner.nextLine());
        Book2 b = new Book2(title, author, year, pages);
        library.addBook(b);
        System.out.println("Book added.");
    }

    private void addLibrarian() {
        System.out.print("First Name: ");
        String fn = scanner.nextLine();
        System.out.print("Last Name: ");
        String ln = scanner.nextLine();
        System.out.print("Employee ID: ");
        String id = scanner.nextLine();
        Librarian2 l = new Librarian2(fn, ln, id);
        library.addLibrarian(l);
        System.out.println("Librarian added.");
    }

    private void listBooks() {
        System.out.println("\nBooks:");
        for (Book2 b : library.getBooks()) {
            System.out.println(b);
        }
    }

    private void listStudents() {
        System.out.println("\nStudents:");
        for (Student2 s : library.getStudents()) {
            System.out.println(s);
        }
    }

    private void listLibrarians() {
        System.out.println("\nLibrarians:");
        for (Librarian2 l : library.getLibrarians()) {
            System.out.println(l);
        }
    }

    private void listLateBorrows() {
        System.out.println("\nLate Borrows:");
        boolean found = false;
        for (Borrow2 b : library.getBorrows()) {
            if (!b.isReturned() && b.isLate()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No late borrows found.");
    }
}

public class Main2 {
    public static void main(String[] args) {
        Manager2 manager = new Manager2("Ali", "Karimi", "PhD");
        Library2 library = new Library2("Uni Library", manager);

        FileHandler2 fileHandler = new FileHandler2(library);

        try {
            fileHandler.loadData();
        } catch (IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }

        Menu2 menu = new Menu2(library);

        try {
            menu.start();
            fileHandler.saveData();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Goodbye!");
    }
}





