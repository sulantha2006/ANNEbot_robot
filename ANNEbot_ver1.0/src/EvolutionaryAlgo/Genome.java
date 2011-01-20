/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
/**
 *
 * @author dilmi
 */
public class Genome {
    Matrix weightMatrix;
    double fitnessValue;

    Genome(){
        fitnessValue = 0;
    }

    Genome(Matrix passweights, double passfitness){
        weightMatrix = passweights;
        fitnessValue = passfitness;
    }

    

}
