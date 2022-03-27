package Generators;

import java.util.ArrayList;
import java.util.Random;

public class EmpGen {
    private Random seedGenerator;
    private ArrayList<EmpiricalObject> distributions;
    private Random[] generators;

    public EmpGen(ArrayList<EmpiricalObject> distributions, int seed) {
        seedGenerator = new Random(seed);
        this.distributions = distributions;
        generators = new Random[distributions.size()+1];
        for (int i = 0; i < generators.length; i++) {
            generators[i] = new Random(seedGenerator.nextInt());
        }
    }
    public boolean checkProbabilities(){
        double sum = 0;
        for (int i = 0; i < distributions.size(); i++) {
            sum += distributions.get(i).getProbability();
        }
        /*double difference = Math.abs((double) 1 - sum);*/
        if (sum != 1){
            return false;
        }else {
            return true;
        }
    }

    public int nextValue() throws Exception {
        double generatedValue = generators[generators.length - 1].nextDouble();
        double sum = 0;
        for (int i = 0; i < distributions.size(); i++) {
            sum += distributions.get(i).getProbability();
            if (generatedValue < sum){
                //return (int)generators[i].nextDouble(distributions.get(i).getMin(),distributions.get(i).getMax()+1);
                return generators[i].nextInt(distributions.get(i).getMin(),distributions.get(i).getMax()+1);
            }
        }
        throw new Exception("Failed to generate number in empirical generator");
    }
}
