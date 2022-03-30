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
        //tests.triangularTest();
        //BeautySalonSimulator simulator = new BeautySalonSimulator(1,100);
        BeautySalonGui gui = new BeautySalonGui();
    }
}
