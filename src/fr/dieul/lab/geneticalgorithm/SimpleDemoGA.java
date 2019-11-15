package fr.dieul.lab.geneticalgorithm;

import java.util.Random;

import fr.dieul.lab.geneticalgorithm.model.Individual;
import fr.dieul.lab.geneticalgorithm.model.Population;

public class SimpleDemoGA {

	private Population population;
	private Individual fittest;
	private Individual secondFittest;
	private int generationCount;
	private static int numberOfGenes;
	private static int numberOfIndividuals;
	private static boolean verbose;
	private static boolean coloredGenes;
    
	public SimpleDemoGA() {
		this.population = new Population(numberOfIndividuals, numberOfGenes);
		this.generationCount = 0;
	}

	public static void main(String[] args) {
    	
		Random rn = new Random();
        
        
		//Set parameters here
        
		//Number of genes each individual has
		numberOfGenes = 20;
		//Number of individuals
		numberOfIndividuals = 5;
		//Verbosity (e.g. Should we print genetic pool in the console?)
		verbose = true;
		//Apply color to genes (if verbose = true) Note: this will slow down the process
		coloredGenes = true;
        
		//===================
        
		//Initialize population
		SimpleDemoGA demo = new SimpleDemoGA();
        
		demo.population = new Population(numberOfIndividuals, numberOfGenes);
        
		System.out.println("Population of "+demo.population.getPopSize()+" individual(s).");

		//Calculate fitness of each individual
		demo.population.calculateFitness();

		System.out.println("\nGeneration: " + demo.generationCount + " Fittest: " + demo.population.getFittestScore());
		//show genetic pool
		showGeneticPool(demo.population.getIndividuals());

		//While population gets an individual with maximum fitness
		while (demo.population.getFittestScore() < numberOfGenes) {
			++demo.generationCount;

			//Do selection
			demo.selection();

			//Do crossover
			demo.crossover();

			//Do mutation under a random probability
			if (rn.nextInt()%7 < 5) {
				demo.mutation();
			}

			//Add fittest offspring to population
			demo.addFittestOffspring();

			//Calculate new fitness value
			demo.population.calculateFitness();

			System.out.println("\nGeneration: " + demo.generationCount + " Fittest score: " + demo.population.getFittestScore());
            
			//show genetic pool
			showGeneticPool(demo.population.getIndividuals());
		}

		System.out.println("\nSolution found in generation " + demo.generationCount);
		System.out.println("Index of winner Individual: "+demo.population.getFittestIndex());
		System.out.println("Fitness: "+demo.population.getFittestScore());
		System.out.print("Genes: ");
		for (int i = 0; i < numberOfGenes; i++) {
			System.out.print(demo.population.selectFittest().getGenes()[i]);
		}

		System.out.println("");

	}

	//Selection
	void selection() {

		//Select the most fittest individual
		fittest = population.selectFittest();

		//Select the second most fittest individual
		secondFittest = population.selectSecondFittest();
	}

	//Crossover
	void crossover() {
		Random rn = new Random();

		//Select a random crossover point
		int crossOverPoint = rn.nextInt(this.numberOfGenes);

		//Swap values among parents
		for (int i = 0; i < crossOverPoint; i++) {
			int temp = fittest.getGenes()[i];
			fittest.getGenes()[i] = secondFittest.getGenes()[i];
			secondFittest.getGenes()[i] = temp;

		}

	}

	//Mutation
	void mutation() {
		Random rn = new Random();

		//Select a random mutation point
		int mutationPoint = rn.nextInt(this.numberOfGenes);

		//Flip values at the mutation point
		if (fittest.getGenes()[mutationPoint] == 0) {
			fittest.getGenes()[mutationPoint] = 1;
		} else {
			fittest.getGenes()[mutationPoint] = 0;
		}

		mutationPoint = rn.nextInt(this.numberOfGenes);

		if (secondFittest.getGenes()[mutationPoint] == 0) {
			secondFittest.getGenes()[mutationPoint] = 1;
		} else {
			secondFittest.getGenes()[mutationPoint] = 0;
		}
	}

	//Get fittest offspring
	Individual getFittestOffspring() {
		if (fittest.getFitness() > secondFittest.getFitness()) {
			return fittest;
		}
		return secondFittest;
	}


	//Replace least fittest individual from most fittest offspring
	void addFittestOffspring() {

		//Update fitness values of offspring
		fittest.calcFitness();
		secondFittest.calcFitness();

		//Get index of least fit individual
		int leastFittestIndex = population.getLeastFittestIndex();

		//Replace least fittest individual from most fittest offspring
		population.getIndividuals()[leastFittestIndex] = getFittestOffspring();
	}
    
	//show genetic state of the population pool
	static void showGeneticPool(Individual[] individuals) {
		if(!verbose) return;
		System.out.println("==Genetic Pool==");
		int increment=0;
		for (Individual individual:individuals) {
			System.out.println("> Individual  "+increment+" | "+(coloredGenes?individual.toStringColor():individual.toString())+" |");
			increment++;
		}
		System.out.println("================");
	}

}