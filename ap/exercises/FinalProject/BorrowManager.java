package ap.exercises.FinalProject;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowManager {
    private List<Borrow> borrows;
    private List<Borrow> pendingBorrows;
    private final String borrowFile = "borrows.txt";
    private final String pendingBorrowFile = "pending_borrows.txt";
    private BookManager bookManager;

    public BorrowManager(BookManager bookManager) {
        this.bookManager = bookManager;
        borrows = new ArrayList<>();
        pendingBorrows = new ArrayList<>();
        loadBorrows();
        loadPendingBorrows();
    }

    public void addBorrow(Borrow borrow) {
        for (Borrow b : borrows) {
            if (b.getBookTitle().equalsIgnoreCase(borrow.getBookTitle())) {
                if (!(borrow.getEndDate().compareTo(b.getStartDate()) < 0 ||
                        borrow.getStartDate().compareTo(b.getEndDate()) > 0)) {
                    System.out.println("This book is already borrowed in the selected time range.");
                    return;
                }
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(borrow.getStartDate(), formatter);

        if (startDate.isEqual(today) || startDate.isEqual(yesterday)) {
            pendingBorrows.add(borrow);
            savePendingBorrows();
            System.out.println("Borrow request submitted and pending approval. Please visit the library to collect your book.");
        } else {
            borrows.add(borrow);
            saveBorrows();
            System.out.println("Borrow request saved successfully.");
        }
    }

    public void approveBorrowRequest(String studentId, String bookTitle) {
        Iterator<Borrow> iterator = pendingBorrows.iterator();
        while (iterator.hasNext()) {
            Borrow borrow = iterator.next();
            if (borrow.getStudentId().equals(studentId) &&
                    borrow.getBookTitle().equalsIgnoreCase(bookTitle)) {

                borrows.add(borrow);
                iterator.remove();

                saveBorrows();
                savePendingBorrows();

                System.out.println("Borrow request approved successfully.");
                return;
            }
        }
        System.out.println("No matching pending borrow request found.");
    }

    public void displayPendingBorrows() {
        if (pendingBorrows.isEmpty()) {
            System.out.println("No pending borrow requests.");
            return;
        }

        System.out.println("\n--- Pending Borrow Requests ---");
        for (Borrow borrow : pendingBorrows) {
            System.out.println(borrow);
        }
    }

    public void showBorrows() {
        for (Borrow b : borrows) {
            System.out.println(b);
        }
    }
    public List<Borrow> getBorrowHistoryByStudent(String studentId) {
        List<Borrow> studentBorrows = new ArrayList<>();
        for (Borrow borrow : borrows) {
            if (borrow.getStudentId().equals(studentId)) {
                studentBorrows.add(borrow);
            }
        }
        return studentBorrows;
    }

    public Map<String, Integer> getStudentBorrowStatistics(String studentId) {
        Map<String, Integer> stats = new HashMap<>();
        List<Borrow> studentBorrows = getBorrowHistoryByStudent(studentId);

        int totalBorrows = studentBorrows.size();
        int notReturnedCount = 0;
        int delayedReturnCount = 0;
        int notReceivedCount = 0;

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Borrow borrow : studentBorrows) {
            LocalDate endDate = LocalDate.parse(borrow.getEndDate(), formatter);

            if (isBookStillBorrowed(borrow.getBookTitle())) {
                notReturnedCount++;
            }

            if (endDate.isBefore(today) && !isBookStillBorrowed(borrow.getBookTitle())) {
                delayedReturnCount++;
            }

            if (!borrow.isReceived()) {
                notReceivedCount++;
            }
        }

        stats.put("totalBorrows", totalBorrows);
        stats.put("notReturned", notReturnedCount);
        stats.put("delayedReturns", delayedReturnCount);
        stats.put("notReceived", notReceivedCount);

        return stats;
    }
    public boolean hasUnreceivedBorrows() {
        for (Borrow borrow : borrows) {
            if (!borrow.isReceived()) {
                return true;
            }
        }
        return false;
    }

    private boolean isBookStillBorrowed(String bookTitle) {
        for (Book book : bookManager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isBorrowed()) {
                return true;
            }
        }
        return false;
    }


    public void returnBook(String studentId, String bookTitle, String returnDate) {
        Borrow target = null;
        for (Borrow b : borrows) {
            if (b.getStudentId().equals(studentId) && b.getBookTitle().equalsIgnoreCase(bookTitle)) {
                target = b;
                break;
            }
        }
        if (target != null) {
            target.setReturnDate(returnDate);
            bookManager.markAsReturned(bookTitle);
            saveBorrows();
            System.out.println("Book returned successfully on " + returnDate);
        } else {
            System.out.println("No matching borrow found.");
        }
    }
    public void markBookAsReceived(String studentId, String bookTitle, String receiveDate) {
        for (Borrow borrow : borrows) {
            if (borrow.getStudentId().equals(studentId) &&
                    borrow.getBookTitle().equalsIgnoreCase(bookTitle) &&
                    !borrow.isReceived()) {

                borrow.setReceiveDate(receiveDate);
                saveBorrows();
                System.out.println("Book received successfully on " + receiveDate);
                return;
            }
        }
        System.out.println("No matching borrow request found or book already received.");
    }
    public void displayUnreceivedBorrows() {
        boolean found = false;
        System.out.println("\n--- Unreceived Borrows ---");
        for (Borrow borrow : borrows) {
            if (!borrow.isReceived()) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No unreceived borrows.");
        }
    }

    public int getBorrowCount() {
        return borrows.size();
    }

    public void displayRecentBorrows(int limit) {
        if (borrows.isEmpty()) {
            System.out.println("No active borrows.");
            return;
        }
        System.out.println("\n--- Recent Borrows ---");
        int start = Math.max(0, borrows.size() - limit);
        for (int i = borrows.size() - 1; i >= start; i--) {
            System.out.println(borrows.get(i));
        }
    }

    private void saveBorrows() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(borrowFile))) {
            for (Borrow b : borrows) {
                pw.println(b.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving borrows: " + e.getMessage());
        }
    }

    private void savePendingBorrows() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pendingBorrowFile))) {
            for (Borrow b : pendingBorrows) {
                pw.println(b.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving pending borrows: " + e.getMessage());
        }
    }

    private void loadBorrows() {
        File f = new File(borrowFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                borrows.add(Borrow.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading borrows: " + e.getMessage());
        }
    }
    public boolean hasPendingBorrows() {
        return !pendingBorrows.isEmpty();
    }

    private void loadPendingBorrows() {
        File f = new File(pendingBorrowFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                pendingBorrows.add(Borrow.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading pending borrows: " + e.getMessage());
        }
    }
}

