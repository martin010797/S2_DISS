package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Simulator;

public class TestingEvent extends Event{

    public TestingEvent(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Testovacia udalost";
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.testing(this);
    }
}
