/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import java.util.Random;


/**
 *
 * @author sulantha
 */
public class GeneticAlgorithm {
    Genome[] genomePopulation;
    int sizeOfPopulation;
    int genomeLength;
    int sumOfFitness;
    int bestFitness;
    int avaerageFitness;
    int worstFitness;
    int indexOfBestGenome;

    double maxMutationFactor = 0.3;
    double crossoverRate;
    double mutationRate;

    int Generation;


    Genome getGenomeRoulette(){
        Genome theChosenOne = null;
        Random randomNum = new Random();
        double value = randomNum.nextDouble()*sumOfFitness;
        double cumalativeFitness = 0;
        for(int i = 0; i < sizeOfPopulation; i++){
            cumalativeFitness = cumalativeFitness + genomePopulation[i].fitnessValue;
            if (cumalativeFitness > value){
                theChosenOne = genomePopulation[i];
                break;
            }
        }
        return theChosenOne;
    }

    Genome mutate(Genome origGenome){
        Genome mutatedGenome = null;
        for (int i = 0; i < genomeLength; i++){
            Random randomNum = new Random();
            double value = randomNum.nextDouble();
            if (value <= mutationRate){
                origGenome.weightMatrix.set(0, i, origGenome.weightMatrix.get(0, i)+(randomNum.nextDouble()-randomNum.nextDouble())*maxMutationFactor);
            }
        }
        mutatedGenome = origGenome;
        return mutatedGenome;
    }
    

}
