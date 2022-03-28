package Simulation.Participants;

public class Personnel extends Person implements Comparable<Personnel>{
    protected double workedTimeTogether;
    protected boolean isWorking;
    //protected double busyUntil;

    @Override
    public int compareTo(Personnel o) {
        if (this.workedTimeTogether < o.workedTimeTogether){
            return -1;
        }else if(this.workedTimeTogether > o.workedTimeTogether){
            return 1;
        }else {
            return 0;
        }
    }
}
