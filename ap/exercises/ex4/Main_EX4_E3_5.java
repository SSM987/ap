package ap.exercises.ex4;

public class Main_EX4_E3_5 {
    public static void main(String[] args) {
        HallWayLight wayLight = new HallWayLight();
        wayLight.getFirstSwitchState();
        wayLight.getSecondSwitchState();
        wayLight.status();
        if (wayLight.getLampState() == 0)
            System.out.println("Test 1 passed");
        else
            System.out.println("Test 1 failed");
        wayLight.toggleFirstSwitch();
        wayLight.status();
        if (wayLight.getLampState() == 1)
            System.out.println("Test 2 passed");
        else
            System.out.println("Test 2 failed");
        wayLight.toggleSecondSwitch();
        wayLight.status();
        if (wayLight.getLampState() == 0)
            System.out.println("Test 3 passed");
        else
            System.out.println("Test 3 failed");
        wayLight.toggleFirstSwitch();
        wayLight.status();
        if (wayLight.getLampState() == 1)
            System.out.println("Test 4 passed");
        else
            System.out.println("Test 4 failed");
    }
}
