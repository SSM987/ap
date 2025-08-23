package ap.exercises.FinalProject;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Guest login");
            System.out.println("4. Manager Login");
            System.out.println("5. Employee Login");
            System.out.println("6. View Registered Student Count");
            System.out.println("7. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 7);

            switch (choice) {
                case 1:
                    handleStudentRegistration();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 3:
                    displayGuestMenu();
                    break;
                case 4:
                    handleManagerLogin();
                    break;
                case 5:
                    handleEmployeeLogin();
                     break;
                case 6:
                    displayStudentCount();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
            System.out.println("___________________________");
        }
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("\nTotal registered students: " + studentCount);
    }
    private void handleManagerLogin() {
        System.out.println("\n--- Manager Login ---");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (username.equals("Sepehr") && password.equals("Mousavi")) {
            displayManagerMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }
    private void handleEmployeeLogin() {
        System.out.println("\n--- Employee Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Employee emp = librarySystem.authenticateEmployee(username, password);

        if (emp != null) {
            System.out.println("Login successful! Welcome, " + emp.getName());
            librarySystem.setCurrentEmployeeId(emp.getEmployeeId());
            displayEmployeeMenu(emp);
        } else {
            System.out.println("Invalid information. try again.");
        }
    }
    private void displayEmployeeMenu(Employee emp) {
        while (true) {
            System.out.println("\n=== Employee Dashboard ===");
            System.out.println("1. View My Information");
            System.out.println("2. Change Password");
            System.out.println("3. Add Book");
            System.out.println("4. Search and Edit Book");
            System.out.println("5. Approve Pending Borrows");
            System.out.println("6. View Student Borrow Report");
            System.out.println("7. Change Student Status");
            System.out.println("8. Receive Book");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 9);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(emp);
                    break;
                case 2:
                    changeEmployeePassword(emp);
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    searchAndEditBook();
                    break;
                case 5:
                    handlePendingBorrowRequests();
                    break;
                case 6:
                    viewStudentBorrowReport();
                    break;
                case 7:
                    manageStudentStatus();
                    break;
                case 8:
                    receiveBorrowedBook();
                    break;
                case 9:
                    System.out.println("Logged out successfully.");
                    return;
            }
        }
    }
    private void manageStudentStatus() {
        System.out.println("\n--- Change Student Status ---");

        librarySystem.displayAllStudentsWithStatus();

        System.out.print("\nEnter Student ID to manage (or Enter 'cancel' to go back): ");
        String studentId = scanner.nextLine();

        if (studentId.equalsIgnoreCase("cancel")) {
            return;
        }

        Student student = librarySystem.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current status: " + (student.isActive() ? "Active" : "Inactive"));
        System.out.println("1. Activate Student");
        System.out.println("2. Deactivate Student");
        System.out.println("3. Cancel");
        System.out.print("Enter your choice: ");

        int choice = getIntInput(1, 3);

        switch (choice) {
            case 1:
                librarySystem.setStudentActiveStatus(studentId, true);
                break;
            case 2:
                librarySystem.setStudentActiveStatus(studentId, false);
                break;
            case 3:
                System.out.println("Operation cancelled.");
                break;
        }
    }
    private void changeEmployeePassword(Employee emp) {
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();
        librarySystem.getEmployeeManager().changeEmployeePassword(emp, newPass);
        emp.setPassword(newPass);
    }


        private void handleStudentRegistration() {
        System.out.println("\n--- New Student Registration ---");

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void displayManagerMenu() {
        while (true) {
            System.out.println("\n=== Manager Dashboard ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee Performance");
            System.out.println("3. View Borrow Statistical information");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    System.out.print("Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Employee ID: ");
                    String empId = scanner.nextLine();
                    System.out.print("Username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    librarySystem.getEmployeeManager().addEmployee(name, empId, uname, pass);
                    break;
                case 2:
                    viewEmployeePerformance();
                    break;
                case 3:
                    viewBorrowStatistics();
                    break;
                case 4:
                    return;
            }
        }
    }
    private void viewBorrowStatistics() {
        System.out.println("\n=== Borrow Statistics Report ===");

        Map<String, Object> stats = librarySystem.getBorrowStatistics();

        System.out.println("Total Borrow Requests: " + stats.get("totalBorrowRequests"));
        System.out.println("  - Approved Borrows: " + stats.get("totalBorrowed"));
        System.out.println("  - Pending Requests: " + stats.get("pendingRequests"));
        System.out.println("Total Returned Books: " + stats.get("totalReturned"));
        System.out.println("Average Borrow Days: "
                + String.format("%.2f", (double) stats.get("averageBorrowDays"))
                + " days");

        System.out.println("Calculated Returns: " + stats.get("calculatedReturns"));

        int totalBorrowed = (int) stats.get("totalBorrowed");
        int totalReturned = (int) stats.get("totalReturned");

        if (totalBorrowed > 0) {
            double returnPercentage = (double) totalReturned / totalBorrowed * 100;
            System.out.printf("Return Percentage: %.2f%%\n", returnPercentage);
        }
    }

            private void viewEmployeePerformance() {
        System.out.println("\n--- Employee Performance ---");
        librarySystem.displayEmployeePerformance();
    }
    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
    private void handlePendingBorrowRequests() {
        System.out.println("\n--- Pending Borrow Requests Management ---");
        librarySystem.displayPendingBorrows();
        if (librarySystem.hasPendingBorrowRequests()) {
            System.out.print("\nDo you want to approve a borrow request? (y/n): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();

                System.out.print("Enter book title: ");
                String bookTitle = scanner.nextLine();

                librarySystem.approveBorrowRequest(studentId, bookTitle);
            } else {
                System.out.println("No pending borrow requests to approve.");
            }
        }
    }

    private void displayLoggedInStudentMenu() {
        while (currentUser != null) {
            System.out.println("\n=== Student Dashboard ===");
            System.out.println("1. View My Information");
            System.out.println("2. Edit My Information");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. View Available Books");
            System.out.println("7. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 7);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(currentUser);
                    break;
                case 2:
                    changeInformation();
                    break;
                case 3:
                    BookSearch();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    librarySystem.displayAvailableBooks();
                    break;
                case 7:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private void displayGuestMenu() {
        while (true) {
            System.out.println("\n=== Guest Dashboard ===");
            System.out.println("1. View Registered Student Count");
            System.out.println("2. Search Book");
            System.out.println("3. View Statistical Information");
            System.out.println("4. Back to Main Menu");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2:
                    guestBookSearch();
                    break;
                case 3:
                    displayGuestStatisticalInformation();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    private boolean hasUnreceivedBorrows() {
        return librarySystem.hasUnreceivedBorrows();
    }
    private void receiveBorrowedBook() {
        System.out.println("\n--- Receive Borrowed Book ---");
        librarySystem.displayUnreceivedBorrows();

        if (!librarySystem.hasUnreceivedBorrows()) {
            System.out.println("No unreceived borrows to process.");
            return;
        }
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Book Title: ");
        String bookTitle = scanner.nextLine();

        System.out.print("Enter Receive Date (YYYY-MM-DD): ");
        String receiveDate = scanner.nextLine();

        librarySystem.markBookAsReceived(studentId, bookTitle, receiveDate);
    }
    private void searchAndEditBook() {
        System.out.println("\n--- Search Book for Edit ---");
        System.out.print("Enter book title (or you can leave blank): ");
        String title = scanner.nextLine();
        System.out.print("Enter author name (or you can leave blank): ");
        String author = scanner.nextLine();
        System.out.print("Enter year (or you can leave blank): ");
        String yearStr = scanner.nextLine();
        Integer year = null;
        if (!yearStr.isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year input. Ignored.");
            }
        }

        librarySystem.searchBookCombined(title, author, year);

        System.out.print("\nEnter the exact title of the book for edit: ");
        String oldTitle = scanner.nextLine();

        System.out.print("Enter New title (or you can leave blank): ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter New author (or you can leave blank): ");
        String newAuthor = scanner.nextLine();
        System.out.print("Enter New year (or you can leave blank): ");
        String newYearStr = scanner.nextLine();
        Integer newYear = null;
        if (!newYearStr.isEmpty()) {
            try {
                newYear = Integer.parseInt(newYearStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year input. Ignored.");
            }
        }

        librarySystem.editBook(oldTitle, newTitle, newAuthor, newYear);
    }
    private void viewStudentBorrowReport() {
        System.out.println("\n--- Student Borrow Report ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        List<Borrow> borrowHistory = librarySystem.getStudentBorrowHistory(studentId);

        if (borrowHistory.isEmpty()) {
            System.out.println("No borrow history found for student ID: " + studentId);
            return;
        }

        System.out.println("\n--- Borrow History for Student ID: " + studentId + " ---");
        for (Borrow borrow : borrowHistory) {
            System.out.println(borrow);
        }

        Map<String, Integer> stats = librarySystem.getStudentBorrowStatistics(studentId);

        System.out.println("\n--- Borrow Statistics ---");
        System.out.println("Total Borrows: " + stats.get("totalBorrows"));
        System.out.println("Not Received Yet: " + stats.get("notReceived"));
        System.out.println("Not Returned Yet: " + stats.get("notReturned"));
        System.out.println("Delayed Returns: " + stats.get("delayedReturns"));

        if (stats.get("totalBorrows") > 0) {
            double delayedPercentage = (double) stats.get("delayedReturns") / stats.get("totalBorrows") * 100;
            System.out.printf("Delayed Return Percentage: %.2f%%\n", delayedPercentage);
        }
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    private void guestBookSearch() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        librarySystem.guestSearchBook(title);
    }
    private void addBook() {
        System.out.println("\n--- Add Book ---");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter publication year: ");
        int year = Integer.parseInt(scanner.nextLine());

        librarySystem.addBook(title, author, year);
        System.out.println("Book added successfully.");
    }
    private void BookSearch() {
        System.out.println("\n--- Book Search ---");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter year of publication: ");
        String yearStr = scanner.nextLine();
        Integer year = null;
        if (!yearStr.isEmpty()) {
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year input. Ignored.");
            }
        }

        librarySystem.searchBookCombined(title, author, year);
    }
    private void borrowBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String start = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String end = scanner.nextLine();

        librarySystem.borrowBook(currentUser, title, start, end);
    }
    private void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        System.out.print("Enter return date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        librarySystem.returnBook(currentUser, title, returnDate);
    }

    private void changeInformation() {
        System.out.print("Enter new username: ");
        String newUser = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPass = scanner.nextLine();
        librarySystem.editStudentInformation(currentUser, newUser, newPass);
        currentUser.setUsername(newUser);
        currentUser.setPassword(newPass);
    }
    private void displayGuestStatisticalInformation() {
        System.out.println("\n--- Library Statistical Information ---");
        System.out.println("Total students: " + librarySystem.getStudentCount());
        System.out.println("Total books: " + librarySystem.getBookCount());
        System.out.println("Total borrows: " + librarySystem.getBorrowCount());

        if (librarySystem.getBorrowCount() > 0) {
            System.out.print("How many recent borrows would you like to see? ");
            int limit = getIntInput(1, librarySystem.getBorrowCount());
            librarySystem.displayRecentBorrows(limit);
        }
    }
}
