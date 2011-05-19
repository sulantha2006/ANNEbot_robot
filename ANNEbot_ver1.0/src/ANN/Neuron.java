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
        private double activation = 0;
    private double threshold = 10000;
    private String NeuronType;


    Neuron(String type){
        this.NeuronType = type;
    }

    public double getActivation() {
        return activation;
    }

    public void setActivation(double activation) {
        this.activation = activation;
    }

    public String getNeuronType() {
        return NeuronType;
    }

    public void setNeuronType(String NeuronType) {
        this.NeuronType = NeuronType;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }



}
