package ap.exercises.ex4;

public class HallWayLight {
    private boolean firstSwitch;
    private boolean secondSwitch;

    public HallWayLight() {
        firstSwitch = false;
        secondSwitch = false;
    }

    public int getFirstSwitchState() {
        if (firstSwitch)
            return 1;
        else
            return 0;
    }

    public int getSecondSwitchState() {
        if (secondSwitch)
            return 1;
        else
            return 0;
    }

    public int getLampState() {
        if (firstSwitch != secondSwitch)
            return 1;
        else
            return 0;
    }

    public void toggleFirstSwitch() {
        firstSwitch = !firstSwitch;
    }

    public void toggleSecondSwitch() {
        secondSwitch = !secondSwitch;
    }

    public void status() {
        System.out.println("First switch:" + (firstSwitch ? "up" : "down"));
        System.out.println("Second switch:" + (secondSwitch ? "up" : "down"));
        System.out.println("Lamp:" + (getLampState() == 1 ? "on" : "off"));
    }
}
