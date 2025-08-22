package ap.exercises.FinalProject;


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
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(emp);
                    break;
                case 2:
                    changeEmployeePassword(emp);
                    break;
                case 3:
                    System.out.println("Logged out successfully.");
                    return;
            }
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
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(1, 3);

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
                    return;
            }
        }
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
        librarySystem.returnBook(currentUser, title);
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
