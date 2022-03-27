package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Simulator;

public class SystemEvent extends Event{
    public SystemEvent(int time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {
        BeautySalonSimulator beautySalonSimulator = (BeautySalonSimulator) simulationCore;
        beautySalonSimulator.systemEventOccured();
    }
}
