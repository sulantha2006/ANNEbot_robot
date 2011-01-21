/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

/**
 *
 * @author dilmi
 */
public class NeuronLayer {
    private String name = "";
    private boolean isInputLayer = false;
    private int neuronCount =0;
    private int neuronWeightCount = 0;
    Neuron [] neurons;

    public NeuronLayer(boolean cIsInputLayer, int cNcount , int cWcount){
        this.isInputLayer = cIsInputLayer;
        this.neuronCount = cNcount;
        this.neuronWeightCount = cWcount;
        this.neurons = new Neuron[this.neuronCount];
    }

    public NeuronLayer(boolean cIsInputLayer, int cNcount , int cWcount, String cName){
        this.isInputLayer = cIsInputLayer;
        this.neuronCount = cNcount;
        this.neuronWeightCount = cWcount;
        this.neurons = new Neuron[this.neuronCount];
        this.name = cName;
    }

    public boolean isIsInputLayer() {
        return isInputLayer;
    }

    public void setIsInputLayer(boolean isInputLayer) {
        this.isInputLayer = isInputLayer;
    }

    public int getNeuronCount() {
        return neuronCount;
    }

    public void setNeuronCount(int neuronCount) {
        this.neuronCount = neuronCount;
    }

    public int getNeuronWeightCount() {
        return neuronWeightCount;
    }

    public void setNeuronWeightCount(int neuronWeightCount) {
        this.neuronWeightCount = neuronWeightCount;
    }




}
