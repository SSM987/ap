package ap.exercises.ex4;

public class HallWayLight2 {
    private boolean firstSwitch;
    private boolean secondSwitch;
    private boolean lamp;

public HallWayLight2() {
    this.firstSwitch=false;
    this.secondSwitch=false;
    this.lamp=false;
}
public int getSwitchState(int switchNum) {
    if (switchNum == 1)
        return firstSwitch ? 1 : 0;
    else
        return secondSwitch ? 1 : 0;
}
public int getLampState(){
    return lamp?1:0;
}
public void toggleSwitch(int switchNum){
    if(switchNum==1)
        firstSwitch=!firstSwitch;
    else
        secondSwitch=!secondSwitch;
    if(firstSwitch==secondSwitch)
        lamp=false;
    else
        lamp=true;
}
}
