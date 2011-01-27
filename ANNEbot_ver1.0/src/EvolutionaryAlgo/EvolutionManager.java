/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EvolutionaryAlgo;
import Utility.*;
import ANN.*;

/**
 *
 * @author sulantha
 */
public class EvolutionManager {



    Matrix[] getWeights(ANN myANN){
        Matrix[] weights= new Matrix[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        for(int i = 0 ; i < myANN.getLayers().length ; i++){
            for(int j = 0 ; j < myANN.getLayers()[i].getNeuronCount() ; j++){
                weights[i+j] = myANN.getLayers()[i].getNeurons()[j].getInputWeights();
            }
        }
        return weights;
    }



}
