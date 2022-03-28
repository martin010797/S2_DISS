package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class WritingOrderBeginning extends Event{

    public WritingOrderBeginning(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok zapisu objednavky";
    }

    @Override
    public void execute() {

    }
}
