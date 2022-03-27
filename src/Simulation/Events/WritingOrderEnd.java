package Simulation.Events;

import Simulation.Simulator;

public class WritingOrderEnd extends Event{
    public WritingOrderEnd(int time, Simulator simulationCore) {
        super(time, simulationCore);
    }

    @Override
    public void execute() {

    }
}
