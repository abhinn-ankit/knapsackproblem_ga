package main.java.com.knapsack;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int generationNo;
    Population population;
    ArrayList<Individual> fittestIndividualOfGeneration;
    ArrayList<Individual> children;

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
        this.children = new ArrayList<>();
        this.population = new Population(this.sizeOfPopulation, this.knapsack);
    }

    public void start() {

        this.population.seed();
        while (this.generationNo <= this.maxGenerations) {
            if (this.generationNo > 10)
                if (this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 2))
                        && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 3))
                        && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 4))
                        && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 5))
                        && this.fittestIndividualOfGeneration.get(generationNo - 1).equals(this.fittestIndividualOfGeneration.get(generationNo - 6))) {
                    System.out.println("\nStop criterion met");
                }
            createGenerations();
            this.generationNo++;
        }

    }

    public void createGenerations() {

        // Calculate the fitness of all individuals and store their sum
        double generationTotalFitness = population.totalGenerationFitness();
        if (this.generationNo > 0)
            cull();

        for (int j = 0; j < this.sizeOfPopulation / 2; j++) {

        }
    }

    public void cull() {

    }

    private void crossoverGenes(Individual individual1, Individual individual2) {

        double crossover = Math.random();

        if (crossover <= crossoverProbability) {
            Random random = new Random();
            int swapIndex = random.nextInt(knapsack.knapsackCapacity) + 1;
            String tempGene1 = individual1.gene.substring(0, swapIndex) + individual2.gene.substring(swapIndex);
            String tempGene2 = individual2.gene.substring(0, swapIndex) + individual1.gene.substring(swapIndex);
            this.children.add(new Individual(tempGene1));
            this.children.add(new Individual(tempGene2));
        } else {
            this.children.add(individual1);
            this.children.add(individual2);
        }

        // Check if mutation is to be performed
//        mutateGene();
    }

    private Individual selectGene(double generationTotalFitness) {

        // Generate random number between 0 and total_fitness_of_generation
        double rand = Math.random() * generationTotalFitness;

        // Use random number to select gene based on fitness level
        for (Individual individual : population.individuals) {
            if (rand <= individual.fitnessScore) {
                return individual;
            }
            rand = rand - individual.fitnessScore;
        }

        // Not reachable; default return value
        return population.individuals.get(0);
    }

}
