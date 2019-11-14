package fr.dieul.lab.geneticalgorithm;

import java.util.Random;

public class SimpleDemoGA {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;
    static boolean verbose;

    public static void main(String[] args) {
    	
    	Random rn = new Random();
        SimpleDemoGA demo = new SimpleDemoGA();
        
        //Set parameters here
        
        //Number of genes each individual has
        int numberOfGenes = 9;
        //Number of individuals
        int numberOfIndividuals = 5;
        //Verbosity (e.g. Should we print genetic pool in the console?)
        demo.verbose = true;
        
        //===================
        
        //Initialize population
        demo.population.initializePopulation(numberOfIndividuals, numberOfGenes);
        
        System.out.println("Population of "+demo.population.popSize+" individual(s).");
        
        //who genetic pool
        showGeneticPool(demo.population.individuals);

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        //who genetic pool
        showGeneticPool(demo.population.individuals);

        //While population gets an individual with maximum fitness
        while (demo.population.fittest < numberOfGenes) {
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

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
            
            //who genetic pool
            showGeneticPool(demo.population.individuals);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < numberOfGenes; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }

        System.out.println("");

    }

    //Selection
    void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
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
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }
    
    //show genetic state of the population pool
    static void showGeneticPool(Individual[] individuals) {
    	if(!verbose) return;
    	System.out.println("==Genetic Pool==");
    	int increment=0;
    	for (Individual individual:individuals) {
    		System.out.println("> Individual  "+increment+" | "+individual.toString()+" |");
    		increment++;
    	}
    	System.out.println("================");
    }

}