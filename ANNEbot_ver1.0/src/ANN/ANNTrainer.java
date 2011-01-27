/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANN;

import EvolutionaryAlgo.EvolutionManager;

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
            ann = temp;

            this.genCount++;
        }    
    }

    

   
    

}
