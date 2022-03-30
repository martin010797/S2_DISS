package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Participants.Receptionist;
import Simulation.Simulator;

public class PaymentBeginning extends Event{
    private Receptionist chosenReceptionist;


    public PaymentBeginning(double time, Simulator simulationCore, Customer customer, Receptionist receptionist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok platby";
        chosenReceptionist = receptionist;
    }

    @Override
    public void execute() {

    }

    public Receptionist getChosenReceptionist() {
        return chosenReceptionist;
    }
}
