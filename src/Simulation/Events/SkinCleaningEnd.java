package Simulation.Events;

import Simulation.Simulator;

public class SkinCleaningEnd extends Event{
    public SkinCleaningEnd(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Koniec cistenia pleti";
    }

    @Override
    public void execute() {

    }
}
