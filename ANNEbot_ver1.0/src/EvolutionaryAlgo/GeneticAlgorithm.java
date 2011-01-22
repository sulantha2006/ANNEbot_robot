/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


/**
 *
 * @author sulantha
 */
public class GeneticAlgorithm {
    ArrayList<Genome> genomePopulation = new ArrayList<Genome>();
    int sizeOfPopulation = genomePopulation.size();
    int genomeLength;
    int sumOfFitness;
    int bestFitness;
    int avaerageFitness;
    int worstFitness;
    int indexOfBestGenome;

    int numberOfEliteGenomesToCopy = 2;
    int numberOfCopiesPerGenome = 1;

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
            cumalativeFitness = cumalativeFitness + genomePopulation.get(i).fitnessValue;
            if (cumalativeFitness > value){
                theChosenOne = genomePopulation.get(i);
                break;
            }
        }
        return theChosenOne;
    }

    Genome mutate(Genome origGenome){
        for (int i = 0; i < genomeLength; i++){
            Random randomNum = new Random();
            double value = randomNum.nextDouble();
            if (value <= mutationRate){
                origGenome.weightMatrix.set(0, i, origGenome.weightMatrix.get(0, i)+(randomNum.nextDouble()-randomNum.nextDouble())*maxMutationFactor);
            }
        }
        return origGenome;
    }

    Genome[] crossover(Genome mom, Genome dad){
        Genome[] babyArray = new Genome[2];

        Random randomNum = new Random();
        if ((randomNum.nextDouble()>crossoverRate)||(mom == dad)){
            babyArray[0] = mom;
            babyArray[1] = dad;

            return babyArray;
        }

        int crossoverPoint = randomNum.nextInt(genomeLength - 1);

        for (int i = 0; i < crossoverPoint; i++){
            babyArray[0].weightMatrix.set(0, i, mom.weightMatrix.get(0, i));
            babyArray[1].weightMatrix.set(0, i, dad.weightMatrix.get(0, i));
        }

        for (int i = crossoverPoint; i < genomeLength; i++){
            babyArray[0].weightMatrix.set(0, i, dad.weightMatrix.get(0, i));
            babyArray[1].weightMatrix.set(0, i, mom.weightMatrix.get(0, i));
        }

        return babyArray;
    }

    ArrayList<Genome> getNewPopulation(ArrayList<Genome> oldPopulation){
        ArrayList<Genome> newPopulation = new ArrayList<Genome>();
        genomePopulation = oldPopulation;
        Collections.sort(genomePopulation); //Check this
        newPopulation = copyNBestFittedGenomesToNewPopulation(numberOfEliteGenomesToCopy, numberOfCopiesPerGenome, newPopulation);
        while (newPopulation.size()<genomePopulation.size()){
            Genome mom = getGenomeRoulette();
            Genome dad = getGenomeRoulette();
            Genome baby1 = null;
            Genome baby2 = null;

            Genome[] babyArray = crossover(mom, dad);
            baby1 = babyArray[0];
            baby2 = babyArray[1];

            baby1 = mutate(baby1);
            baby2 = mutate(baby2);

            newPopulation.add(baby1);
            newPopulation.add(baby2);
        }

        return newPopulation;
    }

    ArrayList<Genome> copyNBestFittedGenomesToNewPopulation(int numberOfEliteGenomesToCopy, int numberOfCopiesPerGenome, ArrayList<Genome> newPopulation) {
        for(int i = 0; i < numberOfEliteGenomesToCopy; i++){
            for (int j = 0; j < numberOfCopiesPerGenome; j++){
                newPopulation.add(genomePopulation.get(genomePopulation.size()-1-i));
            }
        }
        return newPopulation;
    }



    

}
