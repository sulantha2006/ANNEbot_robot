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
public class BreastCancerFitnessFunction extends FitnessFunction{
    private String dataFolder = System.getProperty("user.home")+"\\ANNEbot_Devel\\annebot\\Data\\";

    @Override
    protected double evaluate(IChromosome ic) {
        double fitness = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);
        double[][]input = this.getInput(dataFolder+"BreastCancerTraining.txt");
        double[] output;
        for(int i = 0 ; i < input.length ; i++){
            output = ann.produceOutput(input[i]);
            fitness = fitness + this.getFitness(output, input[i][0]);
        }
        return fitness/input.length*100;
    }

    double[][] getInput(String filePath){
        String [][]stringInput = Utility.DataSetReader.readDataSet(filePath, 31 , "," );
        double [][] doubleInput = new double [stringInput.length][stringInput[0].length];
        for(int i  = 0 ; i < doubleInput.length ; i++){
            for(int j  = 0 ; j < doubleInput[i].length; j++){
                doubleInput[i][j] = Double.valueOf(stringInput[i][j]);                   
            }
        }
        
        return doubleInput;
    }

    

    public double getFitness(double output[], double cancerType){
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
        if((maxIndex+1)==cancerType){fitness=1;}
        
        return fitness;
    }
    
//    public static void main(String[] args) {
//        BreastCancerFitnessFunction bcff = new BreastCancerFitnessFunction();
//        double[][]input = bcff.getInput(bcff.dataFolder+"BreastCancer.txt");
//        new Matrix(input).printMatrix();
//        for (int i = 0; i < input.length; i++) {
//            System.out.println("Cancer type" + input[i][0]);
//            
//        }
//    }
   
    
}
