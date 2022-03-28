package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Simulator;

public class SystemEvent extends Event{
    public SystemEvent(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Systemova udalost";
    }

    @Override
    public void execute() {
        BeautySalonSimulator beautySalonSimulator = (BeautySalonSimulator) simulationCore;
        beautySalonSimulator.systemEventOccured();
    }
}
