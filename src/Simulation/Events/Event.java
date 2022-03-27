package Simulation.Events;

import Simulation.Simulator;

public abstract class Event implements Comparable<Event>{
    protected double time;
    protected Simulator simulationCore;

    public Event(double time, Simulator simulationCore) {
        this.time = time;
        this.simulationCore = simulationCore;
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
}
