package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public abstract class Event implements Comparable<Event>{
    protected double time;
    protected Simulator simulationCore;
    protected String nameOfTheEvent;
    protected Customer customer;

    public Event(double time, Simulator simulationCore, Customer customer) {
        this.time = time;
        this.simulationCore = simulationCore;
        this.customer = customer;
    }

    public abstract void execute();

    @Override
    public int compareTo(Event o) {
        if (this.time < o.time){
            return -1;
        }else if(this.time > o.time){
            return 1;
        }else {
            return 0;
        }
        //return this.time - o.time;
    }

    public double getTime() {
        return time;
    }

    public String getNameOfTheEvent() {
        return nameOfTheEvent;
    }
}
