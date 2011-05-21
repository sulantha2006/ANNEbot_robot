/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import Utility.*;
import org.jgap.IChromosome;

/**
 *
 * @author sulantha
 */
public class EvolverUtility {
    public static Matrix getWeightsFromChromosome(IChromosome ic, boolean [][] connections){
        int totalNeuronCount = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
        Matrix weightMatrix = new Matrix(totalNeuronCount, totalNeuronCount);
        int icIndex = 0;
        for (int i = 0; i < totalNeuronCount; i++){
           for (int j = 0; j < totalNeuronCount; j++){
               if (connections[i][j]){
                   System.out.println(" getWeights method \n");
                   System.out.println(ic.getGene(icIndex));
                   //weightMatrix.set(i, j, ic.getGene(icIndex).getEnergy());
               }
           }

        }
        return weightMatrix;
    }


}
