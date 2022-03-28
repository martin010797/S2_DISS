package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Simulator;

public class SystemEvent extends Event{

    public SystemEvent(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Systemova udalost";
    }

    @Override
    public void execute() {
        BeautySalonSimulator beautySalonSimulator = (BeautySalonSimulator) simulationCore;
        beautySalonSimulator.systemEventOccured();
    }
}
