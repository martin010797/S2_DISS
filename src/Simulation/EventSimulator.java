package Simulation;

import Gui.ISimDelegate;
import Simulation.Events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class EventSimulator extends Simulator{
    protected double currentTime;
    protected PriorityQueue<Event> calendar;
    protected int lengthOfSimulation;

    private List<ISimDelegate> delegates;

    public EventSimulator(int pNumberOfReplications) {
        super(pNumberOfReplications);
        currentTime = 0;
        this.calendar = new PriorityQueue<>();
        delegates = new ArrayList<>();
    }

    /*@Override
    public void simulate() {
        super.simulate();
    }*/

    @Override
    public void replication() {
        while (!calendar.isEmpty() && (calendar.peek().getTime()) <= lengthOfSimulation){
            while (isPaused){
                try {
                    Thread.sleep(300);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Event event = calendar.poll();
            currentTime = event.getTime();
            event.execute();
            refreshGui();
        }
    }

    private void refreshGui(){
        for (ISimDelegate delegate : delegates) {
            delegate.refresh(this);
        }
    }

    public void registerDelegate(ISimDelegate delegate){
        delegates.add(delegate);
    }

    public String getCurrentTime() {
        int seconds = (int)currentTime % 60;
        int minutes = ((int)currentTime / 60) % 60;
        if (minutes == 59 && seconds == 59){
            minutes = minutes;
        }
        int hours = (9 + (int)currentTime / 60 / 60) % 24;
        String time = "";
        if (hours < 10){
            time += "0"+ hours + ":";
        }else {
            time += hours + ":";
        }
        if (minutes < 10){
            time += "0"+ minutes + ":";
        }else {
            time += minutes + ":";
        }
        if (seconds < 10){
            time += "0"+ seconds;
        }else {
            time += seconds;
        }
        //time = hours + ":" + minutes + ":" + seconds;
        return time;
    }

    public int getLengthOfSimulation() {
        return lengthOfSimulation;
    }

    public void setLengthOfSimulation(int lengthOfSimulation) {
        this.lengthOfSimulation = lengthOfSimulation;
    }
}
