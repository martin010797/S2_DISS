package Simulation.Events;

import Simulation.Simulator;

public class PaymentBeginning extends Event{
    public PaymentBeginning(int time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
