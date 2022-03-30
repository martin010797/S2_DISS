package Simulation.Events;

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

    }

    public double getHairstylingStartTime() {
        return hairstylingStartTime;
    }

    public Hairstylist getChosenHairstylist() {
        return chosenHairstylist;
    }
}
