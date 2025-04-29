package ap.exercises.ex4;

public class Main_EX4_E3_12 {
    public static void main(String[] args) {
        Employee employee = new Employee("Harry",43000);
        System.out.println("Name: "+employee.getName());
        System.out.println("Salary: "+employee.getSalary());
        employee.raiseSalary(25);
        System.out.println("Salary after raise: "+employee.getSalary());
    }
}
