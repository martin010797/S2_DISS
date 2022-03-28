package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Simulator;

public class MakeupEnd extends Event{

    public MakeupEnd(double time, Simulator simulationCore, Customer customer) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec licenia";
    }

    @Override
    public void execute() {

    }
}
