package com.knapsack;

import java.util.ArrayList;

public class Population {
	
	public ArrayList<String> population = new ArrayList<String>();
	int sizeOfPopulation = 0;
	int numberOfItems = 0;
	
	public Population(int sizeOfPopulation, Knapsack knapsack) {
		this.sizeOfPopulation = sizeOfPopulation;
		this.numberOfItems = knapsack.numberOfItems;
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

    public static int evaluatePopulation() {
        return 0;
    }

}
