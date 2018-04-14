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
    ArrayList<String> idealSolnForGeneration = new ArrayList<String>();
    ArrayList<Double> avgFitnessForGeneration = new ArrayList<Double>();
    ArrayList<Double> mostFitSolnForGeneration = new ArrayList<Double>();

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
        
        this.idealSolnForGeneration.add(this.population.get(this.getIdealSolution()));

        this.avgFitnessForGeneration.add(this.getAvgFitness());

        this.mostFitSolnForGeneration.add(Individual.evaluateGene(this.idealSolnForGeneration.get(0), this.knapsack));

        if(this.maxGenerations > 1) {

            createFutureGenerations();
        }
    }

    public int getIdealSolution() {
        
        int index = 0;
        double fitness = 0;
        double idealFitness = 0;
        
        for(int i = 0; i < this.sizeOfPopulation; i++) {
            
            fitness = Individual.evaluateGene(this.population.get(i), this.knapsack);
            if( fitness > idealFitness) {
                idealFitness = fitness;
                index = i;
            }
        }
        return index;
    }

    public double getAvgFitness() {
        
        double total = 0;
   	    
        for(int i = 0; i < this.sizeOfPopulation; i++) {
	        total += fitness.get(i);
        }
	    return  total / this.sizeOfPopulation;
    }

    public void createFutureGenerations() {
        
    }

}
