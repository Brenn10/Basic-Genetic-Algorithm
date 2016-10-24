# Genetic Algorithms

## Algorithm
* Create a population of alleles
  * Random
  * All False
  * All True
* Evolve
  * Create a new empty population
  * Fill it by using a tournament select to find mates and use a crossover mechanic to splice their traits
* Screw those kids up with random mutations

## Why did I do this?
Initially, I was working on this for a class. The goal was to make a population of all False alleles become fully True.
I got bored and found i could apply this to other things such as the traveling salesperson problem (In another repo).
It is a very effective way to solve non-linear problems. If there is no easy way to a solution, a genetic algorithm 
can be used to find valid solutions based on a fitness function and Darwinistic evolution.

I reimplemented the algorithm more efficiently in python and create a more abstract version. This newer version allows 
the user to define their own fitness function more easily by passing it in during instantiation of the population.

## Example Use

### Python
