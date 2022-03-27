package Main;

import Generators.EmpGen;
import Generators.EmpiricalObject;
import Generators.ExpGen;
import Generators.Tests;
import Gui.BeautySalonGui;
import Simulation.BeautySalonSimulator;

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
        /*System.out.println((int) 0.1);
        System.out.println((int) 0.9);

        System.out.println((int) 0.5);
        System.out.println((int) 1.1);
        System.out.println((int) 1.0);
        System.out.println((int) 1.4);
        System.out.println((int) 1.5);
        System.out.println((int) 1.9);
        System.out.println((int) 1.99);
        System.out.println((int) 59.4);
        System.out.println((int) 59.9);*/
        //BeautySalonSimulator simulator = new BeautySalonSimulator(1,100);
        BeautySalonGui gui = new BeautySalonGui();

        //TestingGui gui = new TestingGui(simulator);
        //simulator.registerDelegate(gui);
        //gui.simulate();
    }
}
