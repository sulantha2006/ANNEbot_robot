package SampleFitnessFunctions;

import ANN.ANN;
import ANNEvolver.EvolverUtility;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public class RobotFitnessFunction2 extends FitnessFunction {
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
        
        //Transitional
        double max = output[0];
        int maxIndex = 8;        
        for(int i = 0 ; i < 3 ; i++){
            int comparor = Double.compare(max,output[i]);
            if(comparor<0){
                max = output[i];
                maxIndex = i;
            }
            

        }
                   
        if(desiredOutput[0]==0.5 && maxIndex==0)fitness+=1;
        else if(desiredOutput[0]==0.4 && maxIndex==1)fitness+=1;
        else if(desiredOutput[0]==0.1 && maxIndex==2)fitness+=1;         
        
        //Rotational
        maxIndex = 3;
        max = output[3];
        for(int i = 3 ; i < 7 ; i++){
            int comparor = Double.compare(max,output[i]);
            if(comparor<0){
                max = output[i];
                maxIndex = i;
            }
        }
        if(desiredOutput[1]==0.6 && maxIndex==3)fitness+=1;
        else if(desiredOutput[1]==-0.6 && maxIndex==4)fitness+=1;
        else if(desiredOutput[1]==0.9 && maxIndex==5)fitness+=1;
        else if(desiredOutput[1]==-0.9 && maxIndex==6)fitness+=1;            
        
        return fitness/2;
    }
  
    
}
