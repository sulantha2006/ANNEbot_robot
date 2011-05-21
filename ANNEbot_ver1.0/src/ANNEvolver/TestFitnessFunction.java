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
        Matrix weightsNBiasMatrix = EvolverUtility.getWeightsFromChromosome(ic, ANNConfiguration.connectionsConfig);
        //weightsNBiasMatrix.printMatrix();
        double[][]input = this.getInput("/home/dilmi/Desktop/Iris data set.txt");
        Matrix inputM = new Matrix(input);
        //inputM.printMatrix();
        double[] output;
        ANN ann = new ANN(ANNConfiguration.inputNeuronCountConfig, ANNConfiguration.hiddenLNeuronCountConfig, ANNConfiguration.outputNeuronCountConfig);
        Matrix weights = this.removeThresholds(weightsNBiasMatrix, ann);
        ann.setWeights(weights);
        for(int i = 0 ; i < input.length ; i++){
            output = this.produceOutput(input[i], ann);
            fitness = fitness + this.getFitness(output, input[i][4]);
        }
        //System.out.println("fitness of a chromosome" + fitness);
        return fitness;
    }

    double[][] getInput(String filePath){
        String [][]stringInput = Utility.DataSetReader.readDataSet(filePath, 5 , "," );
        double [][] doubleInput = new double [stringInput.length][stringInput[0].length];
        for(int i  = 0 ; i < doubleInput.length ; i++){
            for(int j  = 0 ; j < doubleInput[i].length; j++){
                if(j == 4){
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

    public Matrix removeThresholds(Matrix weightsNBias, ANN ann){
        for(int i = 0 ; i < ann.getTotalNeuronCount() ; i++){
            ann.getNeurons()[i].setThreshold(weightsNBias.get(i, i));
            weightsNBias.set(i, i, 0);
        }
        return weightsNBias;
    }

    public double[] produceOutput(double []input, ANN ann){
        double output [] = new double [ann.getOutputNeuronCount()] ;

        //for Input Neurons
        for(int i = 0 ; i < ann.getInputNeuronCount() ; i++){
            ann.getNeurons()[i].setValue(input[i]);
        }
        //for Hidden Neurons
        double value = 0;
        for(int i = ann.getInputNeuronCount() ; i < (ann.getTotalNeuronCount()-ann.getOutputNeuronCount()); i++){
            for(int j = 0 ; j< ann.getTotalNeuronCount() ; j++){
                value = value + (ann.getNeurons()[j].getValue()*ann.getWeights().get(j, i));
            }
            if(value > ann.getNeurons()[i].getThreshold())ann.getNeurons()[i].setValue(value);
        }

        //for Output Neurons
        value = 0;
        for(int i = ann.getTotalNeuronCount()-ann.getOutputNeuronCount() ; i < ann.getTotalNeuronCount(); i++){
            for(int j = 0 ; j< ann.getTotalNeuronCount(); j++){
                value = value + (ann.getNeurons()[j].getValue()*ann.getWeights().get(j, i));
            }
            if(value > ann.getNeurons()[i].getThreshold())ann.getNeurons()[i].setValue(value);
            output[i-(ann.getTotalNeuronCount()-ann.getOutputNeuronCount())] = value;
        }
        return output;

    }

    public double getFitness(double output[], double irisType){
        double fitness = 0;
        double max = 0;
        for(int i = 0 ; i < output.length-1;i++){
            if(output[i]!=output[i+1] && output[i]> output[i+1]){
                max = i+1;
            }
            else if(output[i]!=output[i+1] && output[i]< output[i+1]){
                max = i+1+1;
            }
        }
        if(max==irisType){fitness=1;}
        //System.out.println("Fitness of one input " + fitness);
        return fitness;
    }


}
