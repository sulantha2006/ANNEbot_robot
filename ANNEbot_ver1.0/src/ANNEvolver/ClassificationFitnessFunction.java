/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANNEvolver;

import ANN.ANN;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public class ClassificationFitnessFunction extends FitnessFunction {
    double[][]input;
    double[] output;
    
    public ClassificationFitnessFunction(double inputset[][], double desiredOutput[]){
        this.input = inputset;
        this.output = desiredOutput;
    }
    

    @Override
    protected double evaluate(IChromosome ic) {
        double fitness = 0;
        ANN ann = EvolverUtility.getANNfromChromosome(ic);        
        for(int i = 0 ; i < input.length ; i++){
            output = ann.produceOutput(input[i]);
            fitness = fitness + this.getFitness(output, input[i][4]);
        }
        return fitness/input.length*100;
    }

    

    

     private double getFitness(double output[], double irisType){
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
        return fitness;
    }
    

   
}
