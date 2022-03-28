package Simulation.Events;

import Simulation.Simulator;

public class HairstyleBeginning extends Event{
    public HairstyleBeginning(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Zaciatok upravy ucesu";
    }

    @Override
    public void execute() {

    }
}
