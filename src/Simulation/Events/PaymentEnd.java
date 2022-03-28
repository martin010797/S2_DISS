package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class PaymentEnd extends Event{

    public PaymentEnd(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec platby";
    }

    @Override
    public void execute() {

    }
}
