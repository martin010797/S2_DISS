package Simulation.Events;

import Simulation.BeautySalonSimulator;
import Simulation.Participants.Customer;
import Simulation.Participants.MakeUpArtist;
import Simulation.Simulator;

public class SkinCleaningBeginning extends Event{
    private MakeUpArtist chosenMakeupArtist;

    public SkinCleaningBeginning(double time, Simulator simulationCore, Customer customer, MakeUpArtist makeUpArtist) {
        super(time, simulationCore, customer);
        nameOfTheEvent = "Zaciatok cistenia pleti";
        chosenMakeupArtist = makeUpArtist;
    }

    @Override
    public void execute() {
        BeautySalonSimulator simulator = (BeautySalonSimulator) simulationCore;
        simulator.skinCleaningBeginningProcess(this);
    }
}
