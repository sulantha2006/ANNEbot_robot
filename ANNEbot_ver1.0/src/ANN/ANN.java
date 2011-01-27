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
    private int inputNeuronCount = 0;
    private int outputNeuronCount = 0;
    private int hiddenLNeuronCount = 0;
    private boolean hasAHiddenLayer = false;
    private double fitness = 0;
    private NeuronLayer [] layers ;
    

    ANN(int numInputNeurons , int hLCount, int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hasAHiddenLayer = true;
        this.hiddenLNeuronCount = hLCount;
        this.initANN();
    }

    ANN(int numInputNeurons , int numOutputNeurons){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.initANN();
    }


    private void initANN(){
        if(this.hasAHiddenLayer){
            this.layers = new NeuronLayer[3];
            layers[0] = new NeuronLayer(true, this.inputNeuronCount);
            layers[1] = new NeuronLayer(this.hiddenLNeuronCount,this.inputNeuronCount,"Hidden Layer");
            layers[2] = new NeuronLayer(this.outputNeuronCount, this.hiddenLNeuronCount, "Output Layer");
        }

        else{
            this.layers = new NeuronLayer[2];
            layers[0] = new NeuronLayer(true, this.inputNeuronCount);
            layers[1] = new NeuronLayer(this.outputNeuronCount, this.inputNeuronCount, "Output Layer");
        }

    }

    public Matrix getWeights(){
        ArrayList<Double> temp = new ArrayList<Double>();
        //Matrix[] weights= new Matrix[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        //double weights[] = new double[myANN.getInputNeuronCount()+myANN.getHiddenLNeuronCount()+myANN.getOutputNeuronCount()];
        for(int i = 0 ; i < this.getLayers().length ; i++){
            for(int j = 0 ; j < this.getLayers()[i].getNeuronCount() ; j++){
                //weights[i+j] = myANN.getLayers()[i].getNeurons()[j].getInputWeights();
                for(int k = 0; j < this.getLayers()[i].getNeurons()[j].getInputWeights().getNumOfCols(); k++ ){
                    temp.add(this.getLayers()[i].getNeurons()[j].getInputWeights().get(0, k));
                }
            }
        }
        Matrix weights = new Matrix(1,temp.size());
        for (int l = 0; l < temp.size(); l++){
            weights.set(0, l, temp.get(l));
        }
        return weights;
    }

    public void setWeights(Matrix newWeights){
        System.out.println("New Weights set after evolution");
        for(int i = 0 ; i < this.getLayers().length ; i++){
            for(int j = 0 ; j < this.getLayers()[i].getNeuronCount() ; j++){
                Matrix weights = new Matrix(1,this.getLayers()[i].getNeurons()[j].getInputWeights().getNumOfCols());
                for(int k = 0; j < this.getLayers()[i].getNeurons()[j].getInputWeights().getNumOfCols(); k++ ){
                    weights.set(0, k, newWeights.get(0, i+j+k));
                }
                System.out.println("Weights of Layer"+i+"Neuron"+j);
                weights.printMatrix();
                System.out.println("");
            }
        }
    }




    public boolean isHasAHiddenLayer() {
        return hasAHiddenLayer;
    }

    public void setHasAHiddenLayer(boolean hasAHiddenLayer) {
        this.hasAHiddenLayer = hasAHiddenLayer;
    }

    public int getHiddenLNeuronCount() {
        return hiddenLNeuronCount;
    }

    public void setHiddenLNeuronCount(int hiddenLNeuronCount) {
        this.hiddenLNeuronCount = hiddenLNeuronCount;
    }

    public int getInputNeuronCount() {
        return inputNeuronCount;
    }

    public void setInputNeuronCount(int inputNeuronCount) {
        this.inputNeuronCount = inputNeuronCount;
    }

    public NeuronLayer[] getLayers() {
        return layers;
    }

    public void setLayers(NeuronLayer[] layers) {
        this.layers = layers;
    }

    public int getOutputNeuronCount() {
        return outputNeuronCount;
    }

    public void setOutputNeuronCount(int outputNeuronCount) {
        this.outputNeuronCount = outputNeuronCount;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }







}

