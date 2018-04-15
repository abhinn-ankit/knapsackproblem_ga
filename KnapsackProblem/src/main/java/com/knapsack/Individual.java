package main.java.com.knapsack;

public class Individual {

    double fitnessScore;
    String gene;

    public Individual(String gene) {
        this.gene = gene;
        this.fitnessScore = 0;
    }

    public void calculateFitness(Knapsack knapsack) {

        double totalWeight = 0;
        double totalValue = 0;
        double diff = 0;
        char ch = '0';

        for (int i = 0; i < knapsack.numberOfItems; i++) {

            ch = this.gene.charAt(i);
            if (ch == '1') {
                totalWeight = totalWeight + knapsack.itemWeights.get(i);
                totalValue = totalValue + knapsack.itemValues.get(i);
            }
        }

        diff = knapsack.knapsackCapacity - totalWeight;
        if (diff >= 0) {
            this.fitnessScore = totalValue;
        }
    }

    public int compareTo(Individual that) {
        if (this.fitnessScore < that.fitnessScore) return -1;
        else if (this.fitnessScore > that.fitnessScore) return 1;
        else return 0;
    }
}