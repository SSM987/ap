package ap.exercises.FinalProject;

public class Student {
    private String name;
    private String studentId;
    private String username;
    private String password;
    private boolean active;

    public Student(String name, String studentId, String username, String password) {
        this.name = name;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + username +
                " | Active: " + active;
    }

    public String toFileString() {
        return name + "," + studentId + "," + username + "," + password + "," + active;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        Student s = new Student(parts[0], parts[1], parts[2], parts[3]);
        s.setActive(Boolean.parseBoolean(parts[4]));
        return s;
    }
}

