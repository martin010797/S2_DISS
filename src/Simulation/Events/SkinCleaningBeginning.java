package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class SkinCleaningBeginning extends Event{

    public SkinCleaningBeginning(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok cistenia pleti";
    }

    @Override
    public void execute() {

    }
}
