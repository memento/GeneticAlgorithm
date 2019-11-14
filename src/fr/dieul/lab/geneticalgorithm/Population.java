package fr.dieul.lab.geneticalgorithm;

//Population class
class Population {

  int popSize;
  Individual[] individuals;
  int fittest = 0;

  //Initialize population
  public void initializePopulation(int popSize) {
	  //initialize variables
	  this.popSize = popSize;
	  this.individuals = new Individual[popSize];
	  
	  
      for (int i = 0; i < popSize; i++) {
          individuals[i] = new Individual();
      }
  }

  //Get the fittest individual
  public Individual getFittest() {
      int maxFit = Integer.MIN_VALUE;
      int maxFitIndex = 0;
      for (int i = 0; i < individuals.length; i++) {
          if (maxFit <= individuals[i].fitness) {
              maxFit = individuals[i].fitness;
              maxFitIndex = i;
          }
      }
      fittest = individuals[maxFitIndex].fitness;
      try {
          return (Individual) individuals[maxFitIndex].clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return null;
  }

  //Get the second most fittest individual
  public Individual getSecondFittest() {
      int maxFit1 = 0;
      int maxFit2 = 0;
      for (int i = 0; i < individuals.length; i++) {
          if (individuals[i].fitness > individuals[maxFit1].fitness) {
              maxFit2 = maxFit1;
              maxFit1 = i;
          } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
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
          if (minFitVal >= individuals[i].fitness) {
              minFitVal = individuals[i].fitness;
              minFitIndex = i;
          }
      }
      return minFitIndex;
  }

  //Calculate fitness of each individual
  public void calculateFitness() {

      for (int i = 0; i < individuals.length; i++) {
          individuals[i].calcFitness();
      }
      getFittest();
  }

}