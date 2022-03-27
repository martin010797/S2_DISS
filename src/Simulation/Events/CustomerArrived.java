package Simulation.Events;

import Simulation.Simulator;

public class CustomerArrived extends Event{
    public CustomerArrived(double time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
