/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

/**
 *
 * @author sulantha
 */
public class WeightModifier {
    private int averageCount;
    private int maxEvolutionsAllowed;
    private double upperThreshold;
    private IChromosome bestChromosome = null;;
    private double bestChromosomeScore;

    public WeightModifier(int averageCount, int maxEvolutionsAllowed, double upperThreshold) {
        this.averageCount = averageCount;
        this.maxEvolutionsAllowed = maxEvolutionsAllowed;
        this.upperThreshold = upperThreshold;
    }
    
    public IChromosome getBestWeightChromosome(int chromosomeLength, int populationSize) throws InvalidConfigurationException{
        Configuration conf = new DefaultConfiguration();
        //FitnessFunction testFunc = new IrisFitnessFunction();
        FitnessFunction testFunc = new IrisFitnessFunction2();
        //FitnessFunction testFunc = new MackeyGlassFitnessFunction();
        conf.setFitnessFunction(testFunc);

        Gene[] sampleGenes = new Gene[chromosomeLength];
        for (int i=0; i<chromosomeLength; i++){
            sampleGenes[i] = new DoubleGene(conf, -10.0, 10.0);
        }
        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );
        conf.setSampleChromosome( sampleChromosome );
        conf.setPopulationSize(populationSize);
        
        for (int i = 0; i < averageCount; i++) {
            Genotype population = Genotype.randomInitialGenotype( conf );
            int numOfEvolutionsToThresh = 0;
            for (int j = 0; j < maxEvolutionsAllowed; j++) {
                numOfEvolutionsToThresh = 0;
                population.evolve();
                double fitness = population.getFittestChromosome().getFitnessValue();
                if (fitness >= upperThreshold) {
                    numOfEvolutionsToThresh = j+1;
                }
            }
            if (numOfEvolutionsToThresh == 0) {
                numOfEvolutionsToThresh = maxEvolutionsAllowed;
            }
            double finalFitness = population.getFittestChromosome().getFitnessValue();
            double score = finalFitness/numOfEvolutionsToThresh;
            if (bestChromosome == null) {
                bestChromosome = population.getFittestChromosome();
                bestChromosomeScore = score;
            }else{
                if (bestChromosomeScore < score) {
                    bestChromosome = population.getFittestChromosome();
                    bestChromosomeScore = score;
                }
            }
        }
        return bestChromosome;
    }
}
