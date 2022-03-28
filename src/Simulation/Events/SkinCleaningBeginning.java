package Simulation.Events;

import Simulation.Simulator;

public class SkinCleaningBeginning extends Event{
    public SkinCleaningBeginning(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Zaciatok cistenia pleti";
    }

    @Override
    public void execute() {

    }
}
