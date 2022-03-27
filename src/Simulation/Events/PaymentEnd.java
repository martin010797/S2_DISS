package Simulation.Events;

import Simulation.Simulator;

public class PaymentEnd extends Event{
    public PaymentEnd(int time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
