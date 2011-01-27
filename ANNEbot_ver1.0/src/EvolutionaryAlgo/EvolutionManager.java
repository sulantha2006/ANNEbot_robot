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


    Genome createGenome(ANN oldANN){
        return new Genome(getWeights(oldANN),oldANN.getFitness());
    }

    public ANN evolveANN(ANN oldANN, int genCount){
        if (genCount == 0){
            createInitialPopulation();
        }
        return oldANN;
    }


    Matrix getWeights(ANN myANN){
        ArrayList<Double> temp = new ArrayList<Double>();
        //Matrix[] weights= new Matrix[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        //double weights[] = new double[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        for(int i = 0 ; i < myANN.getLayers().length ; i++){
            for(int j = 0 ; j < myANN.getLayers()[i].getNeuronCount() ; j++){
                //weights[i+j] = myANN.getLayers()[i].getNeurons()[j].getInputWeights();
                for(int k = 0; j < myANN.getLayers()[i].getNeurons()[j].getInputWeights().getNumOfCols(); k++ ){
                    temp.add(myANN.getLayers()[i].getNeurons()[j].getInputWeights().get(0, k));
                }
            }
        }
        Matrix weights = new Matrix(1,temp.size());
        for (int l = 0; l < temp.size(); l++){
            weights.set(0, l, temp.get(l));
        }
        return weights;
    }

    private void createInitialPopulation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }




}
