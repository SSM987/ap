package ap.exercises.ex4;

public class Employee {
    private String name;
    private float salary;
    public Employee (String name,float salary){
        this.name=name;
        this.salary=salary;
    }
    public float getSalary(){
        return salary;
    }
    public String getName(){
        return name;
    }
    public void raiseSalary(double percent){
        salary+= (float) (salary*percent/100);
    }
}
