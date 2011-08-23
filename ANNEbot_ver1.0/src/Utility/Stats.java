/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

import ANN.ANN;

/**
 *
 * @author sulantha
 */
public class Stats {
    public static ANN[] annArray;
    static ANN best = null;
    public static ANN bestPerOneNeuronIteration = null;
    

    public static ANN getBest() {
        int nonNullCount = 0;
        for (int i = 0; i < annArray.length; i++) {
            if (annArray[i] != null) {
                nonNullCount++;
            }
        }
        ANN[] tempArray = new ANN[nonNullCount];
        for (int i = 0; i < nonNullCount; i++) {
            tempArray[i] = annArray[i];
        }
        annArray = tempArray;
        
        for (int i = 0; i < annArray.length; i++) {
            System.out.println("ARRAY - \n"+annArray[i].getHiddenLNeuronCount()+"  fit - "+annArray[i].getFitness());
            if (i == 0) {
                best = annArray[i];
            }else{
                if (best.getFitness()<annArray[i].getFitness()) {
                    best = annArray[i];
                }
            }
        }
        System.out.println("Best - \n"+best.getHiddenLNeuronCount()+"  fit - "+best.getFitness());
        return best;
    }

    public static ANN getBestOne() {
        best  = annArray[0];
        System.out.println("Best - \n"+best.getHiddenLNeuronCount()+"  fit - "+best.getFitness());
        return best;
    }

}
