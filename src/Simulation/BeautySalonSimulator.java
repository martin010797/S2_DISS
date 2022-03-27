package Simulation;

import Gui.ISimDelegate;
import Simulation.Events.Event;
import Simulation.Events.SystemEvent;
import Simulation.Events.TestingEvent;
import Simulation.Participants.Customer;
import Simulation.Participants.Hairstylist;
import Simulation.Participants.MakeUpArtist;
import Simulation.Participants.Receptionist;

import java.util.*;

public class BeautySalonSimulator extends EventSimulator{

    private Random seed;

    private boolean maxSpeed;
    private int deltaT;
    private int sleepLength;
    private int numberOfReceptionists;
    private int numberOfMakeupArtists;
    private int numberOfHairstylists;

    private Queue<Event> receptionWaitingQueue;
    private Queue<Event> hairstyleWaitingQueue;
    private Queue<Event> makeupWaitingQueue;
    private Queue<Event> paymentWaitingQueue;

    private List<Customer> listOfCustomersInSystem;
    private List<Hairstylist> listOfHairStylists;
    private List<MakeUpArtist> listOfMakeupArtists;
    private List<Receptionist> listOfReceptionists;

    //zoradene podla
    /*protected PriorityQueue<MakeUpArtist> freeMakeUpArtistsByWorktime;
    protected PriorityQueue<Receptionist> freeReceptionistsByWorktime;
    protected PriorityQueue<Hairstylist> freeHairstylistsByWorktime;*/

    public BeautySalonSimulator(int pNumberOfReplications, int lengthOfSimulation) {
        super(pNumberOfReplications);
        this.lengthOfSimulation = lengthOfSimulation;

        receptionWaitingQueue = new LinkedList<>();
        hairstyleWaitingQueue = new LinkedList<>();
        makeupWaitingQueue = new LinkedList<>();
        paymentWaitingQueue = new LinkedList<>();
        //TODO temp
        maxSpeed = false;

        listOfCustomersInSystem = new ArrayList<>();
        listOfHairStylists = new ArrayList<>();
        listOfMakeupArtists = new ArrayList<>();
        listOfReceptionists = new ArrayList<>();

        seed = new Random();
        numberOfReceptionists = 0;
        numberOfHairstylists = 0;
        numberOfMakeupArtists = 0;
    }

    @Override
    public void doBeforeReplications() {
        //TODO
        // pridanie prvej udalosti prichodu namiesto testovacej
        calendar.add(new TestingEvent(0,this));
        if (!maxSpeed){
            calendar.add(new SystemEvent(currentTime,this));
        }
        addPersonnel();
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

    private void addPersonnel(){
        listOfReceptionists.clear();
        listOfHairStylists.clear();
        listOfMakeupArtists.clear();
        for (int i = 0; i < numberOfReceptionists; i++){
            Receptionist receptionist = new Receptionist();
            listOfReceptionists.add(receptionist);
        }
        for (int i = 0; i < numberOfMakeupArtists; i++){
            MakeUpArtist makeUpArtist = new MakeUpArtist();
            listOfMakeupArtists.add(makeUpArtist);
        }
        for (int i = 0; i < numberOfHairstylists; i++){
            Hairstylist hairstylist = new Hairstylist();
            listOfHairStylists.add(hairstylist);
        }
        //TODO
        // mozno pridat vsade aj do priority queue podla poctu odpracovaneho casu ak to tak budem robit
    }

    public void testing(){
        calendar.add(new TestingEvent(currentTime + 1,this));
    }

    public String getStatesOfSimulation(){
        String result = "";
        result += "Pocet ludi v radoch: -\n\t Rad pred recepciou: " + receptionWaitingQueue.size() + " \n\t" +
                "Rad pred upravou ucesu: " + hairstyleWaitingQueue.size() + " \n\t Rad pred licenim: "
                + makeupWaitingQueue.size() + "\n\tRad pred platenim: "+ paymentWaitingQueue.size()
                + "\nStavy personalu: - \nStavy zakaznikov: -";
        return result;
    }

    public void systemEventOccured(){
        try {
            Thread.sleep(sleepLength);
        }catch (Exception e){
            e.printStackTrace();
        }
        double seconds = (double) deltaT/1000;
        calendar.add(new SystemEvent(currentTime+seconds,this));
    }

    public boolean isMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(boolean maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setDeltaT(int deltaT) {
        this.deltaT = deltaT;
    }

    public void setSleepLength(int sleepLength) {
        this.sleepLength = sleepLength;
    }

    public void setNumberOfReceptionists(int numberOfReceptionists) {
        this.numberOfReceptionists = numberOfReceptionists;
    }

    public void setNumberOfMakeupArtists(int numberOfMakeupArtists) {
        this.numberOfMakeupArtists = numberOfMakeupArtists;
    }

    public void setNumberOfHairstylists(int numberOfHairstylists) {
        this.numberOfHairstylists = numberOfHairstylists;
    }
}
