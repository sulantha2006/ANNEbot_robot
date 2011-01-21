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
    boolean hasAHiddenLayer ;
    NeuronLayer [] layers ;
    

    ANN(int numInputNeurons , int numOutputNeurons, boolean hasHL){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hasAHiddenLayer = hasHL;
        this.initANN();
        
    }



    private void initANN(){
        if(this.hasAHiddenLayer){
            this.layers = new NeuronLayer[3];
            //layers[0] = new NeuronLayer
        }

        else{
            this.layers = new NeuronLayer[2];
        }


    }



}

