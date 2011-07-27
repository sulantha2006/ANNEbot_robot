/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.ANNConfiguration;
import Utility.BinaryUtil;
import Utility.Matrix;

/**
 *
 * @author dilmi
 */
public class NeuronModifier {
    ANN newANN ;
    ANN oldANN;

    //Creates a new ANN by adding an additional hidden neuron which is obtained by cloning the most highly connected hidden neuron in the old ANN
    public ANN createNewANN(ANN pOldANN){
        this.oldANN = pOldANN;
        int inputNeuronCount = oldANN.getInputNeuronCount();
        int hiddenNeuronCount = oldANN.getHiddenLNeuronCount() + 1;
        int outputNeuronCount = oldANN.getOutputNeuronCount();
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);        
        this.newANN.initConnectionMatrix();
        int highstConnctdHNIndex = this.getHighestConnectedHN();
        this.setNewConnections(highstConnctdHNIndex);
        System.out.println("Weight Matrices (old then new)");
        pOldANN.getWeights().printMatrix();
        this.newANN.getWeights().printMatrix();
        System.out.println("Connection Matrices (old then new)");
        new Matrix(BinaryUtil.boolean2binary(pOldANN.getConnections())).printMatrix();
        new Matrix(BinaryUtil.boolean2binary(this.newANN.getConnections())).printMatrix();
        ANNConfiguration.connectionsConfig = this.newANN.getConnections();        
        return this.newANN;
    }

    //Returns the index of the most highly connected hidden neuron in the old ANN
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
            if (avgWeightofHiddenNeurons[max]<sum) {
                max = i;
            }
        }
        return max+oldANN.getInputNeuronCount();
    }

    //Sets the connection matrix and the weight matrix for the new ANN
    private void setNewConnections(int highstConnctdHNIndex) {
        Matrix oldWeights = this.oldANN.getWeights();
        Matrix newWeights = this.newANN.getWeights();
        boolean[][] oldConnections = this.oldANN.getConnections();
        boolean[][] newConnections = this.newANN.getConnections();

        for (int i = 0; i < newWeights.getNumOfRows(); i++) {
            for (int j = 0; j < newWeights.getNumOfCols(); j++) {
                if(i <= highstConnctdHNIndex && j <= highstConnctdHNIndex){
                    newWeights.set(i, j, oldWeights.get(i, j));
                    newConnections[i][j] = oldConnections[i][j];
                }
                else if(i > highstConnctdHNIndex + 1 && j > highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j-1));
                    newConnections[i][j] = oldConnections[i-1][j-1];
                }
                else if(j > highstConnctdHNIndex + 1 && i < highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i, j-1));
                    newConnections[i][j] = oldConnections[i][j-1];
                }
                else if(i > highstConnctdHNIndex + 1 && j < highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j));
                    newConnections[i][j] = oldConnections[i-1][j];
                }

                ///////////////////////////////////////////////////////////////////////
                /***The values in the matrix corresponding to the new hidden neuron***/
                /***(the connection weights of this neuron are set to the default  ***/
                /***so that it connects to all relevant neurons in the network)    ***/
                ////////////////////////////////////////////////////////////////////////
                else if(j== highstConnctdHNIndex + 1 && i< highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i, j-1));
                    newConnections[i][j] = oldConnections[i][j-1];
                }
                else if(j== highstConnctdHNIndex + 1 && i> highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j-1));
                    newConnections[i][j] = oldConnections[i-1][j-1];
                }
                else if(i == highstConnctdHNIndex + 1 && j< highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j));
                    newConnections[i][j] = oldConnections[i-1][j];
                }
                else if(i == highstConnctdHNIndex + 1 && j> highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j-1));
                    newConnections[i][j] = oldConnections[i-1][j-1];
                }
                else if(j== highstConnctdHNIndex + 1 && i== highstConnctdHNIndex + 1){
                    newWeights.set(i, j, 1);
                     newConnections[i][j] = false;
                }
                ////////////////////////////////////////////////////////////////////



            }
        }
        this.newANN.setWeights(newWeights);
        this.newANN.setConnections(newConnections);

    }

//    public void createNewANN(ANN pOldANN){
//        this.oldANN = pOldANN;
//        int inputNeuronCount = oldANN.getInputNeuronCount();
//        int hiddenNeuronCount = oldANN.getHiddenLNeuronCount() + 1;
//        int outputNeuronCount = oldANN.getOutputNeuronCount();
//        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
//        int highstConnctdHNIndex = this.getHighestConnectedHN();
//        this.setNewConnections(highstConnctdHNIndex);
//    }
//
//    private int getHighestConnectedHN(){
//        double avgWeightofHiddenNeurons[] = new double [this.oldANN.getHiddenLNeuronCount()];
//        Matrix weights = this.oldANN.getWeights();
//        double sum = 0;
//        int max = 0;
//        for (int i = 0; i < avgWeightofHiddenNeurons.length; i++) {
//            sum = 0;
//            for (int j = 0; j < weights.getNumOfCols(); j++) {
//                sum+=weights.get(i+this.oldANN.getInputNeuronCount(), j);
//            }
//            avgWeightofHiddenNeurons[i] = sum;
//            //keeps track of the max
//            if (avgWeightofHiddenNeurons[max]<sum) {
//                max = i;
//            }
//        }
//        return max+oldANN.getInputNeuronCount();
//    }
//
//    private void setNewConnections(int highstConnctdHNIndex) {
//        Matrix oldWeights = this.oldANN.getWeights();
//        Matrix newWeights = this.newANN.getWeights();
//        boolean[][] oldConnections = this.oldANN.getConnections();
//        boolean[][] newConnections = this.newANN.getConnections();
//        for (int i = 0; i < oldWeights.getNumOfRows()+1; i++) {
//            for (int j = 0; j <= oldWeights.getNumOfCols()+1; j++) {
//                if(i < highstConnctdHNIndex && j < highstConnctdHNIndex){
//                    newWeights.set(i, j, oldWeights.get(i, j));
//                    newConnections[i][j] = oldConnections[i][j];
//                }
//                else if(i > highstConnctdHNIndex + 1 && j > highstConnctdHNIndex + 1){
//                    newWeights.set(i, j, oldWeights.get(i-1, j-1));
//                    newConnections[i][j] = oldConnections[i-1][j-1];
//                }
//                else if(j== highstConnctdHNIndex + 1 && i== highstConnctdHNIndex + 1){
//                    newWeights.set(i, j, 1);
//                    newConnections[i][j] = false;
//                }
//                else if(j== highstConnctdHNIndex + 1 && i!= highstConnctdHNIndex + 1){
//                    newWeights.set(i, j, oldWeights.get(i, j-1));
//                    newConnections[i][j] = oldConnections[i][j-1];
//                }
//                else if(i == highstConnctdHNIndex + 1 && j!= highstConnctdHNIndex + 1){
//                    newWeights.set(i, j, oldWeights.get(i-1, j));
//                    newConnections[i][j] = oldConnections[i-1][j];
//                }
//                else if(j > highstConnctdHNIndex + 1){
//
//                }
//                else if(i > highstConnctdHNIndex + 1){
//
//                }
//
//            }
//        }
//
//    }

}
