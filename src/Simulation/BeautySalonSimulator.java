package Simulation;

import Generators.EmpGen;
import Generators.EmpiricalObject;
import Generators.ExpGen;
import Generators.Triangular;
import Simulation.Events.*;
import Simulation.Participants.*;

import java.util.*;

public class BeautySalonSimulator extends EventSimulator{

    private Random seed;
    private Random exponentialGeneratorBase;
    private ExpGen arrivalGenerator;
    private Random lengthOfOrderingGenerator;
    private Random hairstyleMakeupServicesGenerator;
    private Random cleaningChoiceGenerator;
    private Triangular cleaningTriangularGenerator;
    private Random makeupTypeGenerator;
    private Random simpleMakeupGenerator;
    private Random complicatedMakeupGenerator;
    private Random hairstyleTypeGenerator;
    private Random simpleHairstyleGenerator;
    private EmpGen complicatedHairstyleGenerator;
    private EmpGen weddingHairstyleGenerator;
    private Random paymentGenerator;

    private int deltaT;
    private int sleepLength;
    private int numberOfReceptionists;
    private int numberOfMakeupArtists;
    private int numberOfHairstylists;

    private boolean finished;

    private int numberOfArrivedCustomers;
    private int numberOfServedCustomers;
    private int numberOfLeavingCustomers;

    private PriorityQueue<Customer> receptionWaitingQueue;
    private Queue<Customer> hairstyleWaitingQueue;
    private Queue<Customer> makeupWaitingQueue;

    private ArrayList<Double> waitTimesInReceptionQueue;
    private ArrayList<Double> waitTimesInHairstyleQueue;
    private ArrayList<Double> waitTimesInMakeupQueue;
    private ArrayList<Double> waitTimesInPaymentQueue;
    private double numberOfCustomersInReceptionQueueChangedTime;
    private double numberOfCustomersInHairstyleQueueChangedTime;
    private double numberOfCustomersInMakeupQueueChangedTime;
    private double sumWaitTimeInReceptionQueue;
    private double sumWaitTimeInHairstyleQueue;
    private double sumWaitTimeInMakeupQueue;

    private double customerSumTimesInSystem;
    private double sumOfWaitTimeForOrder;
    private int numberOfStartedOrders;
    private double averageLengthOfReceptionQueue;

    //globalne statistiky
    private double globalSumAvgWaitTimeForOrder;
    private double globalSumAvgTimeInSystem;
    private double globalSumAvgLengthOfReceptionQueue;

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

        receptionWaitingQueue = new PriorityQueue<>();
        hairstyleWaitingQueue = new LinkedList<>();
        makeupWaitingQueue = new LinkedList<>();

        waitTimesInHairstyleQueue = new ArrayList<>();
        waitTimesInMakeupQueue = new ArrayList<>();
        waitTimesInPaymentQueue = new ArrayList<>();
        waitTimesInReceptionQueue = new ArrayList<>();

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
        hairstyleMakeupServicesGenerator = new Random(seed.nextInt());

        cleaningChoiceGenerator = new Random(seed.nextInt());
        cleaningTriangularGenerator = new Triangular(seed.nextInt(),360,900,540);

        makeupTypeGenerator = new Random(seed.nextInt());
        simpleMakeupGenerator = new Random(seed.nextInt());
        complicatedMakeupGenerator = new Random(seed.nextInt());

        hairstyleTypeGenerator = new Random(seed.nextInt());
        simpleHairstyleGenerator = new Random(seed.nextInt());
        ArrayList<EmpiricalObject> listOfComplicatedHairstylesDistributions = new ArrayList<>();
        listOfComplicatedHairstylesDistributions.add(new EmpiricalObject(30,60,0.4));
        listOfComplicatedHairstylesDistributions.add(new EmpiricalObject(61,120,0.6));
        complicatedHairstyleGenerator = new EmpGen(listOfComplicatedHairstylesDistributions, seed.nextInt());
        ArrayList<EmpiricalObject> listOfWeddingHairstylesDistributions= new ArrayList<>();
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(50,60,0.2));
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(61,100,0.3));
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(101,150,0.5));
        weddingHairstyleGenerator = new EmpGen(listOfWeddingHairstylesDistributions,seed.nextInt());
        paymentGenerator = new Random(seed.nextInt());

        numberOfArrivedCustomers = 0;
        numberOfServedCustomers = 0;
        numberOfLeavingCustomers = 0;
    }

    @Override
    public void doBeforeReplications() {
        if (typeOfSimulation == TypeOfSimulation.MAX_WITH_CHART){
            numberOfHairstylists = currentRun+1;
        }
        currentReplication = 0;
        finished = false;
        globalSumAvgLengthOfReceptionQueue = 0;
        globalSumAvgWaitTimeForOrder = 0;
        globalSumAvgTimeInSystem = 0;
        /*receptionWaitingQueue.clear();
        hairstyleWaitingQueue.clear();
        makeupWaitingQueue.clear();
        waitTimesInHairstyleQueue.clear();
        waitTimesInMakeupQueue.clear();
        waitTimesInPaymentQueue.clear();
        waitTimesInReceptionQueue.clear();
        numberOfCustomersInReceptionQueueChangedTime = 0;
        numberOfCustomersInHairstyleQueueChangedTime = 0;
        numberOfCustomersInMakeupQueueChangedTime = 0;
        sumWaitTimeInHairstyleQueue = 0;
        sumWaitTimeInMakeupQueue = 0;
        sumWaitTimeInReceptionQueue = 0;

        globalSumAvgLengthOfReceptionQueue = 0;
        globalSumAvgWaitTimeForOrder = 0;
        globalSumAvgTimeInSystem = 0;

        customerSumTimesInSystem = 0;
        sumOfWaitTimeForOrder = 0;
        numberOfStartedOrders = 0;

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
        numberOfLeavingCustomers = 0;
        calendar.clear();
        lastProcessedEvent = null;
        currentTime = 0;

        double time = currentTime + arrivalGenerator.nextValue();
        Customer arrivedCustomer = new Customer(time);
        listOfCustomersInSystem.add(arrivedCustomer);
        calendar.add(new EndOfTheShift(28800,this,null));
        calendar.add(new CustomerArrived(time, this, arrivedCustomer));
        //calendar.add(new TestingEvent(0,this, null));
        if (typeOfSimulation == TypeOfSimulation.OBSERVE){
            calendar.add(new SystemEvent(currentTime,this, null));
        }*/
        addPersonnel();
    }

    @Override
    public void doAfterReplications() {
        if (currentRun == 9){
            finished = true;
        }
        super.doAfterReplications();
    }

    @Override
    public void doBeforeReplication() {
        receptionWaitingQueue.clear();
        hairstyleWaitingQueue.clear();
        makeupWaitingQueue.clear();
        waitTimesInHairstyleQueue.clear();
        waitTimesInMakeupQueue.clear();
        waitTimesInPaymentQueue.clear();
        waitTimesInReceptionQueue.clear();
        numberOfCustomersInReceptionQueueChangedTime = 0;
        numberOfCustomersInHairstyleQueueChangedTime = 0;
        numberOfCustomersInMakeupQueueChangedTime = 0;
        sumWaitTimeInHairstyleQueue = 0;
        sumWaitTimeInMakeupQueue = 0;
        sumWaitTimeInReceptionQueue = 0;
        averageLengthOfReceptionQueue = 0;

        customerSumTimesInSystem = 0;
        sumOfWaitTimeForOrder = 0;
        numberOfStartedOrders = 0;

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
        numberOfLeavingCustomers = 0;
        calendar.clear();
        lastProcessedEvent = null;
        currentTime = 0;

        double time = currentTime + arrivalGenerator.nextValue();
        Customer arrivedCustomer = new Customer(time);
        listOfCustomersInSystem.add(arrivedCustomer);
        calendar.add(new EndOfTheShift(28800,this,null));
        calendar.add(new CustomerArrived(time, this, arrivedCustomer));
        if (typeOfSimulation == TypeOfSimulation.OBSERVE){
            calendar.add(new SystemEvent(currentTime,this, null));
        }
    }

    @Override
    public void doAfterReplication() {
        if (numberOfReplications == currentReplication+1 && typeOfSimulation != TypeOfSimulation.MAX_WITH_CHART){
            finished = true;
        }
        //vypocet priemernej dlzky radu
        if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
            waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
        }else {
            Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
            waitTimesInReceptionQueue.set(
                    receptionWaitingQueue.size(),
                    currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
        }
        numberOfCustomersInReceptionQueueChangedTime = currentTime;

        double receptionSum = 0;
        for(int i = 0; i < waitTimesInReceptionQueue.size(); i++){
            receptionSum += i * waitTimesInReceptionQueue.get(i);
        }
        averageLengthOfReceptionQueue = receptionSum/currentTime;

        globalSumAvgLengthOfReceptionQueue+= averageLengthOfReceptionQueue;
        double avgWaitTime = 0;
        if (numberOfStartedOrders != 0){
            avgWaitTime = sumOfWaitTimeForOrder/numberOfStartedOrders;
        }
        globalSumAvgWaitTimeForOrder += avgWaitTime;
        globalSumAvgTimeInSystem += customerSumTimesInSystem/numberOfServedCustomers;
        super.doAfterReplication();
        currentReplication++;
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
        //pridava nove vybavovanie objednavky iba ak je niekto na recepcii volny a nie je v radoch pred ucesmi a licenim viac ako 10 ludi
        if (isSomeReceptionistFree && (hairstyleWaitingQueue.size() + makeupWaitingQueue.size()) <= 10){
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
        if (time < 28800){
            Customer arrivedCustomer = new Customer(time);
            listOfCustomersInSystem.add(arrivedCustomer);
            calendar.add(new CustomerArrived(time, this, arrivedCustomer));
        }
        lastProcessedEvent = event;
    }

    public void customerOrderProcessingStarted(WritingOrderBeginning event){
        //planovanie ukoncenia objednavky
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.ORDERING);
        sumOfWaitTimeForOrder += currentTime - customer.getArriveTime();
        numberOfStartedOrders++;
        double lengthOfOrdering = 200 + lengthOfOrderingGenerator.nextDouble(-120,120);
        calendar.add(new WritingOrderEnd(
                currentTime + lengthOfOrdering,
                this,
                customer,
                event.getTime(),
                event.getChosenReceptionist()));
        //Vyber objednavky
        double generatedValue = hairstyleMakeupServicesGenerator.nextDouble();
        if (generatedValue < 0.2){
            //iba uces
            customer.setHairstyle(true);
            customer.setCleaning(false);
            customer.setMakeup(false);
        }else{
            //sem idu vsetci co chcu makeup
            if (generatedValue < 0.35){
                customer.setHairstyle(false);
                customer.setMakeup(true);
            }else {
                customer.setHairstyle(true);
                customer.setMakeup(true);
            }
            double cleaningGeneratedValue = cleaningChoiceGenerator.nextDouble();
            //ak chcu aj cistenie pleti
            if (cleaningGeneratedValue < 0.35){
                customer.setCleaning(true);
            }else {
                customer.setCleaning(false);
            }
        }
        lastProcessedEvent = event;
    }

    public void customerOrderProcessingEnded(WritingOrderEnd event){
        Customer customer = event.getCustomer();
        Receptionist receptionist = event.getChosenReceptionist();

        //update pre recepcnu
        receptionist.setWorking(false);
        isSomeReceptionistFree = true;
        Double workedTime = receptionist.getWorkedTimeTogether();
        receptionist.setWorkedTimeTogether(workedTime + currentTime - event.getWritingOrderStartTime());

        //vyber naplanovania spravnej sluzby popripade priradenie do spravneho radu
        if(customer.isHairstyle()){
            //chce uces
            //je nejaka kadernicka volna tak planuje udalost pre zaciatok ucesu
            if (isSomeHairstylistFree){
                PriorityQueue<Hairstylist> availableHairstylists = new PriorityQueue<>();
                for (int i = 0; i < listOfHairStylists.size(); i++){
                    Hairstylist h = listOfHairStylists.get(i);
                    if (!h.isWorking()) {
                        availableHairstylists.add(h);
                    }
                }
                if (availableHairstylists.size() == 1){
                    isSomeHairstylistFree = false;
                }
                Hairstylist chosenHairstylist = availableHairstylists.poll();
                chosenHairstylist.setWorking(true);
                calendar.add(
                        new HairstyleBeginning(currentTime, this, customer, chosenHairstylist)
                );
            }else {
                //musi sa postavit do radu
                //updatnute cakacie hodnoty pretoze dlzka radu sa bude menit
                if (waitTimesInHairstyleQueue.size() < hairstyleWaitingQueue.size()+1){
                    waitTimesInHairstyleQueue.add(currentTime - numberOfCustomersInHairstyleQueueChangedTime);
                }else {
                    Double currentValue = waitTimesInHairstyleQueue.get(hairstyleWaitingQueue.size());
                    waitTimesInHairstyleQueue.set(
                            hairstyleWaitingQueue.size(),
                            currentValue+(currentTime-numberOfCustomersInHairstyleQueueChangedTime));
                }
                numberOfCustomersInHairstyleQueueChangedTime = currentTime;
                //postavenie do radu
                customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_HAIRSTYLE);
                hairstyleWaitingQueue.add(customer);
            }
        }else{
            //nechcel uces to znamena ze urcite chce aspon licenie
            if (!isSomeMakeupArtistsFree){
                //ak nie je volna kozmeticka tak sa musi postavit do radu a v tomto momente nezalezi ci chce cistenie alebo nie
                //update statistik kvoli zmene poctu ludi v rade
                if (waitTimesInMakeupQueue.size() < makeupWaitingQueue.size()+1){
                    waitTimesInMakeupQueue.add(currentTime - numberOfCustomersInMakeupQueueChangedTime);
                }else {
                    Double currentValue = waitTimesInMakeupQueue.get(makeupWaitingQueue.size());
                    waitTimesInMakeupQueue.set(
                            makeupWaitingQueue.size(),
                            currentValue+(currentTime-numberOfCustomersInMakeupQueueChangedTime));
                }
                numberOfCustomersInMakeupQueueChangedTime = currentTime;
                //pridanie do radu
                customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_MAKEUP);
                makeupWaitingQueue.add(customer);
            }else {
                //nejaka kozmeticka je volna tak sa moze planovat zacatie udalosti
                PriorityQueue<MakeUpArtist> availableMakeupArtists = new PriorityQueue<>();
                for (int i = 0; i < listOfMakeupArtists.size(); i++){
                    MakeUpArtist m = listOfMakeupArtists.get(i);
                    if (!m.isWorking()){
                        availableMakeupArtists.add(m);
                    }
                }
                if (availableMakeupArtists.size() == 1){
                    isSomeMakeupArtistsFree = false;
                }
                MakeUpArtist chosenMakeupArtist = availableMakeupArtists.poll();
                chosenMakeupArtist.setWorking(true);
                if (customer.isCleaning()){
                    //chce aj cistenie a to sa vykonva pred licenim
                    calendar.add(
                            new SkinCleaningBeginning(currentTime,this,customer,chosenMakeupArtist)
                    );
                }else {
                    //chce iba licenie bez cistenia
                    calendar.add(
                            new MakeupBeginning(currentTime, this, customer, chosenMakeupArtist)
                    );
                }
            }
        }

        //TODO prerobit tak aby queue bol priorityQueue a tuto teda aby overoval ci plati alebo nie(podla toho by
        // vytvoril event pre vybavovanie objednavky alebo pre platbu)
        //planovanie dalsieho vybavovania objednavky
        //pokial je niekto v rade pred recepciou nech si ho priradi niekto z recepcie
        if (!receptionWaitingQueue.isEmpty()){
            if (receptionWaitingQueue.peek().isPaying()){
                //ak je platiaci zakaznik tak sa vytvori zaciatok platby
                Customer c = receptionWaitingQueue.poll();
                //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
                if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
                    waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
                }else {
                    Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
                    waitTimesInReceptionQueue.set(
                            receptionWaitingQueue.size(),
                            currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
                }
                numberOfCustomersInReceptionQueueChangedTime = currentTime;

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
                        new PaymentBeginning(currentTime,this, c, chosenReceptionist));
                //TODO toto aj inam?
                sumWaitTimeInReceptionQueue += currentTime - c.getArriveTime();
            }else {
                //ak nie je platiaci(chce spisat objednavku) tak overi ci je kapacita radov mensia ako 11
                if ((hairstyleWaitingQueue.size() + makeupWaitingQueue.size()) <= 10){
                    //moze sa vytvorit udalost pre zaciatok zapisu objednavky
                    //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
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
                    //TODO toto aj inam?
                    sumWaitTimeInReceptionQueue += currentTime - c.getArriveTime();
                }
            }
        }
        lastProcessedEvent = event;
    }

    public void hairstyleBeginningProcess(HairstyleBeginning event){
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.HAIR_STYLING);
        double durationOfHairstyle;
        /*
        calendar.add(new WritingOrderEnd(
                currentTime + lengthOfOrdering,
                this,
                customer,
                event.getTime(),
                event.getChosenReceptionist()));*/
        double choice = hairstyleTypeGenerator.nextDouble();
        if (choice < 0.4){
            //jednoduchy uces
            durationOfHairstyle = simpleHairstyleGenerator.nextInt(10,30+1)*60;
        }else if(choice < 0.8){
            //zlozity uces
            try {
                durationOfHairstyle = complicatedHairstyleGenerator.nextValue()*60;
            }catch (Exception e){
                e.printStackTrace();
                durationOfHairstyle = 100000000;
            }
        }else {
            //svadobny uces
            try {
                durationOfHairstyle = weddingHairstyleGenerator.nextValue()*60;
            }catch (Exception e){
                e.printStackTrace();
                durationOfHairstyle = 100000000;
            }
        }
        calendar.add(
                new HairstyleEnd(
                        currentTime+durationOfHairstyle,
                        this,
                        customer,
                        event.getTime(),
                        event.getChosenHairstylist()
                )
        );
        lastProcessedEvent = event;
        //TODO
        //pokial sa znizil tymto pocet pod 11 v radoch pre licenie a uces tak zacni udalost pre vybavovanie objednavky
        //toto bude nakoniec v ende udalosti(pre vsetky tri sluzby) kde bude planovat novu sluzbu(teda sa uvolni miesot v rade)
        //naplanovanie koncu
    }
    public void makeupBeginningProcess(MakeupBeginning event){
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.MAKE_UP);
        double choice = makeupTypeGenerator.nextDouble();
        double lengthOfMakeup;
        if (choice < 0.3){
            //jednoduche licenie
            lengthOfMakeup = simpleMakeupGenerator.nextInt(10,25+1)*60;
        }else {
            //zlozite
            lengthOfMakeup = complicatedMakeupGenerator.nextInt(20,100+1)*60;
        }
        calendar.add(
                new MakeupEnd(
                        currentTime+lengthOfMakeup,
                        this,
                        customer,
                        event.getTime(),
                        event.getChosenMakeupArtist())
        );
        lastProcessedEvent = event;
    }
    public void skinCleaningBeginningProcess(SkinCleaningBeginning event){
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.CLEANING_SKIN);
        double lengthOfCleaning = cleaningTriangularGenerator.nextValue();
        calendar.add(
                new SkinCleaningEnd(
                        currentTime + lengthOfCleaning,
                        this,
                        customer,
                        event.getTime(),
                        event.getChosenMakeupArtist())
        );
        lastProcessedEvent = event;
    }

    public void skinCleaningEndProcess(SkinCleaningEnd event){
        Customer customer = event.getCustomer();
        MakeUpArtist makeUpArtist = event.getChosenMakeupArtist();
        //temp
        //customer.setCurrentPosition(CurrentPosition.PAYING);

        //update pre kozmeticku
        makeUpArtist.setWorking(false);
        isSomeMakeupArtistsFree = true;
        Double workedTime = makeUpArtist.getWorkedTimeTogether();
        makeUpArtist.setWorkedTimeTogether(workedTime + currentTime - event.getCleaningStartTime());
        //update pre customera
        customer.setCleaning(false);

        //naplanovanie licenia alebo cistenia pleti pre dalsieho zakaznika z radu(podla toho kto je na rade)
        if (!makeupWaitingQueue.isEmpty()){
            //ak niekto caka v rade na licenie/cistenie pleti tak si ho priradi uvolnena kozmeticka
            //update statistik kvoli zmene poctu ludi v rade
            if (waitTimesInMakeupQueue.size() < makeupWaitingQueue.size()+1){
                waitTimesInMakeupQueue.add(currentTime - numberOfCustomersInMakeupQueueChangedTime);
            }else {
                Double currentValue = waitTimesInMakeupQueue.get(makeupWaitingQueue.size());
                waitTimesInMakeupQueue.set(
                        makeupWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInMakeupQueueChangedTime));
            }
            numberOfCustomersInMakeupQueueChangedTime = currentTime;

            Customer c = makeupWaitingQueue.poll();
            PriorityQueue<MakeUpArtist> availableMakeupArtists = new PriorityQueue<>();
            for (int i = 0; i < listOfMakeupArtists.size(); i++){
                MakeUpArtist m = listOfMakeupArtists.get(i);
                if (!m.isWorking()){
                    availableMakeupArtists.add(m);
                }
            }
            if (availableMakeupArtists.size() == 1){
                isSomeMakeupArtistsFree = false;
            }
            MakeUpArtist chosenMakeupArtist = availableMakeupArtists.poll();
            chosenMakeupArtist.setWorking(true);
            if (c.isCleaning()){
                //chce aj cistenie a to sa vykonva pred licenim
                calendar.add(
                        new SkinCleaningBeginning(currentTime,this,c,chosenMakeupArtist)
                );
            }else {
                //chce iba licenie bez cistenia
                calendar.add(
                        new MakeupBeginning(currentTime, this, c, chosenMakeupArtist)
                );
            }
        }
        //naplanovanie licenia pre tohto zakaznika(ak je dostupna kozmeticka)/postavenie do radu
        if (!isSomeMakeupArtistsFree){
            //musi sa postavit do radu pred licenim
            //update statistik kvoli zmene poctu ludi v rade
            if (waitTimesInMakeupQueue.size() < makeupWaitingQueue.size()+1){
                waitTimesInMakeupQueue.add(currentTime - numberOfCustomersInMakeupQueueChangedTime);
            }else {
                Double currentValue = waitTimesInMakeupQueue.get(makeupWaitingQueue.size());
                waitTimesInMakeupQueue.set(
                        makeupWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInMakeupQueueChangedTime));
            }
            numberOfCustomersInMakeupQueueChangedTime = currentTime;
            //pridanie do radu
            customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_MAKEUP);
            makeupWaitingQueue.add(customer);
        }else {
            //moze sa naplanovat udalost pre licenie pretoze je volna kozmeticka
            PriorityQueue<MakeUpArtist> availableMakeupArtists = new PriorityQueue<>();
            for (int i = 0; i < listOfMakeupArtists.size(); i++){
                MakeUpArtist m = listOfMakeupArtists.get(i);
                if (!m.isWorking()){
                    availableMakeupArtists.add(m);
                }
            }
            if (availableMakeupArtists.size() == 1){
                isSomeMakeupArtistsFree = false;
            }
            MakeUpArtist chosenMakeupArtist = availableMakeupArtists.poll();
            chosenMakeupArtist.setWorking(true);
            calendar.add(
                    new MakeupBeginning(currentTime, this, customer, chosenMakeupArtist)
            );
        }
        lastProcessedEvent = event;
    }

    public void makeupEndProcess(MakeupEnd event){
        Customer customer = event.getCustomer();
        MakeUpArtist makeUpArtist = event.getChosenMakeupArtist();
        //temp
        //customer.setCurrentPosition(CurrentPosition.PAYING);

        //update pre kozmeticku
        makeUpArtist.setWorking(false);
        isSomeMakeupArtistsFree = true;
        Double workedTime = makeUpArtist.getWorkedTimeTogether();
        makeUpArtist.setWorkedTimeTogether(workedTime + currentTime - event.getMakeupStartTime());

        //update pre customera
        customer.setMakeup(false);
        //naplanovanie licenia alebo cistenia pleti(podla toho kto je na rade)
        if (!makeupWaitingQueue.isEmpty()){
            //ak niekto caka v rade na licenie/cistenie pleti tak si ho priradi uvolnena kozmeticka
            //update statistik kvoli zmene poctu ludi v rade
            if (waitTimesInMakeupQueue.size() < makeupWaitingQueue.size()+1){
                waitTimesInMakeupQueue.add(currentTime - numberOfCustomersInMakeupQueueChangedTime);
            }else {
                Double currentValue = waitTimesInMakeupQueue.get(makeupWaitingQueue.size());
                waitTimesInMakeupQueue.set(
                        makeupWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInMakeupQueueChangedTime));
            }
            numberOfCustomersInMakeupQueueChangedTime = currentTime;

            Customer c = makeupWaitingQueue.poll();
            PriorityQueue<MakeUpArtist> availableMakeupArtists = new PriorityQueue<>();
            for (int i = 0; i < listOfMakeupArtists.size(); i++){
                MakeUpArtist m = listOfMakeupArtists.get(i);
                if (!m.isWorking()){
                    availableMakeupArtists.add(m);
                }
            }
            if (availableMakeupArtists.size() == 1){
                isSomeMakeupArtistsFree = false;
            }
            MakeUpArtist chosenMakeupArtist = availableMakeupArtists.poll();
            chosenMakeupArtist.setWorking(true);
            if (c.isCleaning()){
                //chce aj cistenie a to sa vykonva pred licenim
                calendar.add(
                        new SkinCleaningBeginning(currentTime,this,c,chosenMakeupArtist)
                );
            }else {
                //chce iba licenie bez cistenia
                calendar.add(
                        new MakeupBeginning(currentTime, this, c, chosenMakeupArtist)
                );
            }
        }
        //platba zakaznika kedze toto je posledna mozna sluzba
        customer.setPaying(true);
        if (isSomeReceptionistFree){
            //vytvaranie eventu pre zaciatok platby
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
                    new PaymentBeginning(currentTime, this, customer, chosenReceptionist)
            );
        }else {
            //postavenie do prioritneho frontu
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
            customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_PAY);
            receptionWaitingQueue.add(customer);
        }

        //pokial sa znizil tymto pocet pod 11 v radoch pre licenie a uces tak zacni udalost pre vybavovanie objednavky
        if (((makeupWaitingQueue.size() + hairstyleWaitingQueue.size()) == 10)
                && !receptionWaitingQueue.isEmpty()
                && isSomeReceptionistFree){
            if (!receptionWaitingQueue.peek().isPaying()){
                //ak chce vytvarat objednavku tak uz moze lebo klesol pocet pod 11
                //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
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
        lastProcessedEvent = event;
    }

    public void hairstyleEndProcess(HairstyleEnd event){
        Customer customer = event.getCustomer();
        Hairstylist hairstylist = event.getChosenHairstylist();
        //temp
        //customer.setCurrentPosition(CurrentPosition.PAYING);

        int queuesSizeBeforeCreatingEvents = makeupWaitingQueue.size() + hairstyleWaitingQueue.size();
        //update pre kadernicku
        hairstylist.setWorking(false);
        isSomeHairstylistFree = true;
        Double workedTime = hairstylist.getWorkedTimeTogether();
        hairstylist.setWorkedTimeTogether(workedTime + currentTime - event.getHairstylingStartTime());
        //update pre customera
        customer.setHairstyle(false);
        //ak ma zakaznik zaujem aj o licenie(popripade potom aj cistenie pleti) tak pokusenie o vytvorenie jedneho z tychto eventov
        if (customer.isCleaning() || customer.isMakeup()){
            //ma zaujem o cistenie pleti alebo licenie
            if (!isSomeMakeupArtistsFree){
                //ak nie je volna kozmeticka tak sa musi postavit do radu
                //update statistik kvoli zmene poctu ludi v rade
                if (waitTimesInMakeupQueue.size() < makeupWaitingQueue.size()+1){
                    waitTimesInMakeupQueue.add(currentTime - numberOfCustomersInMakeupQueueChangedTime);
                }else {
                    Double currentValue = waitTimesInMakeupQueue.get(makeupWaitingQueue.size());
                    waitTimesInMakeupQueue.set(
                            makeupWaitingQueue.size(),
                            currentValue+(currentTime-numberOfCustomersInMakeupQueueChangedTime));
                }
                numberOfCustomersInMakeupQueueChangedTime = currentTime;
                //pridanie do radu
                customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_MAKEUP);
                makeupWaitingQueue.add(customer);
            }else {
                //nejaka kozmeticka je volna tak sa moze planovat zacatie udalosti
                PriorityQueue<MakeUpArtist> availableMakeupArtists = new PriorityQueue<>();
                for (int i = 0; i < listOfMakeupArtists.size(); i++){
                    MakeUpArtist m = listOfMakeupArtists.get(i);
                    if (!m.isWorking()){
                        availableMakeupArtists.add(m);
                    }
                }
                if (availableMakeupArtists.size() == 1){
                    isSomeMakeupArtistsFree = false;
                }
                MakeUpArtist chosenMakeupArtist = availableMakeupArtists.poll();
                chosenMakeupArtist.setWorking(true);
                if (customer.isCleaning()){
                    //chce aj cistenie a to sa vykonva pred licenim
                    calendar.add(
                            new SkinCleaningBeginning(currentTime,this,customer,chosenMakeupArtist)
                    );
                }else {
                    //chce iba licenie bez cistenia
                    calendar.add(
                            new MakeupBeginning(currentTime, this, customer, chosenMakeupArtist)
                    );
                }
            }
        }else {
            //nema zaujem o dalsie sluzby tak pojde platit
            customer.setPaying(true);
            if (isSomeReceptionistFree){
                //vytvaranie eventu pre zaciatok platby
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
                        new PaymentBeginning(currentTime, this, customer, chosenReceptionist)
                );
            }else {
                //postavenie do priroitneho frontu pred recepciou(mal by byt upradnostneny)
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
                customer.setCurrentPosition(CurrentPosition.IN_QUEUE_FOR_PAY);
                receptionWaitingQueue.add(customer);
            }
        }

        //ak niekto caka v rade pre uces tak si ho zoberie uvolnena kozmeticka
        if (!hairstyleWaitingQueue.isEmpty()){
            //updatnute cakacie hodnoty pretoze dlzka radu sa bude menit
            if (waitTimesInHairstyleQueue.size() < hairstyleWaitingQueue.size()+1){
                waitTimesInHairstyleQueue.add(currentTime - numberOfCustomersInHairstyleQueueChangedTime);
            }else {
                Double currentValue = waitTimesInHairstyleQueue.get(hairstyleWaitingQueue.size());
                waitTimesInHairstyleQueue.set(
                        hairstyleWaitingQueue.size(),
                        currentValue+(currentTime-numberOfCustomersInHairstyleQueueChangedTime));
            }
            numberOfCustomersInHairstyleQueueChangedTime = currentTime;

            Customer c = hairstyleWaitingQueue.poll();
            PriorityQueue<Hairstylist> availableHairstylists = new PriorityQueue<>();
            for (int i = 0; i < listOfHairStylists.size(); i++){
                Hairstylist h = listOfHairStylists.get(i);
                if (!h.isWorking()) {
                    availableHairstylists.add(h);
                }
            }
            if (availableHairstylists.size() == 1){
                isSomeHairstylistFree = false;
            }
            Hairstylist chosenHairstylist = availableHairstylists.poll();
            chosenHairstylist.setWorking(true);
            calendar.add(
                    new HairstyleBeginning(currentTime, this, c, chosenHairstylist)
            );
        }

        //TODO tu neviem presne ako by mal postupovat keby mal v rade nejakeho co chce platit
        //pokial sa znizil tymto pocet pod 11 v radoch pre licenie a uces tak zacni udalost pre vybavovanie objednavky
        if (((makeupWaitingQueue.size() + hairstyleWaitingQueue.size()) == 10
                && queuesSizeBeforeCreatingEvents == 11)
                && !receptionWaitingQueue.isEmpty()
                && isSomeReceptionistFree){
            if (!receptionWaitingQueue.peek().isPaying()){
                //ak chce vytvarat objednavku tak uz moze lebo klesol pocet pod 11
                //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
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
        lastProcessedEvent = event;
    }

    public void paymentBeginningProcess(PaymentBeginning event){
        Customer customer = event.getCustomer();
        customer.setCurrentPosition(CurrentPosition.PAYING);
        double lengthOfPayment = 180 + paymentGenerator.nextDouble(-50,50);
        calendar.add(
                new PaymentEnd(currentTime + lengthOfPayment,
                        this,
                        customer,
                        event.getTime(),
                        event.getChosenReceptionist())
        );
        lastProcessedEvent = event;
    }

    public void paymentEndProcess(PaymentEnd event){
        Customer customer = event.getCustomer();
        Receptionist receptionist = event.getChosenReceptionist();
        customerSumTimesInSystem += currentTime - customer.getArriveTime();
        numberOfServedCustomers++;
        listOfCustomersInSystem.remove(customer);
        //update pre recepcnu
        receptionist.setWorking(false);
        isSomeReceptionistFree = true;
        Double workedTime = receptionist.getWorkedTimeTogether();
        receptionist.setWorkedTimeTogether(workedTime + currentTime - event.getWritingOrderStartTime());

        //naplanovanie novej platby/zapisu objednavky ALE ak to je vytvaranie objednavky tak overit ci je velkost
        //radov dokpy pod 11
        if (!receptionWaitingQueue.isEmpty()){
            if (receptionWaitingQueue.peek().isPaying()){
                //ak je platiaci zakaznik tak sa vytvori zaciatok platby
                Customer c = receptionWaitingQueue.poll();
                //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
                if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
                    waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
                }else {
                    Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
                    waitTimesInReceptionQueue.set(
                            receptionWaitingQueue.size(),
                            currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
                }
                numberOfCustomersInReceptionQueueChangedTime = currentTime;

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
                        new PaymentBeginning(currentTime,this, c, chosenReceptionist));
                //TODO toto aj inam?
                sumWaitTimeInReceptionQueue += currentTime - c.getArriveTime();
            }else {
                //ak nie je platiaci(chce spisat objednavku) tak overi ci je kapacita radov mensia ako 11
                if ((hairstyleWaitingQueue.size() + makeupWaitingQueue.size()) <= 10){
                    //moze sa vytvorit udalost pre zaciatok zapisu objednavky
                    //priradenie hodnot pre zistovanie statistik o dlzke radu pred recepciou
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
                    //TODO toto aj inam?
                    sumWaitTimeInReceptionQueue += currentTime - c.getArriveTime();
                }
            }
        }
        lastProcessedEvent = event;
    }
    
    public void closingProcess(){
        Iterator itr = listOfCustomersInSystem.iterator();
        int numberOfRemoved = 0;
        while (itr.hasNext()) {
            Customer c = (Customer)itr.next();
            if (c.getCurrentPosition() == CurrentPosition.IN_QUEUE_FOR_ORDERING){
                receptionWaitingQueue.remove(c);
                itr.remove();
                numberOfRemoved++;
            }
        }
        numberOfLeavingCustomers = numberOfRemoved;
    }

    public void testing(TestingEvent event){
        calendar.add(new TestingEvent(currentTime + 1,this, null));
        lastProcessedEvent = event;
    }

    public String getStatesOfSimulation(){
        String result = "";
        result += "Pocet ludi v radoch: -\n  Rad pred recepciou: " + receptionWaitingQueue.size() + " \n  " +
                "Rad pred upravou ucesu: " + hairstyleWaitingQueue.size() + " \n  Rad pred licenim: "
                + makeupWaitingQueue.size()
                + "\nPocet prichodov zakaznikov: " + numberOfArrivedCustomers +
                "\nPocet obsluzenych zakaznikov: " + numberOfServedCustomers +" \nPocet odchodov po zatvaracke: "
                + numberOfLeavingCustomers + "\nStavy personalu: ";
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
                String wantedServices = "";
                if (c.isHairstyle()){
                    wantedServices += "Uces ";
                }
                if (c.isCleaning()){
                    wantedServices += "Cistenie pleti ";
                }
                if (c.isMakeup()){
                    wantedServices += "Licenie";
                }
                result += "\n  Zakaznik: \n    Cas prichodu: " + convertTimeOfSystem(c.getArriveTime()) +
                        "\n    Aktualne miesto v systeme: " +
                        convertCurrentPosition(c.getCurrentPosition()) +
                        "\n    Momentalne ma zaujem este o sluzby: " + wantedServices;
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
            if (!(e instanceof SystemEvent))
                result += convertTimeOfSystem(e.getTime())+ "\t" + e.getNameOfTheEvent() + "\n";
        }
        return result;
    }

    public String getStats(){
        String result = "Priemerny cas zakaznika v systeme: "
                + getTotalTimeFromSeconds(customerSumTimesInSystem/numberOfServedCustomers)
                + "\n  " + numberOfServedCustomers + " obsluzenych zakaznikov";
        double avgWaitTime = 0;
        if (numberOfStartedOrders != 0){
            avgWaitTime = sumOfWaitTimeForOrder/numberOfStartedOrders;
        }
        result += "\nPriemerny cas cakania v rade na zadanie objednavky: "
                + getTotalTimeFromSeconds(avgWaitTime) + "\n  " + numberOfStartedOrders + " zadanych objednavok";

        if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
            waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
        }else {
            Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
            waitTimesInReceptionQueue.set(
                    receptionWaitingQueue.size(),
                    currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
        }
        numberOfCustomersInReceptionQueueChangedTime = currentTime;

        double receptionSum = 0;
        for(int i = 0; i < waitTimesInReceptionQueue.size(); i++){
            receptionSum += i * waitTimesInReceptionQueue.get(i);
        }
        averageLengthOfReceptionQueue = receptionSum/currentTime;
        result += "\nPriemerny pocet v rade pred recepciou: " + Math.round(averageLengthOfReceptionQueue * 100.0) / 100.0;
        return result;
    }

    public String getGlobalStatsAndForCurrentReplication(){
        //celkove statistiky
        String result = "Globalne statistiky: \n  Cislo replikacie: " + currentReplication +
                "\n  Priemerny cas zakaznika v systeme: " +
                getTotalTimeFromSeconds(globalSumAvgTimeInSystem/(currentReplication+1)) +
                "\n  Priemerny pocet v rade pred recepciou: " +
                Math.round((globalSumAvgLengthOfReceptionQueue/(currentReplication+1)) * 100.0) / 100.0 +
                "\n  Priemerny cas cakania v rade na zadanie objednavky: " +
                getTotalTimeFromSeconds(globalSumAvgWaitTimeForOrder/(currentReplication+1));
        //pre poslednu replikaciu
        result += "\nPosledna replikacia: \n";
        result += "  Priemerny cas zakaznika v systeme: "
                + getTotalTimeFromSeconds(customerSumTimesInSystem/numberOfServedCustomers)
                + "\n  " + numberOfServedCustomers + " obsluzenych zakaznikov";
        double avgWaitTime = 0;
        if (numberOfStartedOrders != 0){
            avgWaitTime = sumOfWaitTimeForOrder/numberOfStartedOrders;
        }
        result += "\nPriemerny cas cakania v rade na zadanie objednavky: "
                + getTotalTimeFromSeconds(avgWaitTime) + "\n  " + numberOfStartedOrders + " zadanych objednavok";

        if (waitTimesInReceptionQueue.size() < receptionWaitingQueue.size()+1){
            waitTimesInReceptionQueue.add(currentTime - numberOfCustomersInReceptionQueueChangedTime);
        }else {
            Double currentValue = waitTimesInReceptionQueue.get(receptionWaitingQueue.size());
            waitTimesInReceptionQueue.set(
                    receptionWaitingQueue.size(),
                    currentValue+(currentTime-numberOfCustomersInReceptionQueueChangedTime));
        }
        numberOfCustomersInReceptionQueueChangedTime = currentTime;

        double receptionSum = 0;
        for(int i = 0; i < waitTimesInReceptionQueue.size(); i++){
            receptionSum += i * waitTimesInReceptionQueue.get(i);
        }
        averageLengthOfReceptionQueue = receptionSum/currentTime;
        result += "\nPriemerny pocet v rade pred recepciou: " + Math.round(averageLengthOfReceptionQueue * 100.0) / 100.0;
        return result;
    }

    public double getGlobalAverageLengthOfReceptionQueue(){
        return globalSumAvgLengthOfReceptionQueue/(currentReplication+1);
    }

    public void systemEventOccured(){
        try {
            Thread.sleep(sleepLength);
        }catch (Exception e){
            e.printStackTrace();
        }
        double seconds = (double) deltaT/1000;
        if(!calendar.isEmpty()){
            calendar.add(new SystemEvent(currentTime+seconds,this, null));
        }else {
            finished = true;
        }
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

    public int getNumberOfHairstylists() {
        return numberOfHairstylists;
    }

    public int getNumberOfArrivedCustomers() {
        return numberOfArrivedCustomers;
    }

    public int getNumberOfServedCustomers() {
        return numberOfServedCustomers;
    }

    public boolean isFinished() {
        return finished;
    }
}
