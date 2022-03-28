package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Simulator;

public class CustomerArrived extends Event{

    public CustomerArrived(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Prichod zakaznika";
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.customerArrivedProcessing(this);
    }
}
