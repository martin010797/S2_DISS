package Simulation.Participants;

public class Customer implements Comparable<Customer>{
    private double arriveTime;
    private CurrentPosition currentPosition;

    private boolean hairstyle;
    private boolean makeup;
    private boolean cleaning;

    private boolean isPaying;

    public Customer(double arriveTime){
        this.arriveTime = arriveTime;
        currentPosition = CurrentPosition.ARRIVED;
        hairstyle = false;
        makeup = false;
        cleaning = false;
        isPaying = false;
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

    public boolean isPaying() {
        return isPaying;
    }

    public void setPaying(boolean paying) {
        isPaying = paying;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.isPaying && !o.isPaying){
            return -1;
        }else if(!this.isPaying && o.isPaying){
            return 1;
        }else {
            return 0;
        }
    }
}
