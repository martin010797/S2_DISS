package Simulation.Events;

import Simulation.Participants.Customer;
import Simulation.Participants.Hairstylist;
import Simulation.Participants.MakeUpArtist;
import Simulation.Simulator;

public class MakeupEnd extends Event{
    private double makeupStartTime;
    private MakeUpArtist chosenMakeupArtist;

    public MakeupEnd(
            double time,
            Simulator simulationCore,
            Customer customer,
            double makeupStartTime,
            MakeUpArtist chosenMakeUpArtist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Koniec licenia";
        this.makeupStartTime = makeupStartTime;
        this.chosenMakeupArtist = chosenMakeUpArtist;
    }

    @Override
    public void execute() {

    }

    public double getMakeupStartTime() {
        return makeupStartTime;
    }

    public MakeUpArtist getChosenMakeupArtist() {
        return chosenMakeupArtist;
    }
}
