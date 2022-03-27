package Simulation.Events;

import Simulation.Simulator;

public abstract class Event implements Comparable<Event>{
    protected int time;
    protected Simulator simulationCore;

    public Event(int time, Simulator simulationCore) {
        this.time = time;
        this.simulationCore = simulationCore;
    }

    public abstract void execute();

    @Override
    public int compareTo(Event o) {
        return this.time - o.time;
    }

    public int getTime() {
        return time;
    }
}
