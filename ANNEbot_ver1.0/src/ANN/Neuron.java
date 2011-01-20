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
    private Matrix inputWeights;
    private double activation = 0;
    private double threshold = 10000;
    private boolean isInputNeuron = false;

    
    Neuron(int numOfweights, boolean cIsInputNeuron){
        this.inputWeights = new Matrix(1, numOfweights);
        this.isInputNeuron = cIsInputNeuron;
        if(this.isInputNeuron){

        }
    }


    public double getActivation() {
        return activation;
    }

    public void setActivation(double activation) {
        this.activation = activation;
    }

    public Matrix getInputWeights() {
        return inputWeights;
    }

    public void setInputWeights(Matrix inputWeights) {
        this.inputWeights = inputWeights;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isIsInputLayer() {
        return isInputNeuron;
    }

    public void setIsInputLayer(boolean isInputLayer) {
        this.isInputNeuron = isInputLayer;
    }

    



}
