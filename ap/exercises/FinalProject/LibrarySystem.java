package ap.exercises.FinalProject;

public class LibrarySystem {
    private StudentManager studentManager;
    private BookManager bookManager;
    private MenuHandler menuHandler;
    private BorrowManager borrowManager;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookManager = new BookManager();
        this.menuHandler = new MenuHandler(this);
        this.borrowManager = new BorrowManager();
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }
    
    public void searchBookCombined(String title, String author, Integer year) {
        bookManager.searchBooks(title, author, year);
    }

    public void editStudentInformation(Student student, String newUsername, String newPassword) {
        studentManager.updateStudentInformation(student, newUsername, newPassword);
    }

    public void borrowBook(Student student, String title, String start, String end) {
        borrowManager.addBorrow(new Borrow(student.getStudentId(), title, start, end));
        bookManager.markAsBorrowed(title);
    }

    public void returnBook(Student student, String title) {
        borrowManager.returnBook(student.getStudentId(), title);
        bookManager.markAsReturned(title);
    }

    public void displayAvailableBooks() {
        bookManager.displayAvailableBooks();
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}

