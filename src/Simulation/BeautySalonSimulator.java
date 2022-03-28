package Simulation;

import Generators.ExpGen;
import Simulation.Events.*;
import Simulation.Participants.*;

import java.util.*;

public class BeautySalonSimulator extends EventSimulator{

    private Random seed;
    private Random exponentialGeneratorBase;
    private ExpGen arrivalGenerator;
    private Random lengthOfOrderingGenerator;

    private boolean maxSpeed;
    private int deltaT;
    private int sleepLength;
    private int numberOfReceptionists;
    private int numberOfMakeupArtists;
    private int numberOfHairstylists;

    private int numberOfArrivedCustomers;
    private int numberOfServedCustomers;

    private Queue<Customer> receptionWaitingQueue;
    private Queue<Customer> hairstyleWaitingQueue;
    private Queue<Customer> makeupWaitingQueue;
    private Queue<Customer> paymentWaitingQueue;

    private ArrayList<Double> waitTimesInReceptionQueue;
    private ArrayList<Double> waitTimesInHairstyleQueue;
    private ArrayList<Double> waitTimesInMakeupQueue;
    private ArrayList<Double> waitTimesInPaymentQueue;
    private double numberOfCustomersInReceptionQueueChangedTime;
    private double numberOfCustomersInHairstyleQueueChangedTime;
    private double numberOfCustomersInMakeupQueueChangedTime;
    private double numberOfCustomersInPaymentQueueChangedTime;
    private double sumWaitTimeInReceptionQueue;
    private double sumWaitTimeInHairstyleQueue;
    private double sumWaitTimeInMakeupQueue;
    private double sumWaitTimeInPaymentQueue;


    private List<Customer> listOfCustomersInSystem;
    private List<Hairstylist> listOfHairStylists;
    private List<MakeUpArtist> listOfMakeupArtists;
    private List<Receptionist> listOfReceptionists;

    private boolean isSomeHairstylistFree;
    private boolean isSomeMakeupArtistsFree;
    private boolean isSomeReceptionistFree;

    public BeautySalonSimulator(int pNumberOfReplications, int lengthOfSimulation) {
        super(pNumberOfReplications);
        this.lengthOfSimulation = lengthOfSimulation;

        receptionWaitingQueue = new LinkedList<>();
        hairstyleWaitingQueue = new LinkedList<>();
        makeupWaitingQueue = new LinkedList<>();
        paymentWaitingQueue = new LinkedList<>();

        waitTimesInHairstyleQueue = new ArrayList<>();
        waitTimesInMakeupQueue = new ArrayList<>();
        waitTimesInPaymentQueue = new ArrayList<>();
        waitTimesInReceptionQueue = new ArrayList<>();

        //TODO temp
        maxSpeed = false;

        listOfCustomersInSystem = new ArrayList<>();
        listOfHairStylists = new ArrayList<>();
        listOfMakeupArtists = new ArrayList<>();
        listOfReceptionists = new ArrayList<>();

        numberOfReceptionists = 0;
        numberOfHairstylists = 0;
        numberOfMakeupArtists = 0;

        seed = new Random();
        exponentialGeneratorBase = new Random(seed.nextInt());
        arrivalGenerator = new ExpGen(exponentialGeneratorBase, 3600/8);
        lengthOfOrderingGenerator = new Random(seed.nextInt());

        numberOfArrivedCustomers = 0;
        numberOfServedCustomers = 0;
    }

    @Override
    public void doBeforeReplications() {
        receptionWaitingQueue.clear();
        hairstyleWaitingQueue.clear();
        makeupWaitingQueue.clear();
        paymentWaitingQueue.clear();
        waitTimesInHairstyleQueue.clear();
        waitTimesInMakeupQueue.clear();
        waitTimesInPaymentQueue.clear();
        waitTimesInReceptionQueue.clear();
        numberOfCustomersInReceptionQueueChangedTime = 0;
        numberOfCustomersInHairstyleQueueChangedTime = 0;
        numberOfCustomersInMakeupQueueChangedTime = 0;
        numberOfCustomersInPaymentQueueChangedTime = 0;
        sumWaitTimeInHairstyleQueue = 0;
        sumWaitTimeInMakeupQueue = 0;
        sumWaitTimeInPaymentQueue = 0;
        sumWaitTimeInReceptionQueue = 0;

        if (numberOfHairstylists > 0){
            isSomeHairstylistFree = true;
        }else {
            isSomeHairstylistFree = false;
        }
        if (numberOfMakeupArtists > 0){
            isSomeMakeupArtistsFree = true;
        }else {
            isSomeMakeupArtistsFree = false;
        }
        if (numberOfReceptionists > 0){
            isSomeReceptionistFree = true;
        }else {
            isSomeReceptionistFree = false;
        }
        listOfCustomersInSystem.clear();
        numberOfArrivedCustomers = 0;
        numberOfServedCustomers = 0;
        calendar.clear();
        lastProcessedEvent = null;
        currentTime = 0;

        double time = currentTime + arrivalGenerator.nextValue();
        Customer arrivedCustomer = new Customer(time);
        listOfCustomersInSystem.add(arrivedCustomer);
        calendar.add(new CustomerArrived(time, this, arrivedCustomer));
        calendar.add(new TestingEvent(0,this, null));
        if (!maxSpeed){
            calendar.add(new SystemEvent(currentTime,this, null));
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
    }

    public void customerArrivedProcessing(CustomerArrived event){
        numberOfArrivedCustomers++;
        if (isSomeReceptionistFree){
            PriorityQueue<Receptionist> availableReceptionists = new PriorityQueue<>();
            for (int i = 0; i < listOfReceptionists.size(); i++){
                Receptionist r = listOfReceptionists.get(i);
                if (!r.isWorking()){
                    availableReceptionists.add(r);
                }
            }
            if (availableReceptionists.size() == 1){
                isSomeReceptionistFree = false;
            }
            Receptionist chosenReceptionist = availableReceptionists.poll();
            chosenReceptionist.setWorking(true);
            calendar.add(
                    new WritingOrderBeginning(currentTime,this, event.getCustomer(), chosenReceptionist));
        }else {
            //priradenie hodnot pre zistovanie statistik o dlzke radu
            if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
                waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
            }else {
                Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
                waitTimesInReceptionQueue.set(
                        receptionWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
            }
            numberOfCustomersInReceptionQueueChangedTime = currentTime;
            //postavenie do radu
            event.getCustomer().setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_ORDERING);
            receptionWaitingQueue.add(event.getCustomer());
        }

        double time = currentTime + arrivalGenerator.nextValue();
        Customer arrivedCustomer = new Customer(time);
        listOfCustomersInSystem.add(arrivedCustomer);

        calendar.add(new CustomerArrived(time, this, arrivedCustomer));
        lastProcessedEvent = event;
    }

    public void customerOrderProcessingStarted(WritingOrderBeginning event){
        //planovanie ukoncenia objednavky
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.ORDERING);
        double lengthOfOrdering = 200 + lengthOfOrderingGenerator.nextDouble(-120,120);
        calendar.add(new WritingOrderEnd(
                currentTime + lengthOfOrdering,
                this,
                customer,
                event.getTime(),
                event.getChosenReceptionist()));
        //TODO
        //spravit vyber objednavky
    }

    public void customerOrderProcessingEnded(WritingOrderEnd event){
        Customer customer = event.getCustomer();
        //temp aby bolo vidno len zmenu ze uz odisiel z tadeto
        customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_HAIRSTYLE);
        Receptionist receptionist = event.getChosenReceptionist();

        //update pre recepcnu
        receptionist.setWorking(false);
        isSomeReceptionistFree = true;
        Double workedTime = receptionist.getWorkedTimeTogether();
        receptionist.setWorkedTimeTogether(workedTime + currentTime - event.getWritingOrderStartTime());
        listOfCustomersInSystem.remove(customer);
        //pokial je niekto v rade nech si ho priradi niekto z recepcie
        if (!receptionWaitingQueue.isEmpty()){
            //priradenie hodnot pre zistovanie statistik o dlzke radu
            if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
                waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
            }else {
                Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
                waitTimesInReceptionQueue.set(
                        receptionWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
            }
            numberOfCustomersInReceptionQueueChangedTime = currentTime;

            Customer c = receptionWaitingQueue.poll();
            //pridanie novej udalosti pre vybavovanie objednavky do kalendaru
            PriorityQueue<Receptionist> availableReceptionists = new PriorityQueue<>();
            for (int i = 0; i < listOfReceptionists.size(); i++){
                Receptionist r = listOfReceptionists.get(i);
                if (!r.isWorking()){
                    availableReceptionists.add(r);
                }
            }
            if (availableReceptionists.size() == 1){
                isSomeReceptionistFree = false;
            }
            Receptionist chosenReceptionist = availableReceptionists.poll();
            chosenReceptionist.setWorking(true);
            calendar.add(
                    new WritingOrderBeginning(currentTime,this, c, chosenReceptionist));
            sumWaitTimeInReceptionQueue += currentTime - c.getArriveTime();
        }
    }

    public void testing(TestingEvent event){
        calendar.add(new TestingEvent(currentTime + 1,this, null));
        lastProcessedEvent = event;
    }

    public String getStatesOfSimulation(){
        String result = "";
        result += "Pocet ludi v radoch: -\n  Rad pred recepciou: " + receptionWaitingQueue.size() + " \n  " +
                "Rad pred upravou ucesu: " + hairstyleWaitingQueue.size() + " \n  Rad pred licenim: "
                + makeupWaitingQueue.size() + "\n  Rad pred platenim: "+ paymentWaitingQueue.size()
                + "\nPocet prichodov zakaznikov: " + numberOfArrivedCustomers +
                "\nPocet obsluzenych zakaznikov: " + numberOfServedCustomers +" " +
                "\nStavy personalu: ";
        result += "\n  Recepcia:";
        for (int i = 0; i < listOfReceptionists.size(); i++){
            Receptionist r = listOfReceptionists.get(i);
            String isWorkig = "";
            if (r.isWorking()){
                isWorkig = "Ano";
            }else {
                isWorkig = "Nie";
            }
            result += "\n    Recepcny c." + (i+1) + ":" + "\n      Odpracovany cas: " +
                    getTotalTimeFromSeconds(r.getWorkedTimeTogether()) + "\n      Pracuje: " + isWorkig;
        }
        result += "\n  Kadernicky:";
        for (int i = 0; i < listOfHairStylists.size(); i++){
            Hairstylist h = listOfHairStylists.get(i);
            String isWorkig = "";
            if (h.isWorking()){
                isWorkig = "Ano";
            }else {
                isWorkig = "Nie";
            }
            result += "\n    Kadernicka c." + (i+1) + ":" + "\n      Odpracovany cas: " +
                    getTotalTimeFromSeconds(h.getWorkedTimeTogether()) + "\n      Pracuje: " + isWorkig;
        }
        result += "\n  Kozmeticky:";
        for (int i = 0; i < listOfMakeupArtists.size(); i++){
            MakeUpArtist m = listOfMakeupArtists.get(i);
            String isWorkig = "";
            if (m.isWorking()){
                isWorkig = "Ano";
            }else {
                isWorkig = "Nie";
            }
            result += "\n    Kozmeticka c." + (i+1) + ":" + "\n      Odpracovany cas: " +
                    getTotalTimeFromSeconds(m.getWorkedTimeTogether()) + "\n      Pracuje: " + isWorkig;
        }
        result += "\nStavy zakaznikov v systeme: ";
        for (int i = 0; i < listOfCustomersInSystem.size(); i++){
            Customer c = listOfCustomersInSystem.get(i);
            if (c.getArriveTime() < currentTime){
                result += "\n  Zakaznik: \n    Cas prichodu: " + converTimeOfSystem(c.getArriveTime()) +
                        "\n    Aktualne miesto v systeme: " +
                        convertCurrentPosition(c.getCurrentPosition());
            }
        }
        return result;
    }

    private String getTotalTimeFromSeconds(double pSeconds){
        int seconds = (int)pSeconds % 60;
        int minutes = ((int)pSeconds / 60) % 60;
        if (minutes == 59 && seconds == 59){
            minutes = minutes;
        }
        int hours = ((int)pSeconds / 60 / 60) % 24;
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
        return time;
    }

    private String convertCurrentPosition(CurrentPosition currentPosition){
        switch (currentPosition){
            case PAYING:{
                return "Platba";
            }
            case ARRIVED:{
                return "Prichod";
            }
            case MAKE_UP:{
                return "Licenie";
            }
            case ORDERING:{
                return "Zadavanie objednavky";
            }
            case HAIR_STYLING:{
                return "Uprava ucesu";
            }
            case CLEANING_SKIN:{
                return "Cistenie pleti";
            }
            case IN_QUEUE_FOR_PAY:{
                return "Rad platba";
            }
            case IN_QUEUE_FOR_MAKEUP:{
                return "Rad licenie";
            }
            case IN_QUEUE_FOR_ORDERING:{
                return "Rad pre objednavku";
            }
            case IN_QUEUE_FOR_HAIRSTYLE:{
                return "Rad uprava ucesu";
            }
            default:{
                return "Nezname";
            }
        }
    }

    public String getCalendar(){
        String result = "";
        PriorityQueue<Event> cal = new PriorityQueue<>(calendar);
        int size = cal.size();
        for (int i = 0; i < size; i++){
            Event e = cal.poll();
            //if (!(e instanceof SystemEvent))
            result += converTimeOfSystem(e.getTime())+ "\t" + e.getNameOfTheEvent() + "\n";
        }
        return result;
    }

    public void systemEventOccured(){
        try {
            Thread.sleep(sleepLength);
        }catch (Exception e){
            e.printStackTrace();
        }
        double seconds = (double) deltaT/1000;
        calendar.add(new SystemEvent(currentTime+seconds,this, null));
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

    public int getNumberOfArrivedCustomers() {
        return numberOfArrivedCustomers;
    }

    public int getNumberOfServedCustomers() {
        return numberOfServedCustomers;
    }
}
