/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.*;

/**
 *
 * @author Dilmi
 */
public class ANN {
    int inputNeuronCount = 0;
    int outputNeuronCount = 0;
    int hiddenLayerCount = 0;
    int[] hiddenLayerNeuronCount ;
    NeuronLayer inputLayer;

    ANN(int numInputNeurons , int numOutputNeurons, int hlNcount){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hiddenLayerCount = 1;
        this.hiddenLayerNeuronCount = new int[1];
        this.hiddenLayerNeuronCount[0] = hlNcount;
        this.initANN();
        
    }

    private void initANN(){
        

    }



}

