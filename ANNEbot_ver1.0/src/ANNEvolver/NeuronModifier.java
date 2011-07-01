/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.ANNConfiguration;

/**
 *
 * @author sulantha
 */
public class NeuronModifier {
    ANN newANN ;
    public NeuronModifier(){
        int inputNeuronCount = ANNConfiguration.inputNeuronCountConfig;
        int hiddenNeuronCount = ANNConfiguration.hiddenLNeuronCountConfig;
        int outputNeuronCount = ANNConfiguration.outputNeuronCountConfig;
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
    }
    public void createNewANN(ANN oldAnn){
        int inputNeuronCount = oldAnn.getInputNeuronCount();
        int hiddenNeuronCount = oldAnn.getHiddenLNeuronCount() + 1;
        int outputNeuronCount = oldAnn.getOutputNeuronCount();
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
        this.setNewConnections();
    }

    public void setNewConnections(){
        
    }

}
