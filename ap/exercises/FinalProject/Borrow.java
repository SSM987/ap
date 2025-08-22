package ap.exercises.FinalProject;

public class Borrow {
    private String studentId;
    private String bookTitle;
    private String startDate;
    private String endDate;
    private String receiveDate;
    private String returnDate;
    private boolean isReceived;

    public Borrow(String studentId, String bookTitle, String startDate, String endDate) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReceived = false;
        this.receiveDate = null;
        this.returnDate = null;
    }

    public String getStudentId() { return studentId; }
    public String getBookTitle() { return bookTitle; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getReceiveDate() { return receiveDate; }
    public String getReturnDate() { return returnDate; }
    public boolean isReceived() { return isReceived; }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
        this.isReceived = true;
    }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    public String toFileString() {
        return studentId + "," + bookTitle + "," + startDate + "," + endDate + "," +
                receiveDate + "," + returnDate + "," + isReceived;
    }

    public static Borrow fromFileString(String line) {
        String[] parts = line.split(",");
        Borrow borrow = new Borrow(parts[0], parts[1], parts[2], parts[3]);
        if (parts.length > 4 && !parts[4].equals("null")) {
            borrow.setReceiveDate(parts[4]);
        }
        if (parts.length > 5 && !parts[5].equals("null")) {
            borrow.setReturnDate(parts[5]);
        }
        if (parts.length > 6) {
            borrow.isReceived = Boolean.parseBoolean(parts[6]);
        }
        return borrow;
    }

    @Override
    public String toString() {
        String status = "Book: " + bookTitle + " | Borrowed by: " + studentId +
                " | From: " + startDate + " To: " + endDate;

        if (isReceived && receiveDate != null) {
            status += " | Received on: " + receiveDate;
        }
        if (returnDate != null) {
            status += " | Returned on: " + returnDate;
        }

        return status;
    }
}
