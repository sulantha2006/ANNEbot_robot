/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.*;
import java.io.Serializable;

/**
 *
 * @author dilmi
 */
public class Neuron implements Serializable{
    private static final long serialVersionUID = 9157839778494534700L;
    
    private double value = 0;
    private double threshold = 0;
    private String NeuronType;
    private String name;


    Neuron(String type){
        this.NeuronType = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double activation) {
        this.value = activation;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
