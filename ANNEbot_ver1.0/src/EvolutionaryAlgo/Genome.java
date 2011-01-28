/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import java.lang.Double;
/**
 *
 * @author dilmi
 */
public class Genome implements Comparable<Genome>{
    Matrix weightMatrix;
    double fitnessValue = 0.0;

    Genome(Matrix passweights){
        weightMatrix = passweights;
    }

    Genome(Matrix passweights, double passfitness){
        weightMatrix = passweights;
        fitnessValue = passfitness;
    }

    public void printGenome(){
        System.out.println("Genome Content : ");
        System.out.println("Weights : ");
        this.weightMatrix.printMatrix();
        System.out.println("Fitness : ");
        System.out.println(this.fitnessValue);
        System.out.println("END ----- ");
    }

    public int compareTo(Genome t) {
        if (this.fitnessValue < t.fitnessValue)
            return -1;
        if (this.fitnessValue > t.fitnessValue)
            return 1;
        return 0;
    }


    

}
