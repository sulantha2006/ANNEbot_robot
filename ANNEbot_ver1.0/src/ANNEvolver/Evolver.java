/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;

/**
 *
 * @author dilmi
 */
public class Evolver {
    private ANN ann;

    public void initialize(int numInputNeurons , int hNCount, int numOutputNeurons){
        this.ann = new ANN(numInputNeurons,hNCount,numOutputNeurons);
    }

    public ANN getAnn() {
        return ann;
    }

    

}
