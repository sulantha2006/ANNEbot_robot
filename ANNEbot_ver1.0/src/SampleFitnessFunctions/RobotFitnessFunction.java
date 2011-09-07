/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SampleFitnessFunctions;

import ANN.ANN;
import ANNEvolver.EvolverUtility;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public class RobotFitnessFunction extends FitnessFunction{

    private String dataFolder = System.getProperty("user.home")+"\\ANNEbot_Devel\\annebot\\Data\\";
    @Override
    protected double evaluate(IChromosome ic) {
        double fitness = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);
        double[][]input = this.getInput(dataFolder+"SimbadDatasetTraining2.txt");
        double[] output;
        double[] desiredOutput = new double[2];
        for(int i = 0 ; i < input.length ; i++){
            output = ann.produceCorrectOutput(input[i],"Sigmoid","Sigmoid");
            double temp = output[1];
            output[1] = temp;
            desiredOutput[0] = input[i][5];
            desiredOutput[1] = input[i][6];
            fitness = fitness + this.getFitness(output, desiredOutput);
        }
        //return fitness/input.length*100;
        return (1-(fitness/input.length))*100;
    }

    double[][] getInput(String filePath){
        String [][]stringInput = Utility.DataSetReader.readDataSet(filePath, 7 , "," );
        double [][] doubleInput = new double [stringInput.length][stringInput[0].length];
        for(int i  = 0 ; i < doubleInput.length ; i++){
            for(int j  = 0 ; j < doubleInput[i].length; j++){
                doubleInput[i][j] = Double.valueOf(stringInput[i][j]);                                    
            }
        }
        return doubleInput;
    }

    

    
    public double getFitness(double output[], double desiredOutput[]){
        double fitness = 0;
        
        double transitionalError = Math.abs(desiredOutput[0]-output[0]);
        
        double rotationalError = Math.abs(desiredOutput[1]-output[1]);
        
        double msefitness = (Math.pow(transitionalError, 2)+Math.pow(rotationalError, 2))/2;
        //if(transitionalError>1) transitionalError = 1;
        //if(rotationalError > 1) rotationalError = 1;
        //fitness = ((1-transitionalError)+(1-rotationalError))/2;
        fitness = msefitness;
        return fitness;
    }
    
    
    
}
