package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.Hairstylist;
import Simulation.Participants.Receptionist;
import Simulation.Simulator;

public class HairstyleEnd extends Event{
    private double hairstylingStartTime;
    private Hairstylist chosenHairstylist;

    public HairstyleEnd(
            double time,
            Simulator simulationCore,
            Customer customer,
            double hairstylingStartTime,
            Hairstylist chosenHairstylist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec upravy ucesu";
        this.hairstylingStartTime = hairstylingStartTime;
        this.chosenHairstylist = chosenHairstylist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.hairstyleEndProcess(this);
    }

    public double getHairstylingStartTime() {
        return hairstylingStartTime;
    }

    public Hairstylist getChosenHairstylist() {
        return chosenHairstylist;
    }
}
