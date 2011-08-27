/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SampleFitnessFunctions;

import ANN.ANN;
import ANNEvolver.EvolverUtility;
import org.jgap.IChromosome;

/**
 *
 * @author Sulantha
 */
public class BreastCancerValidator {

    private String dataFolder = System.getProperty("user.home") + "\\ANNEbot_Devel\\annebot\\Data\\";

    public double getValidationScore(IChromosome ic) {
        double score = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);
        double[][] input = this.getInput(dataFolder + "BreastCancerValidation.txt");
        double[] output;
        for (int i = 0; i < input.length; i++) {
            output = ann.produceOutput(input[i]);
            score = score + this.getFitness(output, input[i][0]);
        }
        return score / input.length * 100;
    }

    public double getFitness(double output[], double irisType) {
        double fitness = 0;
        double max = output[0];
        int maxIndex = 0;

        for (int i = 1; i < output.length; i++) {
            int comparor = Double.compare(max, output[i]);

            if (comparor > 0) {
            } else if (comparor < 0) {
                max = output[i];
                maxIndex = i;
            }


        }
        if ((maxIndex + 1) == irisType) {
            fitness = 1;
        }
        //System.out.println("Fitness of one input " + fitness);
        return fitness;
    }

    double[][] getInput(String filePath) {
        String[][] stringInput = Utility.DataSetReader.readDataSet(filePath, 31, ",");
        double[][] doubleInput = new double[stringInput.length][stringInput[0].length];
        for (int i = 0; i < doubleInput.length; i++) {
            for (int j = 0; j < doubleInput[i].length; j++) {
                doubleInput[i][j] = Double.valueOf(stringInput[i][j]);
//                if (j == 4) {
//                    if (stringInput[i][j].equals("Iris-setosa")) {
//                        doubleInput[i][j] = 1;
//                    } else if (stringInput[i][j].equals("Iris-versicolor")) {
//                        doubleInput[i][j] = 2;
//                    } else if (stringInput[i][j].equals("Iris-virginica")) {
//                        doubleInput[i][j] = 3;
//                    }
//                } else {
//                    doubleInput[i][j] = Double.valueOf(stringInput[i][j]);
//                }
            }
        }
        return doubleInput;
    }
}
