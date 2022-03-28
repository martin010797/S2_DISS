package Simulation.Participants;

public class Customer extends Person{
    private double arriveTime;
    private CurrentPosition currentPosition;

    private boolean hairstyle;
    private boolean makeup;
    private boolean cleaning;

    public Customer(double arriveTime) {
        this.arriveTime = arriveTime;
        currentPosition = CurrentPosition.ARRIVED;
        hairstyle = false;
        makeup = false;
        cleaning = false;
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

    public boolean isHairstyle() {
        return hairstyle;
    }

    public void setHairstyle(boolean hairstyle) {
        this.hairstyle = hairstyle;
    }

    public boolean isMakeup() {
        return makeup;
    }

    public void setMakeup(boolean makeup) {
        this.makeup = makeup;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }
}
