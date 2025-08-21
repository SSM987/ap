package ap.exercises.FinalProject;

class Book {
    private String title;
    private String author;
    private int year;
    private boolean isBorrowed;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isBorrowed = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean b) { this.isBorrowed = b; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year +
                ", Status: " + (isBorrowed ? "Not Available" : "Available");
    }
}

