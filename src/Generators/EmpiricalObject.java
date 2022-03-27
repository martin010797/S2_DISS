package Generators;

public class EmpiricalObject {
    private int min;
    private int max;
    private double probability;

    public EmpiricalObject(int min, int max, double probability) {
        this.min = min;
        this.max = max;
        this.probability = probability;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getProbability() {
        return probability;
    }
}
