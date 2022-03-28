package Simulation.Participants;

public class Customer extends Person{
    private double arriveTime;
    private CurrentPosition currentPosition;

    public Customer(double arriveTime) {
        this.arriveTime = arriveTime;
        currentPosition = CurrentPosition.ARRIVED;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public double getArriveTime() {
        return arriveTime;
    }
}
