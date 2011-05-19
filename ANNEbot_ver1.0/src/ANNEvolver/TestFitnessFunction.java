/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;
import ANN.ANN;
import Utility.ANNConfiguration;
import Utility.Matrix;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public class TestFitnessFunction extends FitnessFunction{
    @Override
    protected double evaluate(IChromosome ic) {
        double fitness = 0;
        Matrix weightMatrix = EvolverUtility.getWeightsFromChromosome(ic);
        double[][]input = this.getInput("/home/dilmi/Desktop/Iris data set.txt");
        double[] output;
        ANN ann = new ANN(ANNConfiguration.inputNeuronCount, ANNConfiguration.hiddenLNeuronCount, ANNConfiguration.outputNeuronCount);
        ann.setWeights(weightMatrix);
        for(int i = 0 ; i < input.length ; i++){
            output = this.processInput(input[i], ann);
            fitness = fitness + this.getFitness(output, input[i][4]);
        }
        return fitness;
    }

    double[][] getInput(String filePath){
        String [][]stringInput = Utility.DataSetReader.readDataSet(filePath, 5 , "," );
        double [][] doubleInput = new double [stringInput.length][stringInput[0].length];
        for(int i  = 0 ; i < doubleInput.length ; i++){
            for(int j  = 0 ; j < doubleInput[i].length; j++){
                if(j == 3){
                    if(stringInput[i][j].equals("Iris-setosa")){doubleInput[i][j] = 1;}
                    else if(stringInput[i][j].equals("Iris-versicolor")){doubleInput[i][j] = 2;}
                    else if(stringInput[i][j].equals("Iris-virginica")){doubleInput[i][j] = 3;}
                }
                else{
                    doubleInput[i][j] = Double.valueOf(stringInput[i][j]);
                }                    
            }
        }
        return doubleInput;
    }

    public double[] processInput(double []input, ANN ann){
        double output [] = new double [ann.getOutputNeuronCount()] ;


        return output;

    }

    public double getFitness(double output[], double irisType){
        double fitness = 0;

        return fitness;
    }


}
