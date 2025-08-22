package ap.exercises.FinalProject;

public class Employee {
    private String name;
    private String employeeId;
    private String username;
    private String password;
    private boolean active;

    public Employee(String name, String employeeId, String username, String password) {
        this.name = name;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Employee ID: " + employeeId +
                " | Username: " + username +
                " | Active: " + active;
    }

    public String toFileString() {
        return name + "," + employeeId + "," + username + "," + password + "," + active;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        Employee e = new Employee(parts[0], parts[1], parts[2], parts[3]);
        e.setActive(Boolean.parseBoolean(parts[4]));
        return e;
    }
}


