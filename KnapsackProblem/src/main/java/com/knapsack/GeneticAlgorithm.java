package main.java.com.knapsack;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    Knapsack knapsack;
    int generationNo;
    Population population;
    ArrayList<Individual> fittestOfGenerations;
    ArrayList<Individual> children;
    final static Logger logger = Logger.getLogger(GeneticAlgorithm.class);

    //Input variables
    int populationSize;
    int maxPopulationSize;
    int maxGenerations;
    double mutationProbability;

    public GeneticAlgorithm(Knapsack knapsack, int maxPopulationSize, int maxGenerations, double mutationProbability) {
        this.knapsack = knapsack;
        this.maxPopulationSize = maxPopulationSize;
        this.maxGenerations = maxGenerations;
        this.mutationProbability = mutationProbability;
        this.generationNo = 0;
        this.fittestOfGenerations = new ArrayList<>();
        this.children = new ArrayList<>();
        this.population = new Population(this.knapsack);
    }

    public void start() {
        this.population.seed();
        this.populationSize = this.population.individuals.size();
        while (this.generationNo < this.maxGenerations) {
            if (this.generationNo > 20) {
                int count = 1;
                for (int i = 1; i < 10; i++) {
                    if (this.fittestOfGenerations.get(generationNo - i - 1).fitnessScore == this.fittestOfGenerations.get(generationNo - 1).fitnessScore) {
                        count++;
                    }
                }
                if (count == 10) {
                    logger.info("\nStop criterion met");
                    break;
                }
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

        for (int i = 0; i < this.populationSize / 2; i++) {
            Individual individual1 = selectIndividual(generationTotalFitness, this.population.individuals);
            Individual individual2 = selectIndividual(generationTotalFitness, this.population.individuals);
            if (individual1 != null && individual2 != null) {
                crossover(individual1, individual2);
            }
        }

        for (int i = 0; i < this.children.size(); i++) {
            mutateGene(i);
        }

        this.population.calcIndividualFitness(this.children);
        //Population.sort(this.children);

        this.population.individuals.addAll(this.children);
        this.populationSize = this.population.individuals.size();

        // Output population
        logger.info("Generation " + (this.generationNo + 1) + ":");
        logger.info("=============");
        logger.info("Population:");
        for (int l = 0; l < this.populationSize; l++) {
            logger.info((l + 1) + " - " + this.population.individuals.get(l));
        }

        // Emptying children list for new generation
        this.children = new ArrayList<>();
        this.population.sort();

        this.fittestOfGenerations.add(this.population.individuals.get(0));
        // Output best fitness of generation
        logger.info("Fitness score of best solution of generation " + (this.generationNo + 1) +
                ": " + this.fittestOfGenerations.get(this.generationNo) + "\n\n");

    }

    public void cull() {

        for (int i = this.population.individuals.size() - 1; this.population.individuals.size() > this.maxPopulationSize / 2; i--) {
            this.population.individuals.remove(i);
        }
        this.populationSize = this.population.individuals.size();

    }

    public static Individual selectIndividual(double generationTotalFitness, ArrayList<Individual> individuals) {

        double rand = Math.random() * generationTotalFitness;
        for (Individual individual : individuals) {
            if (rand <= individual.fitnessScore) {
                return individual;
            }
            rand = rand - individual.fitnessScore;
        }
        return null;
    }

    private void crossover(Individual individual1, Individual individual2) {

        Random random = new Random();
        int swapIndex = random.nextInt(knapsack.numberOfItems) + 1;
        String tempGene1 = individual1.gene.substring(0, swapIndex) + individual2.gene.substring(swapIndex);
        String tempGene2 = individual2.gene.substring(0, swapIndex) + individual1.gene.substring(swapIndex);
        this.children.add(new Individual(tempGene1));
        this.children.add(new Individual(tempGene2));
    }

    private void mutateGene(int childIndex) {

        // Random mutation
        Random random = new Random();
        double rand_mutation = random.nextDouble();
        if (rand_mutation <= this.mutationProbability) {
            Individual mutatedIndividual;
            mutatedIndividual = this.children.get(childIndex);
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
