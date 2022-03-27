package Simulation;

public abstract class Simulator {
    protected int numberOfReplications;
    protected Thread thread;
    protected boolean isPaused;

    public Simulator(int pNumberOfReplications) {
        numberOfReplications = pNumberOfReplications;
        isPaused = false;
    }

    public abstract void doBeforeReplications();
    public abstract void doAfterReplications();
    public abstract void replication();
    public abstract void doBeforeReplication();
    public abstract void doAfterReplication();

    public void simulate(){
        if (thread != null && thread.isAlive() && !isPaused){
            return;
        }
        if (isPaused){
            isPaused = false;
            return;
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doBeforeReplications();
                for(int i = 0; i < numberOfReplications; i++){
                    doBeforeReplication();
                    replication();
                    doAfterReplication();
                }
                doAfterReplications();
            }
        });
        thread.start();
    }

    public int getNumberOfReplications() {
        return numberOfReplications;
    }

    public void setNumberOfReplications(int numberOfReplications) {
        this.numberOfReplications = numberOfReplications;
    }

    public void stopSimulation(){
        if (thread != null && thread.isAlive()){
            thread.stop();
            isPaused = false;
        }
    }

    public boolean setPaused(boolean paused) {
        if (thread != null && thread.isAlive()){
            isPaused = paused;
            return true;
        }
        return false;
    }
}
