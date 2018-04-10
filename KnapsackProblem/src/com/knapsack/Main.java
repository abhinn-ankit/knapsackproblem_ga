package com.knapsack;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        getInput();
    }

    /**
     * Collects user input to be used as parameters for knapsack problem
     */
    private static void getInput() {

        int numberOfItems = 0;
        int knapsackCapacity = 0;

        ArrayList<Double> valuesOfItems = new ArrayList<>();
        ArrayList<Double> weightOfItems = new ArrayList<>();

        //Don't know things
        int populationSize = 0;
        int maxGenerations = 0;
        double crossoverProbability = 0;
        double mutationProbability = 0;

        //Stores the input values
        Scanner c = new Scanner(System.in);

        // Number of items
        System.out.println("Enter the number of items: ");
        numberOfItems = (int) InputNumber();


        // Value and weight of each item
        for (int i = 0; i < numberOfItems; i++) {
            System.out.println("Enter the value for item " + (i + 1) + ": ");
            valuesOfItems.add((double) InputNumber());

            System.out.println("Enter the weight of item " + (i + 1) + ": ");
            weightOfItems.add((double) InputNumber());
        }

        // Capacity of knapsack
        System.out.println("Enter the knapsack capacity: ");
        knapsackCapacity = (int) InputNumber();


        // Population size
        System.out.println("Enter the population size: ");
        populationSize = (int) InputNumber();

        // Maximum number of generations
        System.out.println("Enter the maximum number of generations: ");
        maxGenerations = (int) InputNumber();

        // Crossover probability
        System.out.println("Enter the crossover probability: ");
        crossoverProbability = (double) InputNumber();

        // Mutation probability
        System.out.println("Enter the mutation probability: ");
        mutationProbability = (double) InputNumber();

    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static Number InputNumber() {

        String input;
        Scanner c = new Scanner(System.in);

        char ch;
        do {
            input = c.nextLine();
            if (isInteger(input)) {
                return Integer.parseInt(input);
            } else if (isDouble(input)) {
                return Double.parseDouble(input);
            } else {
                System.out.println("Not a number. Would you like to try again?(Y/n)");
                ch = c.next().charAt(0);
            }
            if (ch == 'n' || ch == 'N')
                System.exit(1);
        } while (true);
    }

}
