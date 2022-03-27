package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Simulator;

public class TestingEvent extends Event{

    public TestingEvent(int time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.testing();
    }
}
