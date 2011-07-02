/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.ANNConfiguration;
import Utility.Matrix;

/**
 *
 * @author sulantha
 */
public class NeuronModifier {
    ANN newANN ;
    ANN oldANN;
    public NeuronModifier(){
        int inputNeuronCount = ANNConfiguration.inputNeuronCountConfig;
        int hiddenNeuronCount = ANNConfiguration.hiddenLNeuronCountConfig;
        int outputNeuronCount = ANNConfiguration.outputNeuronCountConfig;
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
    }
    public void createNewANN(ANN pOldANN){
        this.oldANN = pOldANN;
        int inputNeuronCount = oldANN.getInputNeuronCount();
        int hiddenNeuronCount = oldANN.getHiddenLNeuronCount() + 1;
        int outputNeuronCount = oldANN.getOutputNeuronCount();
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
        int highstConnctdHNIndex = this.getHighestConnectedHN();
        this.setNewConnections(highstConnctdHNIndex);
    }

    private int getHighestConnectedHN(){
        double avgWeightofHiddenNeurons[] = new double [this.oldANN.getHiddenLNeuronCount()];
        Matrix weights = this.oldANN.getWeights();
        double sum = 0;
        int max = 0;
        for (int i = 0; i < avgWeightofHiddenNeurons.length; i++) {
            sum = 0;
            for (int j = 0; j < weights.getNumOfCols(); j++) {
                sum+=weights.get(i+this.oldANN.getInputNeuronCount(), j);
            }
            avgWeightofHiddenNeurons[i] = sum;
            //keeps track of the max
            if (avgWeightofHiddenNeurons[max]<sum) {
                max = i;
            }
        }
        return max+oldANN.getInputNeuronCount();
    }

    private void setNewConnections(int highstConnctdHNIndex) {
        Matrix oldWeights = this.oldANN.getWeights();
        Matrix newWeights = this.newANN.getWeights();
        boolean[][] oldConnections = this.oldANN.getConnections();
        boolean[][] newConnections = this.newANN.getConnections();
        for (int i = 0; i < oldWeights.getNumOfRows()+1; i++) {
            for (int j = 0; j <= oldWeights.getNumOfCols()+1; j++) {
                if(i<highstConnctdHNIndex&&j<highstConnctdHNIndex){
                    newWeights.set(i, j, oldWeights.get(i, j));
                    newConnections[i][j] = oldConnections[i][j];
                }
                else if(j==highstConnctdHNIndex+1){
                    newWeights.set(i, j, oldWeights.get(i, j-1));
                    newConnections[i][j] = oldConnections[i][j-1];
                }
                else if(i==highstConnctdHNIndex+1){
                    newWeights.set(i, j, oldWeights.get(i-1, j));
                    newConnections[i][j] = oldConnections[i-1][j];
                }

            }
        }
        
    }

}
