package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.Receptionist;
import Simulation.Simulator;

public class WritingOrderEnd extends Event{
    private double writingOrderStartTime;
    private Receptionist chosenReceptionist;

    public WritingOrderEnd(
            double time,
            Simulator simulationCore,
            Customer customer,
            double writingOrderStartTime,
            Receptionist chosenReceptionist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec zapisu objednavky";
        this.writingOrderStartTime = writingOrderStartTime;
        this.chosenReceptionist = chosenReceptionist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.customerOrderProcessingEnded(this);
    }

    public Receptionist getChosenReceptionist() {
        return chosenReceptionist;
    }

    public double getWritingOrderStartTime() {
        return writingOrderStartTime;
    }
}
