package ex7;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


class Book {
    private String id;
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String id, String title, String author, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
    }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }
    public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }
    public static Book fromTabLine(String line) {
        String[] parts = line.split("\t");
        return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }
    public String toTabLine() {
        return id + "\t" + title + "\t" + author + "\t" + borrowed;
    }
}

class Student {
    private String id;
    private String name;
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public static Student fromTabLine(String line) {
        String[] parts = line.split("\t");
        return new Student(parts[0], parts[1]);
    }
    public String toTabLine() {
        return id + "\t" + name;
    }
}

class Borrow {
    private String bookId;
    private String studentId;
    private String borrowDate;
    public Borrow(String bookId, String studentId, String borrowDate) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowDate = borrowDate;
    }
    public String getBookId() { return bookId; }
    public String getStudentId() { return studentId; }
    public String getBorrowDate() { return borrowDate; }
    public static Borrow fromTabLine(String line) {
        String[] parts = line.split("\t");
        return new Borrow(parts[0], parts[1], parts[2]);
    }
    public String toTabLine() {
        return bookId + "\t" + studentId + "\t" + borrowDate;
    }
}

// Tab strategies
class BookTabStorageStrategy implements StorageStrategy<Book> {
    private String filename;
    public BookTabStorageStrategy(String filename) { this.filename = filename; }
    public List<Book> load() throws IOException {
        List<Book> list = new ArrayList<>();
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return list;
        for (String line : Files.readAllLines(path)) {
            if (!line.trim().isEmpty()) list.add(Book.fromTabLine(line));
        }
        return list;
    }
    public void save(List<Book> list) throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Book b : list) pw.println(b.toTabLine());
        }
    }
}
class StudentTabStorageStrategy implements StorageStrategy<Student> {
    private String filename;
    public StudentTabStorageStrategy(String filename) { this.filename = filename; }
    public List<Student> load() throws IOException {
        List<Student> list = new ArrayList<>();
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return list;
        for (String line : Files.readAllLines(path)) {
            if (!line.trim().isEmpty()) list.add(Student.fromTabLine(line));
        }
        return list;
    }
    public void save(List<Student> list) throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Student s : list) pw.println(s.toTabLine());
        }
    }
}
class BorrowTabStorageStrategy implements StorageStrategy<Borrow> {
    private String filename;
    public BorrowTabStorageStrategy(String filename) { this.filename = filename; }
    public List<Borrow> load() throws IOException {
        List<Borrow> list = new ArrayList<>();
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return list;
        for (String line : Files.readAllLines(path)) {
            if (!line.trim().isEmpty()) list.add(Borrow.fromTabLine(line));
        }
        return list;
    }
    public void save(List<Borrow> list) throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Borrow b : list) pw.println(b.toTabLine());
        }
    }
}

// Gson strategies
class BookJsonStorageStrategy implements StorageStrategy<Book> {
    private String filename;
    private Gson gson = new Gson();
    public BookJsonStorageStrategy(String filename) { this.filename = filename; }
    public List<Book> load() throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return new ArrayList<>();
        String json = new String(Files.readAllBytes(path));
        List<Book> list = gson.fromJson(json, new TypeToken<List<Book>>(){}.getType());
        return list == null ? new ArrayList<>() : list;
    }
    public void save(List<Book> list) throws IOException {
        String json = gson.toJson(list);
        Files.write(Paths.get(filename), json.getBytes());
    }
}
class StudentJsonStorageStrategy implements StorageStrategy<Student> {
    private String filename;
    private Gson gson = new Gson();
    public StudentJsonStorageStrategy(String filename) { this.filename = filename; }
    public List<Student> load() throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return new ArrayList<>();
        String json = new String(Files.readAllBytes(path));
        List<Student> list = gson.fromJson(json, new TypeToken<List<Student>>(){}.getType());
        return list == null ? new ArrayList<>() : list;
    }
    public void save(List<Student> list) throws IOException {
        String json = gson.toJson(list);
        Files.write(Paths.get(filename), json.getBytes());
    }
}
class BorrowJsonStorageStrategy implements StorageStrategy<Borrow> {
    private String filename;
    private Gson gson = new Gson();
    public BorrowJsonStorageStrategy(String filename) { this.filename = filename; }
    public List<Borrow> load() throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return new ArrayList<>();
        String json = new String(Files.readAllBytes(path));
        List<Borrow> list = gson.fromJson(json, new TypeToken<List<Borrow>>(){}.getType());
        return list == null ? new ArrayList<>() : list;
    }
    public void save(List<Borrow> list) throws IOException {
        String json = gson.toJson(list);
        Files.write(Paths.get(filename), json.getBytes());
    }
}

// SQLite strategies
class BookSQLiteStorageStrategy implements StorageStrategy<Book> {
    private String dbUrl;
    public BookSQLiteStorageStrategy(String dbUrl) { this.dbUrl = dbUrl; }
    public List<Book> load() throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS books (id TEXT PRIMARY KEY, title TEXT, author TEXT, borrowed INTEGER)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "SELECT * FROM books";
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new Book(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("borrowed") != 0
                    ));
                }
            }
        }
        return list;
    }
    public void save(List<Book> list) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS books (id TEXT PRIMARY KEY, title TEXT, author TEXT, borrowed INTEGER)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "DELETE FROM books";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "INSERT INTO books (id, title, author, borrowed) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Book b : list) {
                    ps.setString(1, b.getId());
                    ps.setString(2, b.getTitle());
                    ps.setString(3, b.getAuthor());
                    ps.setInt(4, b.isBorrowed() ? 1 : 0);
                    ps.executeUpdate();
                }
            }
        }
    }
}
class StudentSQLiteStorageStrategy implements StorageStrategy<Student> {
    private String dbUrl;
    public StudentSQLiteStorageStrategy(String dbUrl) { this.dbUrl = dbUrl; }
    public List<Student> load() throws SQLException {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS students (id TEXT PRIMARY KEY, name TEXT)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "SELECT * FROM students";
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getString("id"),
                            rs.getString("name")
                    ));
                }
            }
        }
        return list;
    }
    public void save(List<Student> list) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS students (id TEXT PRIMARY KEY, name TEXT)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "DELETE FROM students";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "INSERT INTO students (id, name) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Student s : list) {
                    ps.setString(1, s.getId());
                    ps.setString(2, s.getName());
                    ps.executeUpdate();
                }
            }
        }
    }
}
class BorrowSQLiteStorageStrategy implements StorageStrategy<Borrow> {
    private String dbUrl;
    public BorrowSQLiteStorageStrategy(String dbUrl) { this.dbUrl = dbUrl; }
    public List<Borrow> load() throws SQLException {
        List<Borrow> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS borrows (bookId TEXT, studentId TEXT, borrowDate TEXT)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "SELECT * FROM borrows";
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    list.add(new Borrow(
                            rs.getString("bookId"),
                            rs.getString("studentId"),
                            rs.getString("borrowDate")
                    ));
                }
            }
        }
        return list;
    }
    public void save(List<Borrow> list) throws SQLException {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            String sql = "CREATE TABLE IF NOT EXISTS borrows (bookId TEXT, studentId TEXT, borrowDate TEXT)";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "DELETE FROM borrows";
            try (Statement st = conn.createStatement()) { st.execute(sql); }
            sql = "INSERT INTO borrows (bookId, studentId, borrowDate) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Borrow b : list) {
                    ps.setString(1, b.getBookId());
                    ps.setString(2, b.getStudentId());
                    ps.setString(3, b.getBorrowDate());
                    ps.executeUpdate();
                }
            }
        }
    }
}

class Library {
    private StorageStrategy<Book> bookStorage;
    private StorageStrategy<Student> studentStorage;
    private StorageStrategy<Borrow> borrowStorage;

    private List<Book> books;
    private List<Student> students;
    private List<Borrow> borrows;

    public Library(StorageStrategy<Book> bookStorage,
                   StorageStrategy<Student> studentStorage,
                   StorageStrategy<Borrow> borrowStorage) throws Exception {
        this.bookStorage = bookStorage;
        this.studentStorage = studentStorage;
        this.borrowStorage = borrowStorage;

        books = bookStorage.load();
        students = studentStorage.load();
        borrows = borrowStorage.load();
    }

    public void addBook(Book b) throws Exception {
        for (Book book : books) {
            if (book.getId().equals(b.getId())) {
                System.out.println("Book with this ID exists.");
                return;
            }
        }
        books.add(b);
        bookStorage.save(books);
    }

    public void addStudent(Student s) throws Exception {
        for (Student st : students) {
            if (st.getId().equals(s.getId())) {
                System.out.println("Student with this ID exists.");
                return;
            }
        }
        students.add(s);
        studentStorage.save(students);
    }

    public void borrowBook(String bookId, String studentId, String date) throws Exception {
        Book book = null;
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                book = b;
                break;
            }
        }
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isBorrowed()) {
            System.out.println("Book already borrowed.");
            return;
        }
        boolean studentFound = false;
        for (Student s : students) {
            if (s.getId().equals(studentId)) {
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            System.out.println("Student not found.");
            return;
        }
        book.setBorrowed(true);
        borrows.add(new Borrow(bookId, studentId, date));
        bookStorage.save(books);
        borrowStorage.save(borrows);
        System.out.println("Book borrowed.");
    }

    public void returnBook(String bookId) throws Exception {
        Book book = null;
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                book = b;
                break;
            }
        }
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isBorrowed()) {
            System.out.println("Book is not borrowed.");
            return;
        }
        book.setBorrowed(false);
        borrows.removeIf(b -> b.getBookId().equals(bookId));
        bookStorage.save(books);
        borrowStorage.save(borrows);
        System.out.println("Book returned.");
    }

    public void listBooks() {
        System.out.println("Books:");
        for (Book b : books) {
            System.out.printf("%s - %s by %s [%s]\n", b.getId(), b.getTitle(), b.getAuthor(),
                    b.isBorrowed() ? "Borrowed" : "Available");
        }
    }

    public void listStudents() {
        System.out.println("Students:");
        for (Student s : students) {
            System.out.printf("%s - %s\n", s.getId(), s.getName());
        }
    }
}
class ConfigReader {
    private Properties props = new Properties();

    public ConfigReader(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        props.load(fis);
        fis.close();
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}

public class EX7 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ConfigReader config = new ConfigReader("config.properties");
        String mode = config.get("storage");

        StorageStrategy<Book> bookStorage;
        StorageStrategy<Student> studentStorage;
        StorageStrategy<Borrow> borrowStorage;

        switch (mode) {
            case "tab":
                bookStorage = new BookTabStorageStrategy("books.tab");
                studentStorage = new StudentTabStorageStrategy("students.tab");
                borrowStorage = new BorrowTabStorageStrategy("borrows.tab");
                break;
            case "json":
                bookStorage = new BookJsonStorageStrategy("books.json");
                studentStorage = new StudentJsonStorageStrategy("students.json");
                borrowStorage = new BorrowJsonStorageStrategy("borrows.json");
                break;
            case "sqlite":
                String url = "jdbc:sqlite:library.db";
                bookStorage = new BookSQLiteStorageStrategy(url);
                studentStorage = new StudentSQLiteStorageStrategy(url);
                borrowStorage = new BorrowSQLiteStorageStrategy(url);
                break;
            default:
                System.out.println("Invalid mode. Exiting.");
                sc.close();
                return;
        }

        Library library = new Library(bookStorage, studentStorage, borrowStorage);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Student");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Students");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Book ID: ");
                    String bookId = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bookId, title, author, false));
                    break;
                case "2":
                    System.out.print("Student ID: ");
                    String studentId = sc.nextLine();
                    System.out.print("Student Name: ");
                    String studentName = sc.nextLine();
                    library.addStudent(new Student(studentId, studentName));
                    break;
                case "3":
                    System.out.print("Book ID to borrow: ");
                    String bId = sc.nextLine();
                    System.out.print("Student ID borrowing: ");
                    String sId = sc.nextLine();
                    System.out.print("Borrow Date (e.g. 2025-06-12): ");
                    String date = sc.nextLine();
                    library.borrowBook(bId, sId, date);
                    break;
                case "4":
                    System.out.print("Book ID to return: ");
                    String retId = sc.nextLine();
                    library.returnBook(retId);
                    break;
                case "5":
                    library.listBooks();
                    break;
                case "6":
                    library.listStudents();
                    break;
                case "7":
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
