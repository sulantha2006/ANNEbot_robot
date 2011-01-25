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
    private Neuron [] neurons;

    //Constructor for Input Layer
    public NeuronLayer(boolean cIsInputLayer, int cNcount){
        this.isInputLayer = cIsInputLayer;
        this.neuronCount = cNcount;
        this.neuronWeightCount = 1;
        this.name = "Input Layer";
        this.neurons = new Neuron[this.neuronCount];
        this.initNeuronLayer();
    }

    //Constructor for any layer other that Input Layer
    public NeuronLayer(int cNcount , int cWcount, String cName){
        this.neuronCount = cNcount;
        this.neuronWeightCount = cWcount;
        this.neurons = new Neuron[this.neuronCount];
        this.name = cName;
        this.initNeuronLayer();
    }

    //Initialize the Neuron Layer by creating the Neuron objects in the neurons array
    private void initNeuronLayer(){
        for(int i = 0 ; i < this.neuronCount ; i++){
            this.neurons[i] = new Neuron(this.neuronWeightCount, this.isInputLayer);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
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
