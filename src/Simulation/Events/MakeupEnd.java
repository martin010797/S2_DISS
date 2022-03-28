package Simulation.Events;

import Simulation.Simulator;

public class MakeupEnd extends Event{
    public MakeupEnd(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Koniec licenia";
    }

    @Override
    public void execute() {

    }
}
