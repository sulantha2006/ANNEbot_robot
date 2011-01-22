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
    double fitnessValue = 0;

    Genome(Matrix passweights){
        weightMatrix = passweights;
    }

    Genome(Matrix passweights, double passfitness){
        weightMatrix = passweights;
        fitnessValue = passfitness;
    }

    

}
