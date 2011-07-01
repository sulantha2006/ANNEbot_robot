/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import Utility.ANNConfiguration;
import Utility.Stats;
import org.jgap.IChromosome;

/**
 *
 * @author sulantha
 */
public class ConnectionModifier {
    IChromosome bestChromosome = null;
    int count = 0;

    int returnStatus(IChromosome bestWeightChromosome) {
        int status = 1;
        if (bestChromosome == null) {
            bestChromosome = bestWeightChromosome;
            ANNConfiguration.oldConnectionConfig = ANNConfiguration.connectionsConfig;
            status = 1;
        }else{
            if (bestChromosome.getFitnessValue() > bestWeightChromosome.getFitnessValue()) {
                ANNConfiguration.connectionsConfig = ANNConfiguration.oldConnectionConfig;
                Stats.annArray[count] = EvolverUtility.getANNfromChromosome(bestChromosome, bestChromosome.getFitnessValue());
                count++;
                bestChromosome = null;
                status = 0;
            }else{
                bestChromosome = bestWeightChromosome;
                ANNConfiguration.oldConnectionConfig = ANNConfiguration.connectionsConfig;
                status = 1;
            }
        }
        if(status == 1){
            boolean[][] connections = ANNConfiguration.connectionsConfig;
            int totalNeuronCount = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
            int noInputNeuron = ANNConfiguration.inputNeuronCountConfig;
            double min = 1000.0;//Range of a weight is set to -10 to 10 in WeightModifier
            int minPos_i = 0;
            int min_Pos_j = 0;

            int icIndex = 0;
            for (int i = 0; i < totalNeuronCount; i++){
               for (int j = 0; j < totalNeuronCount; j++){
                   if ((connections[i][j])||((i>=noInputNeuron)&&(i==j))){//Check
                       icIndex++;
                       if (i==j) {
                           continue;
                       }else{
                           if (Math.abs(((Double)bestWeightChromosome.getGene(icIndex).getAllele()).doubleValue())< min) {
                               min = ((Double)bestWeightChromosome.getGene(icIndex).getAllele()).doubleValue();
                               minPos_i = i;
                               min_Pos_j = j;
                           }
                       }
                   }
               }
            }
            //ANNConfiguration.oldConnectionConfig = ANNConfiguration.connectionsConfig;
            ANNConfiguration.connectionsConfig[minPos_i][min_Pos_j] = false;
        }
        return status;
    }

}
