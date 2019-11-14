package fr.dieul.lab.geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

//Individual class
class Individual implements Cloneable{

  int fitness = 0;
  int[] genes = new int[5];
  int geneLength = 5;

  public Individual() {
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
      for (int i = 0; i < 5; i++) {
          if (genes[i] == 1) {
              ++fitness;
          }
      }
  }
  
  @Override
  protected Object clone() throws CloneNotSupportedException {
      Individual individual = (Individual)super.clone();
      individual.genes = new int[5];
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