package Simulation;

import Gui.ISimDelegate;
import Simulation.Events.Event;
import Simulation.Events.TestingEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BeautySalonSimulator extends EventSimulator{

    private boolean maxSpeed;
    private int deltaT;
    private int sleepLength;

    private Queue<Event> receptionWaitingQueue;
    private Queue<Event> hairstyleWaitingQueue;
    private Queue<Event> makeupWaitingQueue;
    private Queue<Event> paymentWaitingQueue;

    public BeautySalonSimulator(int pNumberOfReplications, int lengthOfSimulation) {
        super(pNumberOfReplications);
        this.lengthOfSimulation = lengthOfSimulation;
        calendar.add(new TestingEvent(0,this));

        receptionWaitingQueue = new LinkedList<>();
        hairstyleWaitingQueue = new LinkedList<>();
        makeupWaitingQueue = new LinkedList<>();
        paymentWaitingQueue = new LinkedList<>();
        maxSpeed = false;
    }

    @Override
    public void doBeforeReplications() {

    }

    @Override
    public void doAfterReplications() {

    }

    @Override
    public void doBeforeReplication() {

    }

    @Override
    public void doAfterReplication() {

    }

    public void testing(){
        calendar.add(new TestingEvent(currentTime + 1,this));
    }

    public String getStatesOfSimulation(){
        String result = "";
        result += "Pocet ludi v radoch: -\n\t Rad pred recepciou: " + receptionWaitingQueue.size() + " \n\t" +
                "Rad pred upravou ucesu: " + hairstyleWaitingQueue.size() + " \n\t Rad pred licenim: "
                + makeupWaitingQueue.size() + "\n\tRad pred platenim: "+ paymentWaitingQueue.size()
                + "\nStavy personalu: - \nStavy zakaznikov: -";
        return result;
    }

    public void systemEventOccured(){

    }

    public boolean isMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(boolean maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setDeltaT(int deltaT) {
        this.deltaT = deltaT;
    }

    public void setSleepLength(int sleepLength) {
        this.sleepLength = sleepLength;
    }
}
