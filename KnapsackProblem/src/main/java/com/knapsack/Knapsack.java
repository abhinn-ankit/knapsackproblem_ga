package main.java.com.knapsack;

import java.util.ArrayList;

public class Knapsack {

    int numberOfItems;
    int knapsackCapacity;
    ArrayList<Integer> itemValues;
    ArrayList<Integer> itemWeights;

    public Knapsack(int knapsackCapacity, int numberOfItems, ArrayList<Integer> itemValues, ArrayList<Integer> itemWeights) {
        this.itemWeights = itemWeights;
        this.itemValues = itemValues;
        this.numberOfItems = numberOfItems;
        this.knapsackCapacity = knapsackCapacity;
    }
}
