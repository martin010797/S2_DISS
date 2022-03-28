package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class MakeupBeginning extends Event{

    public MakeupBeginning(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok licenia";
    }

    @Override
    public void execute() {

    }
}
