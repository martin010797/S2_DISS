package Generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Tests {
    private Random seedGenerator;
    private Random exponentialBaseGenerator;

    public Tests() {
        seedGenerator = new Random();
        exponentialBaseGenerator = new Random(seedGenerator.nextInt());
    }

    public void exponentialTest(){
        exponentialBaseGenerator = new Random();
        ExpGen expGen = new ExpGen(exponentialBaseGenerator,240);
        try {
            File myObj = new File("exp.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("exp.txt");
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        try {
            myWriter = new FileWriter("exp.txt", true);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        for(int i = 0; i < 1000000; i++){
            try {
                myWriter.write(expGen.nextValue() + "   ");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file.");
                e.printStackTrace();
            }
        }
        try {
            myWriter.close();
            System.out.println("Successfully closed file.");
        } catch (IOException e) {
            System.out.println("An error occurred while closing file.");
            e.printStackTrace();
        }
    }

    public void empiricalTest() throws Exception {
        ArrayList<EmpiricalObject> listOfObjects= new ArrayList<>();
        listOfObjects.add(new EmpiricalObject(50,60,0.2));
        listOfObjects.add(new EmpiricalObject(61,100,0.3));
        listOfObjects.add(new EmpiricalObject(101,150,0.5));
        int[] generatedInRange = new int[4];
        EmpGen empGen = new EmpGen(listOfObjects);
        if (!empGen.checkProbabilities()){
            System.out.println("Sucet pravdepodobnosti nedava hodnotu 1");
        }

        //1
        try {
            File myObj1 = new File("emp1.txt");
            if (myObj1.createNewFile()) {
                System.out.println("File created: " + myObj1.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter myWriter1 = null;
        try {
            myWriter1 = new FileWriter("emp1.txt");
            myWriter1.write("");
            myWriter1.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        try {
            myWriter1 = new FileWriter("emp1.txt", true);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        //2
        try {
            File myObj2 = new File("emp2.txt");
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter myWriter2 = null;
        try {
            myWriter2 = new FileWriter("emp2.txt");
            myWriter2.write("");
            myWriter2.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        try {
            myWriter2 = new FileWriter("emp2.txt", true);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        //3
        try {
            File myObj3 = new File("emp3.txt");
            if (myObj3.createNewFile()) {
                System.out.println("File created: " + myObj3.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileWriter myWriter3 = null;
        try {
            myWriter3 = new FileWriter("emp3.txt");
            myWriter3.write("");
            myWriter3.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        try {
            myWriter3 = new FileWriter("emp3.txt", true);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        for(int i = 0; i < 10000000; i++){
            try {
                int value = empGen.nextValue();
                if (value >= 50 && value <= 60){
                    generatedInRange[0] +=1;
                    myWriter1.write( value + "   ");
                }else if(value >= 61 && value <= 100){
                    generatedInRange[1] +=1;
                    myWriter2.write( value + "   ");
                }else if (value >= 101 && value <= 150){
                    generatedInRange[2] +=1;
                    myWriter3.write( value + "   ");
                }else {
                    generatedInRange[3] +=1;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file.");
                e.printStackTrace();
            }
        }
        try {
            myWriter1.close();
            myWriter2.close();
            myWriter3.close();
            System.out.println("Successfully closed files.");
        } catch (IOException e) {
            System.out.println("An error occurred while closing file.");
            e.printStackTrace();
        }
        System.out.println("Prvy rozsah pocet hodnot(0.2): " + generatedInRange[0]);
        System.out.println("Druhy rozsah pocet hodnot(0.3): " + generatedInRange[1]);
        System.out.println("Treti rozsah pocet hodnot(0.5): " + generatedInRange[2]);
        System.out.println("Mimo rozsah pocet hodnot: " + generatedInRange[3]);

    }
}
