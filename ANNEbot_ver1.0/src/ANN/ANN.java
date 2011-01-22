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
    private int inputNeuronCount = 0;
    private int outputNeuronCount = 0;
    private int hiddenLNeuronCount = 0;
    private boolean hasAHiddenLayer = false;
    private NeuronLayer [] layers ;
    

    ANN(int numInputNeurons , int numOutputNeurons, boolean hasHL,int hLCount){
        this.inputNeuronCount = numInputNeurons ;
        this.outputNeuronCount = numOutputNeurons ;
        this.hasAHiddenLayer = hasHL;
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





}

