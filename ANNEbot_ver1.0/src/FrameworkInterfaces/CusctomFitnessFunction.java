/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkInterfaces;

import ANN.ANN;
import ANNEvolver.EvolverUtility;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Dilmi
 */
public abstract class CusctomFitnessFunction extends FitnessFunction {
    
    public ANN ann;

    @Override
    protected double evaluate(IChromosome ic) {
        ann = EvolverUtility.getANNfromChromosome(ic);
        return this.getFitness(this.ann);
        
    }
    
    public abstract double getFitness(ANN ann);
}
