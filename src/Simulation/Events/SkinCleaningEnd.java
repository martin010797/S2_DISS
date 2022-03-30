package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Participants.MakeUpArtist;
import Simulation.Simulator;

public class SkinCleaningEnd extends Event{
    private double cleaningStartTime;
    private MakeUpArtist chosenMakeupArtist;

    public SkinCleaningEnd(
            double time,
            Simulator simulationCore,
            Customer customer,
            double cleaningStartTime,
            MakeUpArtist chosenMakeUpArtist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec cistenia pleti";
        this.cleaningStartTime = cleaningStartTime;
        this.chosenMakeupArtist = chosenMakeUpArtist;
    }

    @Override
    public void execute() {

    }

    public double getCleaningStartTime() {
        return cleaningStartTime;
    }

    public MakeUpArtist getChosenMakeupArtist() {
        return chosenMakeupArtist;
    }
}
