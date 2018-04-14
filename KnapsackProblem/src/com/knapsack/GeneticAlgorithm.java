package com.knapsack;

import java.util.ArrayList;

import java.util.Random;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int sizeOfPopulation;
    int maxGenerations;
    double mutationProbability;
    double crossoverProbability;
    ArrayList<String> population = new ArrayList<String>();
    double generationFitness = 0;
    ArrayList<Double> fitness = new ArrayList<Double>();
    ArrayList<String> idealSolnForGeneration = new ArrayList<String>();
    ArrayList<Double> avgFitnessForGeneration = new ArrayList<Double>();
    ArrayList<Double> mostFitSolnForGeneration = new ArrayList<Double>();
    int crossOverCount = 0;
    int cloneCount = 0;
    boolean mutate = false;
    int generationCount = 1;
    private ArrayList<String> generatePopulation = new ArrayList<String>();

    public GeneticAlgorithm(Knapsack knapsack, int populationSize, int maxGenerations, double crossoverProbability, double mutationProbability) {
        this.knapsack = knapsack;
        this.sizeOfPopulation = populationSize;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
        this.crossoverProbability = crossoverProbability;
        this.initializeKnapsack();
        
    }

    Population populationObj = new Population(this.sizeOfPopulation, this.knapsack);

    public void initializeKnapsack() {
        
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

        for(int i = 1; i < this.maxGenerations; i++) {

            if((this.maxGenerations > 4) && (i > 4)) {
    
                double x = this.avgFitnessForGeneration.get(i - 1);
                double y = this.avgFitnessForGeneration.get(i - 2);
                double z = this.avgFitnessForGeneration.get(i - 3);    
                
                if(x == y && y == z) {
                    this.maxGenerations = i;
                    break;
                }
            }
    
            this.crossOverCount = 0;
            this.cloneCount = 0;
            this.mutate = false;
              
            for(int j = 0; j < this.sizeOfPopulation / 2; j++) {
                this.generatePopulation();
            }
       
            this.fitness.clear();
    
            // Evaluate fitness of breed population members
            this.evaluateGeneratedPopulation();

            // Copy breed_population to population
            for(int k = 0; k < this.sizeOfPopulation; k++) {
                this.population.set(k, this.generatePopulation.get(k));
            } 

            // Clear breed_population
            this.generatePopulation.clear();

            this.idealSolnForGeneration.add(this.population.get(this.getIdealSolution()));
    
            this.avgFitnessForGeneration.add(this.getAvgFitness());
    
            this.mostFitSolnForGeneration.add(Individual.evaluateGene(this.population.get(this.getIdealSolution()), this.knapsack));

            // Output best fitness of generation
            System.out.println("Fitness score of best solution of generation " + (i + 1) + ": " + this.mostFitSolnForGeneration.get(i));

            // Output crossover/cloning summary
            System.out.println("Crossover occurred " + this.crossOverCount + " times");
            System.out.println("Cloning occurred " + this.cloneCount + " times");
            if(this.mutate == false) {
                System.out.println("Mutation did not occur");
            }
            if(this.mutate == true) {
                System.out.println("Mutation did occur");
            }
        }
    }

    public void generatePopulation() {
        
        int geneX;
        int geneY;
        this.generationCount += 1;

        if(this.sizeOfPopulation % 2 == 1) {
            this.generatePopulation.add(this.idealSolnForGeneration.get(this.generationCount - 1));
        }

        geneX = getGene();
        geneY = getGene();
        crossoverGenes(geneX, geneY);
    }

    private int getGene() {

        double random = Math.random() * populationObj.totalFitnessPerGeneration;
        
        for(int i = 0; i < this.sizeOfPopulation; i++) {
            if( random <= fitness.get(i)) {
                return i;
            }
            random = random - fitness.get(i);
        }
	    return 0;
    }

    public void crossoverGenes(int geneX, int geneY) {
    
        String newGeneX;
        String newGeneY;

        double random = Math.random();
        if(random <= this.crossoverProbability) {
            
            this.crossOverCount += 1;
            Random generator = new Random(); 
            int cross_point = generator.nextInt(knapsack.numberOfItems) + 1;

            newGeneX = population.get(geneX).substring(0, cross_point) + population.get(geneY).substring(cross_point);
            newGeneY = population.get(geneY).substring(0, cross_point) + population.get(geneX).substring(cross_point);

            this.generatePopulation.add(newGeneX);
            this.generatePopulation.add(newGeneY);
        }
        else {

            this.cloneCount += 1;
            this.generatePopulation.add(population.get(geneX));
            this.generatePopulation.add(population.get(geneY));
        }

        mutateGene();
    }

    public void mutateGene() {

    }

    public void evaluateGeneratedPopulation() {

    }

}
