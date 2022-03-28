package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class PaymentBeginning extends Event{

    public PaymentBeginning(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok platby";
    }

    @Override
    public void execute() {

    }
}
