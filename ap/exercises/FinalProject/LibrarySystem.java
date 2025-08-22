package ap.exercises.FinalProject;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class LibrarySystem {
    private StudentManager studentManager;
    private BookManager bookManager;
    private MenuHandler menuHandler;
    private BorrowManager borrowManager;
    private EmployeeManager employeeManager;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookManager = new BookManager();
        this.menuHandler = new MenuHandler(this);
        this.borrowManager = new BorrowManager(bookManager);
        this.employeeManager = new EmployeeManager();
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }
    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }
    public Employee authenticateEmployee(String username, String password) {
        return employeeManager.authenticateEmployee(username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }
    public void editBook(String oldTitle, String newTitle, String newAuthor, Integer newYear) {
        bookManager.editBook(oldTitle, newTitle, newAuthor, newYear);
    }

    public void searchBookCombined(String title, String author, Integer year) {
        bookManager.searchBooks(title, author, year);
    }

    public void editStudentInformation(Student student, String newUsername, String newPassword) {
        studentManager.updateStudentInformation(student, newUsername, newPassword);
    }
    public void addBook(String title, String author, int year) {
        bookManager.addBook(new Book(title, author, year));
    }
    public void approveBorrowRequest(String studentId, String bookTitle) {
        borrowManager.approveBorrowRequest(studentId, bookTitle);
    }
    public void displayPendingBorrows() {
        borrowManager.displayPendingBorrows();
    }
    public boolean hasPendingBorrowRequests() {
        return borrowManager.hasPendingBorrows();
    }
    public void markBookAsReceived(String studentId, String bookTitle, String receiveDate) {
        borrowManager.markBookAsReceived(studentId, bookTitle, receiveDate);
    }

    public void displayUnreceivedBorrows() {
        borrowManager.displayUnreceivedBorrows();
    }

    public void borrowBook(Student student, String title, String start, String end) {

        Student currentStudent = studentManager.getStudentById(student.getStudentId());
        if (currentStudent != null && !currentStudent.isActive()) {
            System.out.println("Cannot borrow book. Student is inactive.");
            return;
        }
            borrowManager.addBorrow(new Borrow(student.getStudentId(), title, start, end));
            bookManager.markAsBorrowed(title);
        }

    public void returnBook(Student student, String title, String returnDate) {
        borrowManager.returnBook(student.getStudentId(), title, returnDate);
    }
    public List<Borrow> getStudentBorrowHistory(String studentId) {
        return borrowManager.getBorrowHistoryByStudent(studentId);
    }
    public boolean hasUnreceivedBorrows() {
        return borrowManager.hasUnreceivedBorrows();
    }

    public Map<String, Integer> getStudentBorrowStatistics(String studentId) {
        return borrowManager.getStudentBorrowStatistics(studentId);
    }
    public void setStudentActiveStatus(String studentId, boolean active) {
        studentManager.setStudentActiveStatus(studentId, active);
    }

    public void displayAllStudentsWithStatus() {
        studentManager.displayAllStudentsWithStatus();
    }

    public Student getStudentById(String studentId) {
        return studentManager.getStudentById(studentId);
    }


    public void displayAvailableBooks() {
        bookManager.displayAvailableBooks();
    }
    public void guestSearchBook(String title) {
        bookManager.searchBooksForGuest(title);
    }
    public int getBookCount() {
        return bookManager.getBooks().size();
    }

    public int getBorrowCount() {
        return borrowManager.getBorrowCount();
    }

    public void displayRecentBorrows(int limit) {
        borrowManager.displayRecentBorrows(limit);
    }


    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}

