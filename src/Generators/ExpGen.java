package Generators;

import java.util.Random;

public class ExpGen {
    private Random randGen;
    private int e;

    public ExpGen(Random randGen, int e) {
        this.randGen = randGen;
        this.e = e;
    }

    public double nextValue(){
        double randValue = randGen.nextDouble();
        double lambda = (double)1/e;
        //int value = (int)(-(Math.log(1-randValue))/(lambda));
        return ((double)(-1)/(lambda))*(Math.log(randValue));
    }
}
