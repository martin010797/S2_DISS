package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.Receptionist;
import Simulation.Simulator;

public class PaymentEnd extends Event{
    private double writingOrderStartTime;
    private Receptionist chosenReceptionist;

    public PaymentEnd(
            double time,
            Simulator simulationCore,
            Customer customer,
            double writingOrderStartTime,
            Receptionist chosenReceptionist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec platby";
        this.writingOrderStartTime = writingOrderStartTime;
        this.chosenReceptionist = chosenReceptionist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.paymentEndProcess(this);
    }

    public double getWritingOrderStartTime() {
        return writingOrderStartTime;
    }

    public Receptionist getChosenReceptionist() {
        return chosenReceptionist;
    }
}
