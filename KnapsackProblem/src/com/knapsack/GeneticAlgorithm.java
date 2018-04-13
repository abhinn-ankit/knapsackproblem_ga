package com.knapsack;

import java.util.ArrayList;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int sizeOfPopulation;
    int maxGenerations;
    double mutationProbability;
    ArrayList<String> population = new ArrayList<String>();
    double generationFitness = 0;
    ArrayList<Double> fitness = new ArrayList<Double>();

    public GeneticAlgorithm(Knapsack knapsack, int populationSize, int maxGenerations, double mutationProbability) {
        this.knapsack = knapsack;
        this.sizeOfPopulation = populationSize;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
        this.initializeKnapsack();
    }

    public void initializeKnapsack() {

        Population populationObj = new Population(this.sizeOfPopulation, this.knapsack);
        
        this.population = populationObj.makePopulation();
        
        this.fitness = populationObj.evaluatePopulation(this.fitness);
        

    }

}
