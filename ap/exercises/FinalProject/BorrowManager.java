package ap.exercises.FinalProject;

import java.io.*;
import java.util.*;

public class BorrowManager {
    private List<Borrow> borrows;
    private final String borrowFile = "borrows.txt";

    public BorrowManager() {
        borrows = new ArrayList<>();
        loadBorrows();
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
        borrows.add(borrow);
        saveBorrows();
        System.out.println("Borrow request saved successfully.");
    }

    public void showBorrows() {
        for (Borrow b : borrows) {
            System.out.println(b);
        }
    }
    public void returnBook(String studentId, String bookTitle) {
        Borrow target = null;
        for (Borrow b : borrows) {
            if (b.getStudentId().equals(studentId) && b.getBookTitle().equalsIgnoreCase(bookTitle)) {
                target = b;
                break;
            }
        }
        if (target != null) {
            borrows.remove(target);
            saveBorrows();
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("No matching borrow found.");
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
}

