/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import EvolutionaryAlgo.EvolutionManager;
import java.util.Random;

/**
 *
 * @author dilmi
 */
public class ANNTrainer {
    ANN ann;
    EvolutionManager eMan = new EvolutionManager();
    int popCount = 0;
    int genCount = 0;

    public ANNTrainer(int numInputNeurons , int hLCount, int numOutputNeurons) {
        this.ann = new ANN(numInputNeurons , hLCount, numOutputNeurons);
    }
    
    public void train(){
        for(;;){
            ANN temp = eMan.evolveANN(this.ann, this.popCount);
            this.genCount = eMan.getGenCount();
            this.setFitness(temp);
            ann = temp;
            this.popCount++;
        }    
    }

    private void setFitness(ANN myANN){
        if(this.genCount<10)
            myANN.setFitness(Math.random()%10);
        
        else if(this.genCount <= 10 && this.genCount < 20)
            myANN.setFitness(10+Math.random()%10);
        
        else if(this.genCount <= 20 && this.genCount < 30)
            myANN.setFitness(20+Math.random()%10);

        else
            myANN.setFitness(30+Math.random()%10);
    }
 }

    

   
    

