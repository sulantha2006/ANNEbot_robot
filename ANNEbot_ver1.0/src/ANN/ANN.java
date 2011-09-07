/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import Utility.*;
import java.io.Serializable;

/**
 *
 * @author Dilmi
 */
public class ANN implements Serializable{
    private static final long serialVersionUID = 9157839778494534699L;

    int DEBUG = 0;

    private int inputNeuronCount = 0;
    private int outputNeuronCount = 0;
    private int hiddenLNeuronCount = 0;
    private int totalNeuronCount;    
    private double fitness = 0;
    private Matrix weights;
    private boolean [][] connections;
    private Neuron[] neurons;
    private String hlTransferFunc = "step";
    private double hlConst = 1;
    private String olTransferFunc= "linear";
    private double olConst = 1;
    
  
    public ANN(int numInputNeurons , int numOutputNeurons){
        this.initANN(numInputNeurons,0,numOutputNeurons);
    }

    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons){
        this.initANN(numInputNeurons,numHiddenNeurons,numOutputNeurons);
    }
    
    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons, String hlTransferF,String olTransferF ){
        this.hlTransferFunc = hlTransferF;
        this.olTransferFunc = olTransferF;
        this.initANN(numInputNeurons,numHiddenNeurons,numOutputNeurons);
    }
    
    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons, String hlTransferF, double hlconstant, String olTransferF ){
        this.hlTransferFunc = hlTransferF;
        this.hlConst = hlconstant;
        this.olTransferFunc = olTransferF;
        this.initANN(numInputNeurons,numHiddenNeurons,numOutputNeurons);
    }
    
    public ANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons, String hlTransferF, double hlconstant, String olTransferF, double olconstant ){
        this.hlTransferFunc = hlTransferF;
        this.hlConst = hlconstant;
        this.olTransferFunc = olTransferF;
        this.olConst = olconstant;
        this.initANN(numInputNeurons,numHiddenNeurons,numOutputNeurons);
    }
    
    private void initANN(int numInputNeurons , int numHiddenNeurons, int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hiddenLNeuronCount = numHiddenNeurons;
        this.totalNeuronCount = this.inputNeuronCount+this.outputNeuronCount+this.hiddenLNeuronCount;        
        this.connections = new boolean[this.totalNeuronCount][this.totalNeuronCount];
        
        this.weights = new Matrix(this.totalNeuronCount,this.totalNeuronCount);
        this.connections = new boolean [this.totalNeuronCount][this.totalNeuronCount];
        this.neurons = new Neuron[this.totalNeuronCount];
        for(int i = 0 ; i < this.totalNeuronCount ; i++){
            if(i<this.inputNeuronCount){
                neurons[i] = new Neuron("Input Neuron");
            }
            else if(i<=this.inputNeuronCount && i<(this.inputNeuronCount+this.hiddenLNeuronCount)){
                if(this.hlConst==1)neurons[i] = new Neuron("Hidden Neuron",this.hlTransferFunc);
                else neurons[i] = new Neuron("Hidden Neuron",this.hlTransferFunc,this.hlConst);
            }
            else
                if(this.olConst==1)neurons[i] = new Neuron("Output Neuron",this.olTransferFunc);
                else neurons[i] = new Neuron("Output Neuron",this.olTransferFunc,this.olConst);
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
    
     public double[] produceCorrectOutput(double []input,String hlTransferFunc, String olTransferFunc){
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
            this.getNeurons()[i].setOutput(value + this.getNeurons()[i].getThreshold(), hlTransferFunc);
        }

        //for Output Neurons
        value = 0;
        for(int i = this.getTotalNeuronCount()-this.getOutputNeuronCount() ; i < this.getTotalNeuronCount(); i++){
            for(int j = 0 ; j< this.getTotalNeuronCount(); j++){
                value = value + (this.getNeurons()[j].getValue()*this.getWeights().get(j, i));
            }
            this.getNeurons()[i].setOutput(value + this.getNeurons()[i].getThreshold(), olTransferFunc);
            output[i-(this.getTotalNeuronCount()-this.getOutputNeuronCount())] = this.getNeurons()[i].getValue();
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

     public void initConnectionMatrix() {
        for(int i = 0; i < this.inputNeuronCount; i++){
            for(int j = this.inputNeuronCount; j < this.totalNeuronCount;j++){
                this.getConnections()[i][j] = true;
            }
        }
        for(int i = 0; i < this.hiddenLNeuronCount; i++){
            for(int j = this.inputNeuronCount; j < this.totalNeuronCount;j++){
                if(this.inputNeuronCount+i == j){
                    continue;
                }else{
                    this.getConnections()[this.inputNeuronCount+i][j] = true;
                }

            }
        }
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

    public int getOutputNeuronCount() {
        return outputNeuronCount;
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

    public int getHiddenLNeuronCount() {
        return hiddenLNeuronCount;
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
    
    public double getHlConst() {
        return hlConst;
    }

    public void setHlConst(double hlConst) {
        this.hlConst = hlConst;
    }

    public String getHlTransferFunc() {
        return hlTransferFunc;
    }

    public void setHlTransferFunc(String hlTransferFunc) {
        this.hlTransferFunc = hlTransferFunc;
    }

    public double getOlConst() {
        return olConst;
    }

    public void setOlConst(double olConst) {
        this.olConst = olConst;
    }

    public String getOlTransferFunc() {
        return olTransferFunc;
    }

    public void setOlTransferFunc(String olTransferFunc) {
        this.olTransferFunc = olTransferFunc;
    }
}

