package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class WritingOrderEnd extends Event{

    public WritingOrderEnd(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec zapisu objednavky";
    }

    @Override
    public void execute() {

    }
}
