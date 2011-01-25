/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.Matrix;

/**
 *
 * @author dilmi
 */
public class ANNTrainer {
    ANN ann;

    public ANNTrainer(ANN myAnn) {
        this.ann = myAnn;
    }
   
    Matrix[] getWeights(ANN myANN){
        Matrix[] weights= new Matrix[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        for(int i = 0 ; i < myANN.getLayers().length ; i++){
            for(int j = 0 ; j < myANN.getLayers()[i].getNeuronCount() ; j++){
                weights[i+j] = myANN.getLayers()[i].neurons[j].getInputWeights();
            }
        }
        return weights;
    }

}
