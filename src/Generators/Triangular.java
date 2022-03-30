package Generators;

import java.util.Random;

public class Triangular {
    private Random generator;
    private double min;
    private double max;
    private double mode;

    public Triangular(int seed, double min, double max, double mode) {
        this.min = min;
        this.max = max;
        this.mode = mode;
        generator = new Random(seed);
    }

    public double nextValue(){
        double F = (mode-min)/(max-min);
        double generatedValue = generator.nextDouble();
        if (generatedValue < F){
            return (min + Math.sqrt(generatedValue*(max-min)*(mode-min)));
        }else {
            return (max - Math.sqrt((1-generatedValue)*(max-min)*(max-mode)));
        }
    }
}
