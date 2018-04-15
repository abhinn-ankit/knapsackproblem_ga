package test.java.com.knapsack;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import main.java.com.knapsack.Individual;
import main.java.com.knapsack.Knapsack;
import main.java.com.knapsack.Population;

class MainTest {

	@Test
	void testFitnessFunction() {
		
		int numberOfItems = 5;
        ArrayList<Integer> itemValues = new ArrayList<>();
        ArrayList<Integer> itemWeights = new ArrayList<>();

        itemValues.add(14);
        itemValues.add(7);
        itemValues.add(9);
        itemValues.add(6);
        itemValues.add(12);
        
        itemWeights.add(7);
        itemWeights.add(12);
        itemWeights.add(15);
        itemWeights.add(9);
        itemWeights.add(5);
        
        Knapsack knapsack = new Knapsack(27, numberOfItems, itemValues, itemWeights);
        Individual in = new Individual("10101");
        
        double score = in.calculateFitness(knapsack);
        assertEquals(35, score);
	}
	
	@Test
	void testSortFunction() {
		
		ArrayList<Individual> list = new ArrayList<>();
		
		for(int i = 0; i<5; i++) {
			list.add(new Individual("10101"));
		}
		list.get(0).fitnessScore = 10;
		list.get(1).fitnessScore = 20;
		list.get(2).fitnessScore = 30;
		list.get(3).fitnessScore = 40;
		list.get(4).fitnessScore = 50;
		
		Population.sort(list);
		assertTrue(list.get(0).fitnessScore == 50);
		assertTrue(list.get(1).fitnessScore == 40);
		assertTrue(list.get(4).fitnessScore == 10);
	}

}
