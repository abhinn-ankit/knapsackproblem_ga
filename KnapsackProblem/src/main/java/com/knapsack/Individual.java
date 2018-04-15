package main.java.com.knapsack;

public class Individual {

    public double fitnessScore;
    public double totalWeight;
    public String gene;

    public Individual(String gene) {
        this.gene = gene;
        this.fitnessScore = 0;
    }

    public double calculateFitness(Knapsack knapsack) {

        this.totalWeight = 0;
        double totalValue = 0;
        double diff = 0;
        char ch = '0';

        for (int i = 0; i < knapsack.numberOfItems; i++) {
            ch = this.gene.charAt(i);
            if (ch == '1') {
                this.totalWeight = this.totalWeight + knapsack.itemWeights.get(i);
                totalValue = totalValue + knapsack.itemValues.get(i);
            }
        }

        diff = knapsack.knapsackCapacity - totalWeight;
        if (diff >= 0) {
            this.fitnessScore = totalValue;
        }
        return this.fitnessScore;
    }

    public int compareTo(Individual that) {
        if (this.fitnessScore < that.fitnessScore) return -1;
        else if (this.fitnessScore > that.fitnessScore) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return this.gene+" with total-weight: "+this.totalWeight+" and Fitness score: "+this.fitnessScore;
    }
}