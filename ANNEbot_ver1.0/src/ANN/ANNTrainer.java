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
    int genCount = 0;

    public ANNTrainer(ANN myAnn) {
        this.ann = myAnn;
    }
    
    public void train(){
        for(;;){
            ANN temp = eMan.evolveANN(this.ann, this.genCount);
            this.setFitness(temp);
            ann = temp;
            this.genCount++;
        }    
    }

    private setFitness(ANN myANN){
        if(this.genCount<10){
            myANN.setFitness(Math.random()%10);
        }
        else if(this.genCount <= 10 && this.genCount < 20)
        {
            myANN.setFitness(Math.random()%20);
        }
        else if(this.genCount <= 20 && this.genCount < 30)
        {
            myANN.setFitness(Math.random()%30);
        }

    }

    

   
    

}
