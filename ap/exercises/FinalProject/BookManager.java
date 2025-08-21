package ap.exercises.FinalProject;

import java.io.*;
import java.util.*;

public class BookManager {
    private List<Book> books;
    private final String bookFile = "books.txt";

    public BookManager() {
        this.books = new ArrayList<>();
        loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void searchBooks(String title, String author, Integer year) {
        boolean found = false;
        for (Book b : books) {
            boolean match = true;

            if (title != null && !title.isEmpty() && !b.getTitle().equalsIgnoreCase(title)) {
                match = false;
            }
            if (author != null && !author.isEmpty() && !b.getAuthor().equalsIgnoreCase(author)) {
                match = false;
            }
            if (year != null && b.getYear() != year) {
                match = false;
            }

            if (match) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }


    private void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(bookFile))) {
            for (Book b : books) {
                pw.println(b.getTitle() + "," + b.getAuthor() + "," + b.getYear() + "," + b.isBorrowed());
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private void loadBooks() {
        File f = new File(bookFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Book b = new Book(parts[0], parts[1], Integer.parseInt(parts[2]));
                if (Boolean.parseBoolean(parts[3])) {
                    b.setBorrowed(true);
                }
                books.add(b);
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}

