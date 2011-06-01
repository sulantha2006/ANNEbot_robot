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

    int DEBUG = 0;

    private int inputNeuronCount = 0;
    private int outputNeuronCount = 0;
    private int hiddenLNeuronCount = 0;
    private int totalNeuronCount;
    private boolean[][] hiddenNodes;
    private double fitness = 0;
    private Matrix weights;
    private boolean [][] connections;
    private Neuron[] neurons;


    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hiddenLNeuronCount = numHiddenNeurons;
        this.totalNeuronCount = this.inputNeuronCount+this.outputNeuronCount+this.hiddenLNeuronCount;
        this.hiddenNodes = new boolean[this.totalNeuronCount][this.totalNeuronCount];
        this.connections = new boolean[this.totalNeuronCount][this.totalNeuronCount];
        this.initANN();
    }

    public ANN(int numInputNeurons , int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.totalNeuronCount = this.inputNeuronCount+this.outputNeuronCount+this.hiddenLNeuronCount;
        this.connections = new boolean[this.totalNeuronCount][this.totalNeuronCount];
        this.initANN();
    }

    private void initANN(){
        this.weights = new Matrix(this.totalNeuronCount,this.totalNeuronCount);
        this.connections = new boolean [this.totalNeuronCount][this.totalNeuronCount];
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

        public double[] produceOutput(double []input){
        double output [] = new double [this.getOutputNeuronCount()] ;

        //for Input Neurons
        for(int i = 0 ; i < this.getInputNeuronCount() ; i++){
            this.getNeurons()[i].setValue(input[i]);
        }
        //for Hidden Neurons
        double value = 0;
        for(int i = this.getInputNeuronCount() ; i < (this.getTotalNeuronCount()-this.getOutputNeuronCount()); i++){
            for(int j = 0 ; j< this.getTotalNeuronCount() ; j++){
                value = value + (this.getNeurons()[j].getValue()*this.getWeights().get(j, i));
            }
            if(value > this.getNeurons()[i].getThreshold())this.getNeurons()[i].setValue(value);
        }

        //for Output Neurons
        value = 0;
        for(int i = this.getTotalNeuronCount()-this.getOutputNeuronCount() ; i < this.getTotalNeuronCount(); i++){
            for(int j = 0 ; j< this.getTotalNeuronCount(); j++){
                value = value + (this.getNeurons()[j].getValue()*this.getWeights().get(j, i));
            }
            if(value > this.getNeurons()[i].getThreshold())this.getNeurons()[i].setValue(value);
            output[i-(this.getTotalNeuronCount()-this.getOutputNeuronCount())] = value;
        }
        return output;

    }

    public String[][] ANNstats(){
        String[][] stats = new String[5][2];
        stats[0][0] = "Input Neurons : ";
        stats[0][1] = Integer.toString(this.inputNeuronCount);

        stats[1][0] = "Hidden Neurons : ";
        stats[1][1] = Integer.toString(this.hiddenLNeuronCount);

        stats[2][0] = "Output Neurons : ";
        stats[2][1] = Integer.toString(this.outputNeuronCount);

        stats[3][0] = "Total Neurons : ";
        stats[3][1] = Integer.toString(this.totalNeuronCount);

        stats[4][0] = "Total Connection Weights : ";
        int totalconnectionsweights = 0;
        for (int i = 0; i < totalNeuronCount; i++){
           for (int j = 0; j < totalNeuronCount; j++){
               if (connections[i][j]){
                   totalconnectionsweights = totalconnectionsweights + 1;
               }
               }
           }
        stats[4][1] = Integer.toString(totalconnectionsweights);
        return stats;
    }

    public Matrix getWeights(){
        return this.weights;
    }

    public void setWeights(Matrix newWeights){
        this.weights = newWeights;
    }

    public boolean[][] getConnections() {
        return connections;
    }

    public void setConnections(boolean[][] connections) {
        this.connections = connections;
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

    public boolean[][] getHiddenNodes() {
        return hiddenNodes;
    }

    public void setHiddenNodes(boolean[][] hiddenNodes) {
        this.hiddenNodes = hiddenNodes;
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

