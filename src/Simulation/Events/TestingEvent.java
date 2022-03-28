package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Simulator;

public class TestingEvent extends Event{

    public TestingEvent(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Testovacia udalost";
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.testing(this);
    }
}
