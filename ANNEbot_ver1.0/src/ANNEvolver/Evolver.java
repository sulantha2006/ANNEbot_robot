/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.BinaryUtil;
import Utility.Matrix;
import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.Gene;
import org.jgap.impl.DoubleGene;
import org.jgap.Genotype;
import org.jgap.IChromosome;

/**
 *
 * @author dilmi
 */
public class Evolver {

    int DEBUG = 0;

    private ANN ann;
    private int noOfInputNs;
    private int noOfHiddenNs;
    private int noOfOutputNs;
    private int totalNs;
    int chromosomeLength = 0;

    public void initialize(int numInputNeurons , int hNCount, int numOutputNeurons){
        this.ann = new ANN(numInputNeurons,hNCount,numOutputNeurons);
        noOfInputNs = numInputNeurons;
        noOfHiddenNs = hNCount;
        noOfOutputNs = numOutputNeurons;
        totalNs = noOfInputNs + noOfHiddenNs + noOfOutputNs;
    }



    public void train() throws Exception{

        //Initilization of the connection Matrix
        initConnectionMatrix();
        Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
        connections.printMatrix();
        //Get Chromosome Length
        getChromosomeLength();

        //JGAP
        Configuration conf = new DefaultConfiguration();
        FitnessFunction testFunc = new TestFitnessFunction();
        conf.setFitnessFunction(testFunc);

        int lengthOfChromosome = this.ann.getWeights().toPackedArray().length;

        Gene[] sampleGenes = new Gene[lengthOfChromosome];
        for (int i=0; i<lengthOfChromosome; i++){
            sampleGenes[i] = new DoubleGene(conf);
        }

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );
        conf.setSampleChromosome( sampleChromosome );

        conf.setPopulationSize(10);

        Genotype population = Genotype.randomInitialGenotype( conf );
        population.evolve();

        //Testing Evolved Data

        if (DEBUG == 0){
            System.out.println("Population Evolved 1 times\n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
                System.out.println(bestSolutionSoFar.getGene(i)+" ");
            }
        }
        for (int k = 0; k<99;k++){
            population.evolve();
        }

        if (DEBUG == 0){
            System.out.println("Population Evolved 100 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
                System.out.println(bestSolutionSoFar.getGene(i)+" ");
            }
        }
    }

    private void initConnectionMatrix() {
        for(int i = 0; i < noOfInputNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++)
            this.ann.getConnections()[i][j] = true;
        }
        for(int i = 0; i < noOfHiddenNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++)
            this.ann.getConnections()[noOfInputNs+i][j+i] = true;
        }
        
    }

    private void getChromosomeLength() {
        for (int i = 0; i < totalNs; i++){

        }
    }

    

}
