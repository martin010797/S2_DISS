package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class HairstyleBeginning extends Event{

    public HairstyleBeginning(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok upravy ucesu";
    }

    @Override
    public void execute() {

    }
}
