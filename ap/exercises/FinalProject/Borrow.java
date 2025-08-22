package ap.exercises.FinalProject;

public class Borrow {
    private String studentId;
    private String bookTitle;
    private String startDate;
    private String endDate;

    public Borrow(String studentId, String bookTitle, String startDate, String endDate) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStudentId() { return studentId; }
    public String getBookTitle() { return bookTitle; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    public String toFileString() {
        return studentId + "," + bookTitle + "," + startDate + "," + endDate;
    }

    public static Borrow fromFileString(String line) {
        String[] parts = line.split(",");
        return new Borrow(parts[0], parts[1], parts[2], parts[3]);
    }

    @Override
    public String toString() {
        return "Book: " + bookTitle + " | Borrowed by: " + studentId +
                " | From: " + startDate + " To: " + endDate;
    }
}

