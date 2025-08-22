package ap.exercises.FinalProject;

public class Employee {
    private String name;
    private String employeeId;
    private String username;
    private String password;
    private boolean active;
    private int booksAdded;
    private int booksBorrowed;
    private int booksReceived;

    public Employee(String name, String employeeId, String username, String password) {
        this.name = name;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.active = true;
        this.booksAdded = 0;
        this.booksBorrowed = 0;
        this.booksReceived = 0;
    }

    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActive() { return active; }
    public int getBooksAdded() { return booksAdded; }
    public int getBooksBorrowed() { return booksBorrowed; }
    public int getBooksReceived() { return booksReceived; }

    public void setPassword(String password) {this.password = password;}
    public void setActive(boolean active) { this.active = active; }
    public void incrementBooksAdded() { booksAdded++; }
    public void incrementBooksBorrowed() { booksBorrowed++; }
    public void incrementBooksReceived() { booksReceived++; }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Employee ID: " + employeeId +
                " | Username: " + username +
                " | Password: " + password +
                " | Active: " + active +
                " | Books Added: " + booksAdded +
                " | Books Borrowed: " + booksBorrowed +
                " | Books Received: " + booksReceived;
    }

    public String toFileString() {
        return name + "," + employeeId + "," + username + "," + password + "," +
                active + "," + booksAdded + "," + booksBorrowed + "," + booksReceived;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        Employee e = new Employee(parts[0], parts[1], parts[2], parts[3]);
        e.setActive(Boolean.parseBoolean(parts[4]));
        if (parts.length > 5) {
            e.booksAdded = Integer.parseInt(parts[5]);
            e.booksBorrowed = Integer.parseInt(parts[6]);
            e.booksReceived = Integer.parseInt(parts[7]);
        }
        return e;
    }
}

