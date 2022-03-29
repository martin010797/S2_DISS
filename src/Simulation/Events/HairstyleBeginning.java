package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.Hairstylist;
import Simulation.Simulator;

public class HairstyleBeginning extends Event{
    private Hairstylist chosenHairstylist;

    public HairstyleBeginning(double time, Simulator simulationCore, Customer customer, Hairstylist hairstylist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok upravy ucesu";
        chosenHairstylist = hairstylist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.hairstyleBeginningProcess(this);
    }
}
