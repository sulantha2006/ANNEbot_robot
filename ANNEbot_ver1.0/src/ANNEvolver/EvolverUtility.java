/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.*;
import org.jgap.IChromosome;

/**
 *
 * @author sulantha
 */
public class EvolverUtility {

    public static ANN getANNfromChromosome(IChromosome ic){
        Matrix weightsNBiasMatrix = EvolverUtility.getWeightsFromChromosome(ic, ANNConfiguration.connectionsConfig);
        ANN ann = new ANN(ANNConfiguration.inputNeuronCountConfig, ANNConfiguration.hiddenLNeuronCountConfig, ANNConfiguration.outputNeuronCountConfig);
        Matrix weights = EvolverUtility.removeThresholds(weightsNBiasMatrix, ann);
        ann.setWeights(weights);
        return ann;
    }
    public static Matrix getWeightsFromChromosome(IChromosome ic, boolean [][] connections){
        int totalNeuronCount = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
        int noInputNeuron = ANNConfiguration.inputNeuronCountConfig;
        Matrix weightMatrix = new Matrix(totalNeuronCount, totalNeuronCount);
        //System.out.println("Weight Matrix " + weightMatrix.getNumOfRows()+"*"+weightMatrix.getNumOfCols());
        int icIndex = 0;
        for (int i = 0; i < totalNeuronCount; i++){
           for (int j = 0; j < totalNeuronCount; j++){
               if ((connections[i][j])||((i>=noInputNeuron)&&(i==j))){//Check
                   //System.out.println("Weight Matrix position " + i+"*"+j);
                   //System.out.println("IC index " + icIndex );
                   weightMatrix.set(i, j, ((Double)ic.getGene(icIndex).getAllele()).doubleValue());
                   icIndex++;
               }
           }
        }
        return weightMatrix;
    }

    public static Matrix removeThresholds(Matrix weightsNBias, ANN ann){
        for(int i = 0 ; i < ann.getTotalNeuronCount() ; i++){
            ann.getNeurons()[i].setThreshold(weightsNBias.get(i, i));
            weightsNBias.set(i, i, 0);
        }
        return weightsNBias;
    }



}
