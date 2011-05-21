/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.ANNConfiguration;
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

    Matrix weights;
    TestFitnessFunction test = new TestFitnessFunction();

    public void initialize(int numInputNeurons , int hNCount, int numOutputNeurons){
        this.ann = new ANN(numInputNeurons,hNCount,numOutputNeurons);
        ANNConfiguration.inputNeuronCountConfig = numInputNeurons;
        ANNConfiguration.hiddenLNeuronCountConfig = hNCount;
        ANNConfiguration.outputNeuronCountConfig = numOutputNeurons;
        noOfInputNs = numInputNeurons;
        noOfHiddenNs = hNCount;
        noOfOutputNs = numOutputNeurons;
        totalNs = noOfInputNs + noOfHiddenNs + noOfOutputNs;
    }



    public void train() throws Exception{

        //Initilization of the connection Matrix
        initConnectionMatrix();
        //Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
        //connections.printMatrix();
        //Get Chromosome Length
        getChromosomeLength();
        //JGAP
        Configuration conf = new DefaultConfiguration();
        FitnessFunction testFunc = new TestFitnessFunction();
        conf.setFitnessFunction(testFunc);

        int lengthOfChromosome = chromosomeLength;

        Gene[] sampleGenes = new Gene[lengthOfChromosome];
        for (int i=0; i<lengthOfChromosome; i++){
            sampleGenes[i] = new DoubleGene(conf, -10.0, 10.0);
        }

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );
        conf.setSampleChromosome( sampleChromosome );

        conf.setPopulationSize(100);

        Genotype population = Genotype.randomInitialGenotype( conf );
        population.evolve();

        //Testing Evolved Data

        if (DEBUG == 0){
            System.out.println("Population Evolved 1 times\n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);

            wem.printMatrix();
        }
        for (int k = 0; k<99;k++){
            population.evolve();
        }

        if (DEBUG == 0){
            System.out.println("Population Evolved 100 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }

        ////////////////////////////////////////////////////////////////////////////
        for (int k = 0; k<100;k++){
            population.evolve();
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 200 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }
        for (int k = 0; k<100;k++){
            population.evolve();
            
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 300 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }
        for (int k = 0; k<100;k++){
            population.evolve();
            

        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 400 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }for (int k = 0; k<100;k++){
            population.evolve();
            
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 500 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }for (int k = 0; k<100;k++){
            population.evolve();
            
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 600 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }for (int k = 0; k<100;k++){
            population.evolve();
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 700 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }for (int k = 0; k<100;k++){
            population.evolve();
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 800 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
        }for (int k = 0; k<100;k++){
            population.evolve();
        }
        if (DEBUG == 0){
            System.out.println("Population Evolved 900 times \n");
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            System.out.println(bestSolutionSoFar.getFitnessValue());
//            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//                System.out.println(bestSolutionSoFar.getGene(i)+" ");
//            }
            Matrix wem = EvolverUtility.getWeightsFromChromosome(bestSolutionSoFar, ANNConfiguration.connectionsConfig);
//            Matrix connections = new Matrix(BinaryUtil.boolean2binary(ann.getConnections()));
//            connections.printMatrix();
            wem.printMatrix();
            weights = EvolverUtility.getWeightsFromChromosome(population.getFittestChromosome(), ANNConfiguration.connectionsConfig);
            test.verificationTable(weights);
        }
        
        ////////////////////////////////////////////////////////////////////////////
    }

    private void initConnectionMatrix() {
        for(int i = 0; i < noOfInputNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++){
                this.ann.getConnections()[i][j] = true;
            }
        }
        for(int i = 0; i < noOfHiddenNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++){
                if(noOfInputNs+i <= j)
                    this.ann.getConnections()[noOfInputNs+i][j] = true;
            }
        }

       ANNConfiguration.connectionsConfig = this.ann.getConnections();
        
    }

    private void getChromosomeLength() {
        int length = 0;
        for (int i = 0; i < totalNs; i++){
            for (int j = 0; j < totalNs; j++){
                if (this.ann.getConnections()[i][j])
                    length = length +1;
            }

        }

        // Have to add the threasholds to the chromosome
        chromosomeLength = length + noOfHiddenNs + noOfOutputNs;
    }

    

}
