package com.knapsack;

public class GeneticAlgorithm {

    private Knapsack knapsack;
    private int sizeOfPopulation;
    private int maxGenerations;
    private double mutationProbability;

    public GeneticAlgorithm(Knapsack knapsack, int populationSize, int maxGenerations, double mutationProbability) {
        this.knapsack = knapsack;
        this.sizeOfPopulation = populationSize;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
        
        Population population = new Population(this.sizeOfPopulation, this.knapsack);
    }

}
