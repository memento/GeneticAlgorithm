package fr.dieul.lab.geneticalgorithm.model;

//Population class
public class Population {

	private int popSize;
	private Individual[] individuals;
	private int geneLength;
	private int fittestScore = 0;

  
  
	/**
	 * @purpose Initialize population
	 * @param popSize is the population size
	 * @param geneLength is the number of genes an individual will have
	 */
	public Population(int popSize, int geneLength) {
		super();
		this.popSize = popSize;
		this.geneLength = geneLength;
		this.individuals = new Individual[popSize];
		
		//Create a first population pool
		for (int i = 0; i < popSize; i++) {
			individuals[i] = new Individual(geneLength);
		}
	}

	//Get the fittest individual and update fittest score
	public Individual selectFittest() {
		int maxFit = Integer.MIN_VALUE;
		int maxFitIndex = 0;
		for (int i = 0; i < individuals.length; i++) {
			if (maxFit <= individuals[i].getFitness()) {
				maxFit = individuals[i].getFitness();
				maxFitIndex = i;
			}
		}
		//update fittest score
		fittestScore = individuals[maxFitIndex].getFitness();
      
		//try to return the fittest individual
		try {
			return (Individual) individuals[maxFitIndex].clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Get the second most fittest individual
	public Individual selectSecondFittest() {
		int maxFit1 = 0;
		int maxFit2 = 0;
		for (int i = 0; i < individuals.length; i++) {
			if (individuals[i].getFitness() > individuals[maxFit1].getFitness()) {
				maxFit2 = maxFit1;
				maxFit1 = i;
			} else if (individuals[i].getFitness() > individuals[maxFit2].getFitness()) {
				maxFit2 = i;
			}
		}
		try {
			return (Individual) individuals[maxFit2].clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Get index of least fittest individual
	public int getLeastFittestIndex() {
		int minFitVal = Integer.MAX_VALUE;
		int minFitIndex = 0;
		for (int i = 0; i < individuals.length; i++) {
			if (minFitVal >= individuals[i].getFitness()) {
				minFitVal = individuals[i].getFitness();
				minFitIndex = i;
			}
		}
		return minFitIndex;
	}
  
	//Get index of the fittest individual
	public int getFittestIndex() {
		int maxFit = Integer.MIN_VALUE;
		int maxFitIndex = 0;
		for (int i = 0; i < individuals.length; i++) {
			if (maxFit <= individuals[i].getFitness()) {
				maxFit = individuals[i].getFitness();
				maxFitIndex = i;
			}
		}
		return maxFitIndex;
		}

	//Calculate fitness of each individual
	public void calculateFitness() {
		for (int i = 0; i < individuals.length; i++) {
			individuals[i].calcFitness();
		}
		selectFittest();
	}
  
	//Getters and Setters
  
	public int getPopSize() {
		return popSize;
	}
	
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	
	public Individual[] getIndividuals() {
		return individuals;
	}
	
	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}
	
	public int getGeneLength() {
		return geneLength;
	}
	
	public void setGeneLength(int geneLength) {
		this.geneLength = geneLength;
	}

	
	public int getFittestScore() {
		return fittestScore;
	}
	

	public void setFittestScore(int fittestScore) {
		this.fittestScore = fittestScore;
	}

}