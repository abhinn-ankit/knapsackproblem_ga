# Genetic Algorithm: The Knapsack Problem #
Final project for INFO 6205 Program Structures and Algorithms

## Authors ##
- Team 308
  - Aditya Joshi (001837740)
  - Abhinn Ankit (001837913)

## Prerequisites ##
- JUnit 5
- Log4j 1.2.17

## Program Execution ##
1. First we take 7 input parameters consisting of population size, maximum number of generations, mutation probability, knapsack capacity, number of items, maximum weight and values for items.
2. These parameters are then used to generate random pairs of weights and values of items for knapsack. We create a population set using random values of individuals consisting of genes. Fitness scores are then calculated, and genes are selected for breeding future generations by performing crossover and mutation. The above process is repeated to find the best solution for knapsack problem.
3. The threshold to terminate the program has been set if the fitness scores of last 10 generations is same.
4. You can run the program in Eclipse and output can be seen in the console window of Eclipse.

## Unit Tests ##
Unit tests are to be run in Eclipse
