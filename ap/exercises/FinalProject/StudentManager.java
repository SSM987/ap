package ap.exercises.FinalProject;

import java.util.*;
import java.io.*;

public class StudentManager {
    private List<Student> students;
    private final String studentFile = "students.txt";

    public StudentManager() {
        this.students = new ArrayList<>();
        loadStudents();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                System.out.println("Student ID already exists. Please use a different ID.");
                return;
            }
        }
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }
        Student newStudent = new Student(name, studentId, username, password);
        students.add(newStudent);
        saveStudents();
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password) && s.isActive())
                .findFirst()
                .orElse(null);
    }


    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private boolean isUsernameTaken(String username) {
        return students.stream().anyMatch(s -> s.getUsername().equals(username));
    }

    public int getStudentCount() {
        return students.size();
    }

    public void updateStudentInformation(Student student, String newUsername, String newPassword) {
        boolean updated = false;
        for (Student s : students) {
            if (s.getStudentId().equals(student.getStudentId())) {
                s.setUsername(newUsername);
                s.setPassword(newPassword);
                updated = true;
                break;
            }
        }
        if (updated) {
            saveStudents();
            System.out.println("Credentials updated successfully.");
        } else {
            System.out.println("Student not found for update.");
        }
    }
    public void setStudentActiveStatus(String studentId, boolean active) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setActive(active);
                saveStudents();
                System.out.println("Student " + studentId + " is now " + (active ? "active" : "inactive"));
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudentsWithStatus() {
        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        System.out.println("\n--- List of All Students ---");
        for (Student student : students) {
            System.out.println(student.getName() + " | ID: " + student.getStudentId() +
                    " | Status: " + (student.isActive() ? "Active" : "Inactive"));
        }
    }

    private void saveStudents() {
        Map<String, Student> uniqueStudents = new HashMap<>();
        for (Student s : students) {
            uniqueStudents.put(s.getStudentId(), s);
        }
        students = new ArrayList<>(uniqueStudents.values());
        try (PrintWriter pw = new PrintWriter(new FileWriter(studentFile))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private void loadStudents() {
        students.clear();
        File f = new File(studentFile);
        if (!f.exists()) return;
        Map<String, Student> uniqueStudents = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student student = Student.fromFileString(line);
                uniqueStudents.put(student.getStudentId(), student);
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        students.addAll(uniqueStudents.values());
    }
}

