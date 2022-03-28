package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.Receptionist;
import Simulation.Simulator;

public class WritingOrderBeginning extends Event{
    private Receptionist chosenReceptionist;

    public WritingOrderBeginning(double time, Simulator simulationCore, Customer customer, Receptionist chosenReceptionist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok zapisu objednavky";
        this.chosenReceptionist = chosenReceptionist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.customerOrderProcessingStarted(this);
    }

    public Receptionist getChosenReceptionist() {
        return chosenReceptionist;
    }
}
