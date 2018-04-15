package main.java.com.knapsack;

import java.util.ArrayList;

public class Population {

	int populationSize;
    double generationTotalFitness;
    Knapsack knapsack;
    ArrayList<Individual> individuals;
	
	public Population(Knapsack knapsack) {
		this.populationSize = 10;
        this.knapsack = knapsack;
        this.individuals = new ArrayList<>();
	}

    void sort(){
        calcIndividualFitness(this.individuals);
        sort(this.individuals);
    }

    ArrayList<Individual> calcIndividualFitness(ArrayList<Individual> individualArrayList) {
        for (Individual in: individualArrayList) {
            in.calculateFitness(this.knapsack);
        }
        return individualArrayList;
    }

    public static void sort(ArrayList<Individual> list) {
        int n = list.size();
        for (int i = 1; i < n; i++)
            for (int j = i; j > 0 && list.get(j).compareTo(list.get(j-1)) >= 0; j--) {
                exch(list, j, j-1);
            }
    }

    private static void exch(ArrayList<Individual> array, int i, int j) {
        Individual a = array.get(i);
        array.set(i, array.get(j));
        array.set(j, a);
    }

    void seed() {
        for(int i = 0; i < populationSize; i++) {
            individuals.add(createGene(knapsack.numberOfItems));
        }
    }
	
    public static Individual createGene(int numberOfItems) {
        StringBuilder gene = new StringBuilder(numberOfItems);
        for(int i = 0; i < numberOfItems; i++) {
            double random = Math.random();
            char ch = ( random > 0.5) ? '1' : '0';
            gene.append(ch);
        }
        return new Individual(gene.toString());
    }

    public double totalGenerationFitness() {
        this.generationTotalFitness = 0;
        for(Individual in: individuals) {
            in.calculateFitness(this.knapsack);
            generationTotalFitness = generationTotalFitness + in.fitnessScore;
        }
        return this.generationTotalFitness;
    }
}
