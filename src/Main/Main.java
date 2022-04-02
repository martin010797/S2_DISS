package Main;

import Generators.EmpGen;
import Generators.EmpiricalObject;
import Generators.ExpGen;
import Generators.Tests;
import Gui.BeautySalonGui;
import Simulation.BeautySalonSimulator;
import Simulation.Events.HairstyleEnd;
import Simulation.Events.SystemEvent;
import Simulation.Participants.CurrentPosition;
import Simulation.Participants.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        //Tests tests = new Tests();
        //tests.exponentialTest();
        //tests.empiricalTest();
        //tests.triangularTest();
        //BeautySalonSimulator simulator = new BeautySalonSimulator(1,100);
        BeautySalonGui gui = new BeautySalonGui();
        /*Random seed = new Random();
        Random hairstyleTypeGenerator = new Random(seed.nextInt());
        Random simpleHairstyleGenerator = new Random(seed.nextInt());
        ArrayList<EmpiricalObject> listOfComplicatedHairstylesDistributions = new ArrayList<>();
        listOfComplicatedHairstylesDistributions.add(new EmpiricalObject(30,60,0.4));
        listOfComplicatedHairstylesDistributions.add(new EmpiricalObject(61,120,0.6));
        EmpGen complicatedHairstyleGenerator = new EmpGen(listOfComplicatedHairstylesDistributions, seed.nextInt());
        ArrayList<EmpiricalObject> listOfWeddingHairstylesDistributions= new ArrayList<>();
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(50,60,0.2));
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(61,100,0.3));
        listOfWeddingHairstylesDistributions.add(new EmpiricalObject(101,150,0.5));
        EmpGen weddingHairstyleGenerator = new EmpGen(listOfWeddingHairstylesDistributions,seed.nextInt());
        double totalDuration = 0;
        for (int i = 0; i < 1000000; i++){
            double durationOfHairstyle;
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
            totalDuration += durationOfHairstyle;
            int seconds = (int)durationOfHairstyle % 60;
            int minutes = ((int)durationOfHairstyle / 60) % 60;
            if (minutes == 59 && seconds == 59){
                minutes = minutes;
            }
            int hours = ((int)durationOfHairstyle / 60 / 60) % 24;
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
            System.out.println(time);
        }
        double result = totalDuration / 1000000;
        int seconds = (int)result % 60;
        int minutes = ((int)result / 60) % 60;
        if (minutes == 59 && seconds == 59){
            minutes = minutes;
        }
        int hours = ((int)result / 60 / 60) % 24;
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
        System.out.println(time);*/
    }
}
