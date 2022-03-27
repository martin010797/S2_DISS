package Simulation.Events;

import Simulation.Simulator;

public class PaymentEnd extends Event{
    public PaymentEnd(double time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
