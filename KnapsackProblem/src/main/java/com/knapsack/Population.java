package main.java.com.knapsack;

import java.util.ArrayList;

public class Population {
	
	ArrayList<Individual> individuals;
	int sizeOfPopulation;
    int numberOfItems;
    Knapsack knapsack;
    double totalFitnessPerGeneration;
	
	public Population(int sizeOfPopulation, Knapsack knapsack) {
		this.sizeOfPopulation = sizeOfPopulation;
        this.numberOfItems = knapsack.numberOfItems;
        this.knapsack = knapsack;
        this.individuals = new ArrayList<>();
	}
	
	public void seed() {
		
        for(int i = 0; i < sizeOfPopulation; i++) {
            individuals.add(createGene());
        }
    }
	
    private Individual createGene() {

        StringBuilder gene = new StringBuilder(numberOfItems);
        for(int i = 0; i < numberOfItems; i++) {

            double random = Math.random();
            char ch = ( random > 0.5) ? '1' : '0';
            gene.append(ch);
        }
        return new Individual(gene.toString());
    }

    public ArrayList<Double> evaluatePopulation(ArrayList<Double> fitness) {

        this.totalFitnessPerGeneration = 0;

        for(Individual individual: this.individuals) {

            double temp = individual.calcFitness(this.knapsack);
            this.totalFitnessPerGeneration += temp;
            fitness.add(temp);
        }
        return fitness;
    }

}
