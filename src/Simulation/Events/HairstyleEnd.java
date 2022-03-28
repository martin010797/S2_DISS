package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class HairstyleEnd extends Event{


    public HairstyleEnd(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec upravy ucesu";
    }

    @Override
    public void execute() {

    }
}
