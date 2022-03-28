package Simulation.Events;

import Simulation.Simulator;

public class PaymentEnd extends Event{
    public PaymentEnd(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Koniec platby";
    }

    @Override
    public void execute() {

    }
}
