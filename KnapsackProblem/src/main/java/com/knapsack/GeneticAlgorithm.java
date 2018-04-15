package main.java.com.knapsack;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int generationNo;
    Population population;
    ArrayList<Individual> fittestOfGenerations;
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
        this.fittestOfGenerations = new ArrayList<>();
        this.children = new ArrayList<>();
        this.population = new Population(this.sizeOfPopulation, this.knapsack);
    }

    public void start() {

        this.population.seed();
        while (this.generationNo <= this.maxGenerations) {
            if (this.generationNo > 10)
                if (this.fittestOfGenerations.get(generationNo - 1).equals(this.fittestOfGenerations.get(generationNo - 2))
                        && this.fittestOfGenerations.get(generationNo - 1).equals(this.fittestOfGenerations.get(generationNo - 3))
                        && this.fittestOfGenerations.get(generationNo - 1).equals(this.fittestOfGenerations.get(generationNo - 4))
                        && this.fittestOfGenerations.get(generationNo - 1).equals(this.fittestOfGenerations.get(generationNo - 5))
                        && this.fittestOfGenerations.get(generationNo - 1).equals(this.fittestOfGenerations.get(generationNo - 6))) {
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

        for (int i = 0; i < this.sizeOfPopulation / 2; i++) {
            Individual individual1 = selectIndividual(generationTotalFitness);
            Individual individual2 = selectIndividual(generationTotalFitness);
            crossover(individual1, individual2);
        }

        if (this.sizeOfPopulation % 2 == 1) {
            Individual individual1 = selectIndividual(generationTotalFitness);
            Individual individual2 = selectIndividual(generationTotalFitness);
            crossover(individual1, individual2);
        }

        this.population.calcIndividualFitness(this.children);
        Population.sort(this.children);

        for (int i = 0; i < this.sizeOfPopulation; i++) {
            this.population.individuals.set(i, this.children.get(i));
        }

        // Output population
        System.out.println("\nGeneration " + (this.generationNo + 1) + ":");
        if ((this.generationNo + 1) < 10) {
            System.out.println("=============");
        }
        if ((this.generationNo + 1) >= 10) {
            System.out.println("==============");
        }
        if ((this.generationNo + 1) >= 100) {
            System.out.println("===============");
        }
        System.out.println("Population:");
        for (int l = 0; l < this.sizeOfPopulation; l++) {
            System.out.println((l + 1) + " - " + this.population.individuals.get(l));
        }

        this.children.clear();

        this.population.sort();

        this.fittestOfGenerations.add(this.population.individuals.get(0));
        // Output best fitness of generation
        System.out.println("Fitness score of best solution of generation " + (this.generationNo + 1) +
                ": " + this.fittestOfGenerations.get(this.generationNo));

    }

    public void cull() {
        double rand = Math.random();
        int keep = 0;

        if (rand * sizeOfPopulation > 2) {
            keep = (int) Math.floor(rand * sizeOfPopulation);
        } else {
            keep = 2;
        }
        // sort the population
        population.sort();

        for (int i = sizeOfPopulation-keep+1; i < sizeOfPopulation; i++) {
            population.individuals.remove(i);
        }
    }

    private Individual selectIndividual(double generationTotalFitness) {

        // Generate random number between 0 and total_fitness_of_generation
        double rand = Math.random() * generationTotalFitness;

        // Use random number to select gene based on fitness level
        for (Individual individual : population.individuals) {
            if (rand <= individual.fitnessScore) {
                return individual;
            }
            rand = rand - individual.fitnessScore;
        }

        return population.individuals.get(0);
    }

    private void crossover(Individual individual1, Individual individual2) {

        double crossover = Math.random();

        if (crossover <= crossoverProbability) {
            Random random = new Random();
            int swapIndex = random.nextInt(knapsack.numberOfItems) + 1;
            String tempGene1 = individual1.gene.substring(0, swapIndex) + individual2.gene.substring(swapIndex);
            String tempGene2 = individual2.gene.substring(0, swapIndex) + individual1.gene.substring(swapIndex);
            this.children.add(new Individual(tempGene1));
            this.children.add(new Individual(tempGene2));
        } else {
            this.children.add(individual1);
            this.children.add(individual2);
        }

        // Perform mutation if required
        mutateGene();
    }

    private void mutateGene() {

        // Random mutation
        double rand_mutation = Math.random();
        if (rand_mutation <= mutationProbability) {

            Individual mutatedIndividual;
            Random random = new Random();

            // Mutation for which child
            boolean whichChild = random.nextBoolean();

            // Mutate gene
            if (whichChild) {
                mutatedIndividual = this.children.get(this.children.size() - 1);
                int index = random.nextInt(knapsack.numberOfItems);
                StringBuilder mutatedGene = new StringBuilder(mutatedIndividual.gene);
                if (mutatedIndividual.gene.charAt(index) == '1') {
                    mutatedGene.setCharAt(index, '0');
                    this.children.set(this.children.size() - 1, new Individual(mutatedGene.toString()));
                }
                if (mutatedIndividual.gene.charAt(index) == '0') {
                    mutatedGene.setCharAt(index, '1');
                    this.children.set(this.children.size() - 1, new Individual(mutatedGene.toString()));
                }
            }
            if (!whichChild) {
                mutatedIndividual = this.children.get(this.children.size() - 2);
                int index = random.nextInt(knapsack.numberOfItems);
                StringBuilder mutatedGene = new StringBuilder(mutatedIndividual.gene);
                if (mutatedIndividual.gene.charAt(index) == '1') {
                    mutatedGene.setCharAt(index, '0');
                    this.children.set(this.children.size() - 1, new Individual(mutatedGene.toString()));
                }
                if (mutatedIndividual.gene.charAt(index) == '0') {
                    mutatedGene.setCharAt(index, '1');
                    this.children.set(this.children.size() - 1, new Individual(mutatedGene.toString()));
                }
            }
        }
    }

}
