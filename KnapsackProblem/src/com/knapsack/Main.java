package com.knapsack;

public class Main {

    public static void main(String[] args) {

        Knapsack knapsack = new Knapsack();
        knapsack.fillData();

        System.out.println("Enter the population size: ");
        int sizeOfPopulation = (int) knapsack.inputNumber();

        System.out.println("Enter the maximum number of generations: ");
        int maxGenerations = (int) knapsack.inputNumber();

//        System.out.println("Enter the crossover probability: ");
//        double crossoverProbability = (double) knapsack.inputNumber();

        System.out.println("Enter the mutation probability: ");
        double mutationProbability = (double) knapsack.inputNumber();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsack, sizeOfPopulation, maxGenerations, mutationProbability);

    }

}
