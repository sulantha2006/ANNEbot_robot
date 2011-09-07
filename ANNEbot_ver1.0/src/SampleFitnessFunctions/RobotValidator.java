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
 * @author Dilmi
 */
public class RobotValidator {
    private String dataFolder = System.getProperty("user.home")+"\\ANNEbot_Devel\\annebot\\Data\\";
    public double getValidationScore(IChromosome ic) {
        double fitness = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);
        double[][]input = this.getInput(dataFolder+"SimbadDatasetValidation2.txt");
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
//    public double getFitness(double output[], double desiredOutput[]){
//        double fitness = 0;
//        System.out.println("Transitional (Output and Desired Output)" + output [0] + " " + desiredOutput[0]);
//        System.out.println("Rotational (Output and Desired Output)" + output [1] + " " + desiredOutput[1]);
//        double transitionalError = Math.abs(desiredOutput[0]-output[0]);
//        System.out.println("Transitional Error" + transitionalError);
//        if(transitionalError>1) transitionalError = 1;
//        double rotationalError = Math.abs(desiredOutput[1]-output[1]);
//        System.out.println("Rotational Error" + rotationalError);
//        if(rotationalError > 1) rotationalError = 1;
//        fitness = ((1-transitionalError)+(1-rotationalError))/2;
//        return fitness;
//    }
//    public double getFitness(double output[], double desiredOutput[]){
//        double fitness = 0;
//        double transitionalError = Math.abs(desiredOutput[0]-output[0]);
//        if(transitionalError>1) transitionalError = 1;
//        double rotationalError = Math.abs(desiredOutput[1]-output[1]);
//        if(rotationalError > 1) rotationalError = 1;
//        fitness = ((1-transitionalError)+(1-rotationalError))/2;
//        return fitness;
//    }
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
