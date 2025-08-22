package ap.exercises.FinalProject;

import java.io.*;
import java.util.*;

public class EmployeeManager {
    private List<Employee> employees;
    private final String employeeFile = "employees.txt";

    public EmployeeManager() {
        this.employees = new ArrayList<>();
        loadEmployees();
    }

    public void addEmployee(String name, String employeeId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists");
            return;
        }
        Employee emp = new Employee(name, employeeId, username, password);
        employees.add(emp);
        saveEmployees();
        System.out.println("Employee registered successfully.");
    }
    public void changeEmployeePassword(Employee emp, String newPassword) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equals(emp.getEmployeeId())) {
                e.setPassword(newPassword);
                break;
            }
        }
        saveEmployees();
        System.out.println("Password changed successfully.");
    }
    public void increaseEmployeeBooksAdded(String employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equals(employeeId)) {
                e.incrementBooksAdded();
                saveEmployees();
                return;
            }
        }
    }

    public void increaseEmployeeBooksBorrowed(String employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equals(employeeId)) {
                e.incrementBooksBorrowed();
                saveEmployees();
                return;
            }
        }
    }
    public void increaseEmployeeBooksReceived(String employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equals(employeeId)) {
                e.incrementBooksReceived();
                saveEmployees();
                return;
            }
        }
    }
    public void displayEmployeePerformance() {
        if (employees.isEmpty()) {
            System.out.println("No employees registered yet.");
            return;
        }

        System.out.println("\n=== Employee Performance Report ===");
        for (Employee e : employees) {
            System.out.println("Name: " + e.getName() +
                    " | ID: " + e.getEmployeeId() +
                    " | Books Added: " + e.getBooksAdded() +
                    " | Books Borrowed: " + e.getBooksBorrowed() +
                    " | Books Received: " + e.getBooksReceived());
        }
    }


    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees registered yet.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private boolean isUsernameTaken(String username) {
        return employees.stream().anyMatch(e -> e.getUsername().equals(username));
    }
    public Employee authenticateEmployee(String username, String password) {
        loadEmployees();
        return employees.stream()
                .filter(e -> e.getUsername().equals(username) && e.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }


    private void saveEmployees() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(employeeFile))) {
            for (Employee e : employees) {
                pw.println(e.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }

    private void loadEmployees() {
        employees.clear();
        File f = new File(employeeFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                employees.add(Employee.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

