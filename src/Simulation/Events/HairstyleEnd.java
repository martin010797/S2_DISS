package Simulation.Events;

import Simulation.Simulator;

public class HairstyleEnd extends Event{
    public HairstyleEnd(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Koniec upravy ucesu";
    }

    @Override
    public void execute() {

    }
}
