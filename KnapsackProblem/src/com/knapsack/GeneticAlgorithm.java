package com.knapsack;

public class GeneticAlgorithm {

    private Knapsack knapsack;
    private int populationSize;
    private int maxGenerations;
    private double mutationProbability;

    public GeneticAlgorithm(Knapsack knapsack, int populationSize, int maxGenerations, double mutationProbability) {
        this.knapsack = knapsack;
        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
    }

}
