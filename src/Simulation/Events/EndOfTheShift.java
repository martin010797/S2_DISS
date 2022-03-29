package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class EndOfTheShift extends Event{
    public EndOfTheShift(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
    }

    @Override
    public void execute() {

    }
}
