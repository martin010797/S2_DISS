package Simulation.Events;

import Simulation.Simulator;

public class MakeupBeginning extends Event{
    public MakeupBeginning(double time, Simulator simulationCore) {
        super(time, simulationCore);
        nameOfTheEvent = "Zaciatok licenia";
    }

    @Override
    public void execute() {

    }
}
