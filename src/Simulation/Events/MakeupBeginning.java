package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.MakeUpArtist;
import Simulation.Simulator;

public class MakeupBeginning extends Event{
    private MakeUpArtist chosenMakeupArtist;

    public MakeupBeginning(double time, Simulator simulationCore, Customer customer, MakeUpArtist makeUpArtist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok licenia";
        chosenMakeupArtist = makeUpArtist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.makeupBeginningProcess(this);
    }
}
