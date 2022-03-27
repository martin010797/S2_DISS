package Simulation;

import Gui.ISimDelegate;
import Simulation.Events.TestingEvent;

import java.util.ArrayList;
import java.util.List;

public class BeautySalonSimulator extends EventSimulator{

    public BeautySalonSimulator(int pNumberOfReplications, int lengthOfSimulation) {
        super(pNumberOfReplications);
        this.lengthOfSimulation = lengthOfSimulation;
        calendar.add(new TestingEvent(0,this));
    }

    @Override
    public void doBeforeReplications() {

    }

    @Override
    public void doAfterReplications() {

    }

    @Override
    public void doBeforeReplication() {

    }

    @Override
    public void doAfterReplication() {

    }

    public void testing(){
        calendar.add(new TestingEvent(currentTime + 1,this));
    }
}
