package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class SkinCleaningEnd extends Event{

    public SkinCleaningEnd(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec cistenia pleti";
    }

    @Override
    public void execute() {

    }
}
