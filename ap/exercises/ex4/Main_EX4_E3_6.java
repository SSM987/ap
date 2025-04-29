package ap.exercises.ex4;

public class Main_EX4_E3_6 {
    public static void main(String[] args) {
        HallWayLight2 hallWayLight2 = new HallWayLight2();
        System.out.println("First switch status: "+hallWayLight2.getSwitchState(1));
        System.out.println("Second switch status: "+hallWayLight2.getSwitchState(2));
        System.out.println("Lamp status: "+hallWayLight2.getLampState());
        hallWayLight2.toggleSwitch(1);
        System.out.println("After change first switch status");
        System.out.println("First switch status: "+hallWayLight2.getSwitchState(1));
        System.out.println("Second switch status: "+hallWayLight2.getSwitchState(2));
        System.out.println("Lamp status: "+hallWayLight2.getLampState());
        hallWayLight2.toggleSwitch(2);
        System.out.println("After change second switch status");
        System.out.println("First switch status: "+hallWayLight2.getSwitchState(1));
        System.out.println("Second switch status: "+hallWayLight2.getSwitchState(2));
        System.out.println("Lamp status: "+hallWayLight2.getLampState());
        hallWayLight2.toggleSwitch(1);
        System.out.println("After change first switch status");
        System.out.println("First switch status: "+hallWayLight2.getSwitchState(1));
        System.out.println("Second switch status: "+hallWayLight2.getSwitchState(2));
        System.out.println("Lamp status: "+hallWayLight2.getLampState());
    }
}
