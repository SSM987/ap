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

