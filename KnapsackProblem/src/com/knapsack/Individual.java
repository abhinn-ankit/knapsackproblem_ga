package com.knapsack;

public class Individual {

    public static double evaluateGene(String gene, Knapsack knapsackObj) {

        double totalWeight = 0;
        double totalValue = 0;
        double fitnessValue = 0;
        double diff = 0;
        char ch = '0';

        for(int i = 0; i < knapsackObj.numberOfItems; i ++) {
            
            ch = gene.charAt(i);

            if(ch == '1') {
                totalWeight = totalWeight + knapsackObj.weightOfItems.get(i);
                totalValue = totalValue + knapsackObj.valuesOfItems.get(i);
            }
        }
        
        diff = knapsackObj.knapsackCapacity - totalWeight;
        if(diff >= 0) {

            fitnessValue = totalValue;
        }
        
        return fitnessValue;
    }

}