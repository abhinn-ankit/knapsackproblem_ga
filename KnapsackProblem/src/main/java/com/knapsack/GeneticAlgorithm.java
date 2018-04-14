package main.java.com.knapsack;

import java.util.ArrayList;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int generationNo;
    Population population;
    ArrayList<Individual> fittestIndividualOfGeneration;

    //Input variables
    int sizeOfPopulation;
    int maxGenerations;
    double mutationProbability;
    double crossoverProbability;

    public GeneticAlgorithm(Knapsack knapsack, int sizeOfPopulation, int maxGenerations, double crossoverProbability, double mutationProbability) {
        this.knapsack = knapsack;
        this.sizeOfPopulation = sizeOfPopulation;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
        this.crossoverProbability = crossoverProbability;
        this.generationNo = 0;
        this.fittestIndividualOfGeneration = new ArrayList<>();
        this.population = new Population(this.sizeOfPopulation, this.knapsack);
    }

    public void start() {

        this.population.seed();
        int i = 0;
        while (this.generationNo <= this.maxGenerations) {
            if (this.generationNo > 10
                    && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 2))
                    && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 3))
                    && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 4))
                    && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 5))
                    && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 6))) {
                System.out.println("\nStop criterion met");
                maxGenerations = i;
            }
            createGenerations();
            this.generationNo++;

        }

    }

    public void createGenerations() {
        if(this.generationNo > 0)
            cull();


    }

    public void cull() {

    }

}
