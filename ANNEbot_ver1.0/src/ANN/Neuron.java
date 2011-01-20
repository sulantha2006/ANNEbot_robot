/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.*;

/**
 *
 * @author dilmi
 */
public class Neuron {
    Matrix inputWeights;
    double activation = 0;
    double threshold = 10000;

    Neuron(int numOfweights){
        inputWeights = new Matrix(1, numOfweights);
        
    }



}
