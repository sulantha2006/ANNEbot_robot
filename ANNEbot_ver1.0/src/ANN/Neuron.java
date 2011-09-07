/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

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
    private String transferFunc = "none";
    private double transferFuncConst = 0.1;


    Neuron(String type){
        this.NeuronType = type;       
    }
    Neuron(String type, String tFunc){
        this.NeuronType = type;
        this.transferFunc = tFunc;
    }
    Neuron(String type, String tFunc, double tFuncConstant){
        this.NeuronType = type;
        this.transferFunc = tFunc;
        this.transferFuncConst = tFuncConstant;
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

    public void setOutput(double input, String transferFunc){
        double output = 0;
        this.transferFunc = transferFunc;
        TransferFunction transferFunction = new TransferFunction();
        if(this.transferFunc.equalsIgnoreCase("step")){
            output = transferFunction.stepFunction(input);
        }
        else if(this.transferFunc.equalsIgnoreCase("sigmoid")){
            output = transferFunction.sigmoidFunction(input, this.transferFuncConst);
        }
        else if(this.transferFunc.equalsIgnoreCase("linear")){
            output = transferFunction.linear(input);
        }
        else
            output = input;
        
        this.value = output;
    }



}
