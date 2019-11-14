package fr.dieul.lab.geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

//Individual class
class Individual implements Cloneable{

	int geneLength;
	int fitness = 0;
	int[] genes;

	public Individual(int geneLength) {
		
		//Initialization
		this.geneLength = geneLength;
		this.genes = new int[geneLength];
		
		Random rn = new Random();
	
		//Set genes randomly for each individual
		for (int i = 0; i < genes.length; i++) {
		  genes[i] = Math.abs(rn.nextInt() % 2);
		}
	
		fitness = 0;
	}

  //Calculate fitness
  public void calcFitness() {

      fitness = 0;
      for (int i = 0; i < genes.length; i++) {
          if (genes[i] == 1) {
              ++fitness;
          }
      }
  }
  
  @Override
  protected Object clone() throws CloneNotSupportedException {
      Individual individual = (Individual)super.clone();
      individual.genes = new int[geneLength];
      for(int i = 0; i < individual.genes.length; i++){
          individual.genes[i] = this.genes[i];
      }
      return individual;
  }

	@Override
	public String toString() {
		return "[genes=" + Arrays.toString(genes) + "]";
	}
  
  

}