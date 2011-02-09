/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

import Utility.*;


/**
 *
 * @author sulantha
 */
public class GeneticAlgorithm {

    int DEBUG = 0;

    ArrayList<Genome> genomePopulation = new ArrayList<Genome>();
    int sizeOfPopulation;
    int genomeLength;
    double sumOfFitness;
    int bestFitness;
    int avaerageFitness;
    int worstFitness;
    int indexOfBestGenome;

    int numberOfEliteGenomesToCopy = 2;
    int numberOfCopiesPerGenome = 1;

    double maxMutationFactor = 0.3;
    double crossoverRate = 0.7;
    double mutationRate = 0.1;

    Matrix zeroMatrix;

    int Generation;




    Genome getGenomeRoulette(){
        Genome theChosenOne = null;
        Random randomNum = new Random();
        double value = randomNum.nextDouble()*sumOfFitness;
        double cumalativeFitness = 0;
        for(int i = 0; i < sizeOfPopulation; i++){
            cumalativeFitness = cumalativeFitness + genomePopulation.get(sizeOfPopulation -1 -i).fitnessValue;
            if (cumalativeFitness > value){
                theChosenOne = genomePopulation.get(sizeOfPopulation -1 -i);
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
        zeroMatrix = new Matrix(1, genomeLength);
        for (int j = 0; j < genomeLength ; j++){
            zeroMatrix.set(0, j, 0);
        }
        babyArray[0] = new Genome(zeroMatrix);
        zeroMatrix = new Matrix(1, genomeLength);
        for (int j = 0; j < genomeLength ; j++){
            zeroMatrix.set(0, j, 0);
        }
        babyArray[1] = new Genome(zeroMatrix);

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
        setBackgroundVars();

        if (DEBUG == 1){
            System.out.println("Genome Population print (Before sort) ...");
            for (int i = 0; i < genomePopulation.size();i++){
                genomePopulation.get(i).printGenome();
            }
        }

        Collections.sort(genomePopulation); //Working. (Checked)

        if (DEBUG == 1){
            System.out.println("Genome Population print (After sort) ...");
            for (int i = 0; i < genomePopulation.size();i++){
                genomePopulation.get(i).printGenome();
            }
        }

        newPopulation = copyNBestFittedGenomesToNewPopulation(numberOfEliteGenomesToCopy, numberOfCopiesPerGenome, newPopulation);
        while (newPopulation.size()<genomePopulation.size()){
            Genome mom = getGenomeRoulette();
            if(DEBUG == 1){
                System.out.println("MOM: ");
                mom.weightMatrix.printMatrix();
            }
            Genome dad = getGenomeRoulette();
            if(DEBUG == 1){
                System.out.println("DAD: ");
                dad.weightMatrix.printMatrix();
            }
            Genome baby1 = null;
            Genome baby2 = null;

            Genome[] babyArray = crossover(mom, dad);
            baby1 = babyArray[0];
            if(DEBUG == 1){
                System.out.println("Baby1: ");
                baby1.weightMatrix.printMatrix();
            }
            baby2 = babyArray[1];
            if(DEBUG == 1){
                System.out.println("Baby2: ");
                baby2.weightMatrix.printMatrix();
            }

            baby1 = mutate(baby1);
            if(DEBUG == 1){
                System.out.println("Baby1: ");
                baby1.weightMatrix.printMatrix();
            }

            baby2 = mutate(baby2);
            if(DEBUG == 1){
                System.out.println("Baby2: ");
                baby2.weightMatrix.printMatrix();
            }

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

    private void setBackgroundVars() {
        sumOfFitness = 0;
        sizeOfPopulation = genomePopulation.size();
        for(int g = 0; g < sizeOfPopulation; g++){
            sumOfFitness += genomePopulation.get(g).fitnessValue;
        }
        genomeLength = genomePopulation.get(0).weightMatrix.getNumOfCols();

        

    }



    

}
