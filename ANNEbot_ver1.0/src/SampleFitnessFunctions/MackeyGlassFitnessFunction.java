/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SampleFitnessFunctions;
import ANN.ANN;
import ANNEvolver.EvolverUtility;
import Utility.ANNConfiguration;
import Utility.Matrix;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public class MackeyGlassFitnessFunction extends FitnessFunction{
    @Override
    protected double evaluate(IChromosome ic) {
        double fitness = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);
        double[][]input = this.getInput("/home/dilmi/Desktop/Mackey Glass Problem.txt");
        double[] output;
        for(int i = 0 ; i < input.length ; i++){
            output = ann.produceOutput(input[i]);
            fitness = fitness + this.getFitness(output, input[i][10]);
        }
        return fitness/input.length*100;
    }

    double[][] getInput(String filePath){
        String [][]stringInput = Utility.DataSetReader.readDataSet(filePath, 11 , "," );
        double [][] doubleInput = new double [stringInput.length][stringInput[0].length];
        for(int i  = 0 ; i < doubleInput.length ; i++){
            for(int j  = 0 ; j < doubleInput[i].length; j++){
                doubleInput[i][j] = Double.valueOf(stringInput[i][j]);

            }
        }
        return doubleInput;
    }



    public double getFitness(double output[], double irisType){
        double fitness = 0;
        double max = output[0];
        int maxIndex = 0;

        for(int i = 1 ; i < output.length;i++){
                int comparor = Double.compare(max,output[i]);

                if(comparor>0){

                }
                else if(comparor<0){
                    max = output[i];
                    maxIndex = i;
                }


        }
        if((maxIndex+1)==irisType){fitness=1;}
        //System.out.println("Fitness of one input " + fitness);
        return fitness/214*100;
    }


    public void verificationTable(Matrix weightsNBias){
        double[][]input = this.getInput("/home/dilmi/Desktop/Iris data set.txt");
        double[] output;
        ANN ann = new ANN(ANNConfiguration.inputNeuronCountConfig, ANNConfiguration.hiddenLNeuronCountConfig, ANNConfiguration.outputNeuronCountConfig);
        Matrix weights = EvolverUtility.removeThresholds(weightsNBias, ann);
        ann.setWeights(weights);
        for(int i = 0 ; i < input.length ; i++){
            output = ann.produceOutput(input[i]);
            Matrix.createRowMatrix(output).printMatrix();
            System.out.println("Input belongs to Class: " + input[i][10]);
            System.out.println("Fitness : "+this.getFitness(output, input[i][10]));
        }

    }



}
