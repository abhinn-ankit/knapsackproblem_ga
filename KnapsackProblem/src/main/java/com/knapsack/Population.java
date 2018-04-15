package main.java.com.knapsack;

import java.util.ArrayList;

public class Population {

	int sizeOfPopulation;
    int numberOfItems;
    double generationTotalFitness;
    Knapsack knapsack;
    ArrayList<Individual> individuals;
	
	public Population(int sizeOfPopulation, Knapsack knapsack) {
		this.sizeOfPopulation = sizeOfPopulation;
        this.numberOfItems = knapsack.numberOfItems;
        this.knapsack = knapsack;
        this.individuals = new ArrayList<>();
	}

    public ArrayList<Individual> sort(){
        ArrayList<Individual> sortedList = new ArrayList<>();
        for (Individual in: individuals) {
            in.calculateFitness(this.knapsack);
            sortedList.add(in);
        }
        sort(sortedList);
        return sortedList;
    }

    private static void sort(ArrayList<Individual> list) {
        int n = list.size();
        for (int i = 1; i < n; i++)
            for (int j = i; j > 0 && list.get(j).compareTo(list.get(j-1)) > 0; j--) {
                exch(list, j, j-1);
            }
    }

    private static void exch(ArrayList<Individual> array, int i, int j) {
        Individual a = array.get(i);
        array.set(i, array.get(j));
        array.set(j, a);
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

    public double totalGenerationFitness() {
        this.generationTotalFitness = 0;
        for(Individual in: individuals) {
            in.calculateFitness(this.knapsack);
            generationTotalFitness = generationTotalFitness + in.fitnessScore;
        }
        return this.generationTotalFitness;
    }
}
