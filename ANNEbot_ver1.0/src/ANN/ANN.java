/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.*;
import java.util.ArrayList;

/**
 *
 * @author Dilmi
 */
public class ANN {

    int DEBUG = 0;

    private int inputNeuronCount = 0;
    private int outputNeuronCount = 0;
    private int hiddenLNeuronCount = 0;
    private int totalNeuronCount;
    private boolean hasAHiddenNeurons = false;
    private double fitness = 0;
    private Matrix weights;
    private Neuron[] neurons;


    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hiddenLNeuronCount = numHiddenNeurons;
        this.hasAHiddenNeurons = true;
        this.initANN();
    }

    public ANN(int numInputNeurons , int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.initANN();
    }




    private void initANN(){
        this.totalNeuronCount = this.inputNeuronCount+this.outputNeuronCount+this.hiddenLNeuronCount;
        this.weights = new Matrix(this.totalNeuronCount,this.totalNeuronCount);
        this.neurons = new Neuron[this.totalNeuronCount];
        for(int i = 0 ; i < this.totalNeuronCount ; i++){
            if(i<this.inputNeuronCount){
                neurons[i] = new Neuron("Input Neuron");
            }
            else if(i<=this.inputNeuronCount && i<(this.inputNeuronCount+this.hiddenLNeuronCount)){
                neurons[i] = new Neuron("Hidden Neuron");
            }
            else
                neurons[i] = new Neuron("Output Neuron");
        }

    }



    public Matrix getWeights(){
        return this.weights;
    }

    public void setWeights(Matrix newWeights){
        this.weights = newWeights;
    }

    public boolean isHasAHiddenLayer() {
        return hasAHiddenNeurons;
    }

    public void setHasAHiddenLayer(boolean hasAHiddenLayer) {
        this.hasAHiddenNeurons = hasAHiddenLayer;
    }

    public int getInputNeuronCount() {
        return inputNeuronCount;
    }

    public void setInputNeuronCount(int inputNeuronCount) {
        this.inputNeuronCount = inputNeuronCount;
    }

    public int getOutputNeuronCount() {
        return outputNeuronCount;
    }

    public void setOutputNeuronCount(int outputNeuronCount) {
        this.outputNeuronCount = outputNeuronCount;
    }

    public int getDEBUG() {
        return DEBUG;
    }

    public void setDEBUG(int DEBUG) {
        this.DEBUG = DEBUG;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public boolean isHasAHiddenNeurons() {
        return hasAHiddenNeurons;
    }

    public void setHasAHiddenNeurons(boolean hasAHiddenNeurons) {
        this.hasAHiddenNeurons = hasAHiddenNeurons;
    }

    public int getHiddenLNeuronCount() {
        return hiddenLNeuronCount;
    }

    public void setHiddenLNeuronCount(int hiddenLNeuronCount) {
        this.hiddenLNeuronCount = hiddenLNeuronCount;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    public int getTotalNeuronCount() {
        return totalNeuronCount;
    }

    public void setTotalNeuronCount(int totalNeuronCount) {
        this.totalNeuronCount = totalNeuronCount;
    }

}

