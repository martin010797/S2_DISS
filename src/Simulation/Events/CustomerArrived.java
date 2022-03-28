package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Simulator;

public class CustomerArrived extends Event{
    public CustomerArrived(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Prichod zakaznika";
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.customerArrivedProcessing();
    }
}
