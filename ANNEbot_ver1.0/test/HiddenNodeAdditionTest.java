
import ANN.ANN;
import Utility.BinaryUtil;
import Utility.Matrix;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dilmi
 */
public class HiddenNodeAdditionTest {
    ANN newANN ;
    ANN oldANN;
    public static void main(String args[]){
        HiddenNodeAdditionTest test = new HiddenNodeAdditionTest();
        ANN old = new ANN(4, 4, 3);
        old.initConnectionMatrix();
        for (int i = 0; i < old.getWeights().getNumOfRows(); i++) {
            for (int j = 0; j < old.getWeights().getNumOfCols(); j++) {
                old.getWeights().set(i, j, Math.random());
            }
        }
        System.out.println("OLD ANN weight matrix : " );
        old.getWeights().printMatrix();
        new Matrix(BinaryUtil.boolean2binary(old.getConnections())).printMatrix();
        test.createNewANN(old);
        System.out.println("NEW ANN weight matrix : " );
        test.newANN.getWeights().printMatrix();
        new Matrix(BinaryUtil.boolean2binary(test.newANN.getConnections())).printMatrix();

    }

     public void createNewANN(ANN pOldANN){
        this.oldANN = pOldANN;
        int inputNeuronCount = oldANN.getInputNeuronCount();
        int hiddenNeuronCount = oldANN.getHiddenLNeuronCount() + 1;
        int outputNeuronCount = oldANN.getOutputNeuronCount();
        this.newANN = new ANN(inputNeuronCount,hiddenNeuronCount,outputNeuronCount);
        this.newANN.getWeights().printMatrix();
        this.newANN.initConnectionMatrix();
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
                }
                else if(j== highstConnctdHNIndex + 1 && i> highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j-1));                    
                }
                else if(i == highstConnctdHNIndex + 1 && j< highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j));                    
                }
                else if(i == highstConnctdHNIndex + 1 && j> highstConnctdHNIndex + 1){
                    newWeights.set(i, j, oldWeights.get(i-1, j-1));                    
                }
                else if(j== highstConnctdHNIndex + 1 && i== highstConnctdHNIndex + 1){
                    newWeights.set(i, j, 1);
                }
                ////////////////////////////////////////////////////////////////////

                
                
            }            
        }
        this.newANN.setWeights(newWeights);
        this.newANN.setConnections(newConnections);

    }

}
