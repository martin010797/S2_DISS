package Simulation.Events;

import Simulation.Simulator;

public class PaymentBeginning extends Event{
    public PaymentBeginning(double time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
