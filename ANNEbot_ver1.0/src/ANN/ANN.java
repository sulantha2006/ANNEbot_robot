/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.Matrix;

/**
 *
 * @author Dilmi
 */
public class ANN {
    int inputNeuronCount = 0;
    int outputNeuronCount = 0;
    Matrix inputNeurons;
    Matrix outputNeurons;
    Matrix weightMatrix;

    ANN(int numInputNeurons , int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.inputNeurons = new Matrix(this.inputNeuronCount, 1);
        this.outputNeurons = new Matrix(this.outputNeuronCount, 1);
        this.weightMatrix = new Matrix(this.outputNeuronCount , (this.inputNeuronCount + 1));

    }

}

