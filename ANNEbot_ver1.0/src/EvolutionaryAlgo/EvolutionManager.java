/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import ANN.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sulantha
 */
public class EvolutionManager {

    int DEBUG = 2;
    
    int populationSize = 10;
    int genomeIndex = 0;
    ArrayList<Genome> oldPopulation = new ArrayList<Genome>();
    ArrayList<Genome> newPopulation = new ArrayList<Genome>();

    GeneticAlgorithm genAlgo = new GeneticAlgorithm();
    int genCount;

    public int getGenCount() {
        return genCount;
    }

    


    Genome createGenome(Matrix weights, double fitness){
        return new Genome(weights, fitness);
    }

    public ANN evolveANN(ANN oldANN, int popCount){
        if(DEBUG == 1){
            System.out.println("Evolve comm recieved. Population Count : " + popCount);
            System.out.println("Its fitness : " + oldANN.getFitness());
        }
        if (popCount == 0){
            createInitialPopulation(oldANN.getWeights().getNumOfCols()+1);
            Matrix newWeights = oldPopulation.get(genomeIndex).weightMatrix;
            double newFitness = oldPopulation.get(genomeIndex).fitnessValue;

            if(DEBUG == 1){
                System.out.println("Before setting to new ANN");
                newWeights.printMatrix();
            }

            oldANN.setWeights(newWeights);

            if(DEBUG == 1){
                System.out.println("After setting to new ANN");
                oldANN.getWeights().printMatrix();
            }

            oldANN.setFitness(newFitness);
            genomeIndex++;

        }else{
            Matrix oldWeights = oldANN.getWeights();
            double oldFitness = oldANN.getFitness();
            if(DEBUG == 1){
                    System.out.println("Data for new Genome : ");
                    //oldWeights.printMatrix();
                    oldANN.getWeights().printMatrix();
            }
            Genome genomeFromOldData = createGenome(oldWeights, oldFitness);
            if(DEBUG == 1){
                    System.out.println("New Genome created from old data and new fitness : ");
                    genomeFromOldData.printGenome();
            }
            newPopulation.add(genomeFromOldData);
            if (newPopulation.size() == populationSize){
                if(DEBUG == 1){
                    System.out.println("Evolution Happening ");
                }
                oldPopulation = genAlgo.getNewPopulation(newPopulation);
                newPopulation = new ArrayList<Genome>();
                if(DEBUG == 1){
                    System.out.println("newPopulation Sizex : " + newPopulation.size());
                }
                genomeIndex = 0;
                genCount++;
                if(DEBUG == 2){
                    System.out.println("Size of old Population :  " + oldPopulation.size());
                    System.out.println("Generation Count : " + genCount);
                    System.out.println("Population recieved from Evolution : ");
                    for(int g = 0; g < oldPopulation.size(); g++){
                        oldPopulation.get(g).weightMatrix.printMatrix();
                        //System.out.println(oldPopulation.get(g).toString());
                    }
                    System.out.println("END Print........");
                }
            }
            if(DEBUG == 1){
                    System.out.println("GenomeIndex count :  " + genomeIndex);
            }
            
            Matrix newWeights = oldPopulation.get(genomeIndex).weightMatrix;
            double newFitness = oldPopulation.get(genomeIndex).fitnessValue;
            oldANN.setWeights(newWeights);
            oldANN.setFitness(newFitness);
            genomeIndex++;

        }
        if(DEBUG == 1){
            System.out.println("newPopulation size " + oldPopulation.size());
        }
        return oldANN;
    }


    private void createInitialPopulation(int matrixSize) {
        for(int i = 0; i<populationSize;i++){
            Random rand = new Random();
            Matrix weightMatrix = new Matrix(1, matrixSize);
            for (int j = 0; j < matrixSize ; j++){
                double initWeights = rand.nextDouble();
                weightMatrix.set(0, j, initWeights);
                
            }
            oldPopulation.add(new Genome(weightMatrix));
        }
    }




}
