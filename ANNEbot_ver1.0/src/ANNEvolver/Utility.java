/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import Utility.Matrix;
import org.jgap.IChromosome;

/**
 *
 * @author sulantha
 */
public class Utility {
    public static Matrix getWeightsFromChromosome(IChromosome ic){
        Matrix weightMatrix = new Matrix(1, ic.size());
        for (int l = 0; l < ic.size(); l++){
            weightMatrix.set(0, l, ic.getGene(l).getEnergy());
        }
        return weightMatrix;
    }
}
