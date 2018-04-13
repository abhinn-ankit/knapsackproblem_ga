package com.knapsack;

import java.util.ArrayList;

public class Population {
	
	public ArrayList<String> population = new ArrayList<String>();
	int sizeOfPopulation = 0;
    int numberOfItems = 0;
    Knapsack knapsackObj;
	
	public Population(int sizeOfPopulation, Knapsack knapsack) {
		this.sizeOfPopulation = sizeOfPopulation;
        this.numberOfItems = knapsack.numberOfItems;
        this.knapsackObj = knapsack;
	}
	
	public ArrayList<String> makePopulation() {
		
        for(int i = 0; i < sizeOfPopulation; i++) {
            
            population.add(makeGene());      
        }
        return population;
    }
	
    private String makeGene() {

        StringBuilder gene = new StringBuilder(numberOfItems);

        for(int i = 0; i < numberOfItems; i++) {
        	
            double random = Math.random(); 
            
            char ch = ( random > 0.5) ? '1' : '0';
            
            gene.append(ch);
        }
        
        return gene.toString();
    }

    public ArrayList<Double> evaluatePopulation(ArrayList<Double> fitness) {

        //double totalFitnessPerGeneration = 0;

        for(int i = 0; i < this.sizeOfPopulation; i++) {

            double temp = Individual.evaluateGene(population.get(i), knapsackObj);

            //totalFitnessPerGeneration = totalFitnessPerGeneration + temp;

            fitness.add(temp);
        }
        return fitness;
    }

}
