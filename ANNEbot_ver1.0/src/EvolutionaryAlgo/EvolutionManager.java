/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import ANN.*;
import java.util.ArrayList;

/**
 *
 * @author sulantha
 */
public class EvolutionManager {
    int populationSize = 10;
    int genomeIndex = 0;
    ArrayList<Genome> oldPopulation = new ArrayList<Genome>();
    ArrayList<Genome> newPopulation = new ArrayList<Genome>();


    Genome createGenome(Matrix weights, double fitness){
        return new Genome(weights, fitness);
    }

    public ANN evolveANN(ANN oldANN, int genCount){
        if (genCount == 0){
            createInitialPopulation();
            Matrix newWeights = oldPopulation.get(genomeIndex).weightMatrix;
            double newFitness = oldPopulation.get(genomeIndex).fitnessValue;
            //oldANN.setWeights(newWeights);
            //oldANN.setFitness(newFitness);

        }else{
            //Matrix oldWeights = oldANN.getWeights();
            //double oldFitness = oldANN.getFitness();
            //genomeFromOldData = createGenome(oldWeights, oldFitness);
            //newPopulation.set(genomeIndex, genomeFromOldData);

        }
        return returnANN;
    }

    ANN getANNFromGenome(Genome genome){
        return new ANN;
    }


    private void createInitialPopulation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }




}
