/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ANNEvolver;

import ANN.ANN;
import Utility.ANNConfiguration;
import Utility.DataLogger;
import Utility.Stats;
import org.jgap.IChromosome;

/**
 *
 * @author dilmi
 */
public class OptimizedEvolver {
    private String dataFolder = System.getProperty("user.home")+"\\ANNEbot_Devel\\annebot\\Data\\";

    int DEBUG = 0;

    private ANN ann;
    private ANN bestANN;
    private ANN comparingANN = null;
    private int noOfInputNs;
    private int noOfHiddenNs;
    private int noOfOutputNs;
    private int totalNs;
    int chromosomeLength = 0;
    int populationSize = 100;

    
    private int noOfEvolutions;
    double []fitnessValue;
    private double[] validationError;
    int maxHiddenNeurons = 0;
    int numOfModifiableConnections = 0;//=noOfHiddenNs*(noOfOutputNs+noOfInputNs) at the perticular instance.
    private boolean isReachedHundred;
    private boolean finichsedNeuroniteration;

    

    public void initialize(int numInputNeurons , int hNCount, int numOutputNeurons, int numEvolutions){
        //this.ann = new ANN(numInputNeurons,hNCount,numOutputNeurons);
        ANNConfiguration.inputNeuronCountConfig = numInputNeurons;
        ANNConfiguration.hiddenLNeuronCountConfig = hNCount;
        ANNConfiguration.outputNeuronCountConfig = numOutputNeurons;
        noOfInputNs = numInputNeurons;
        noOfHiddenNs = hNCount;
        maxHiddenNeurons = hNCount;
        Stats.annArray = new ANN[hNCount];
        noOfOutputNs = numOutputNeurons;
        this.noOfEvolutions = numEvolutions;
        fitnessValue = new double[numEvolutions];
        validationError = new double[numEvolutions];
        totalNs = noOfInputNs + noOfHiddenNs + noOfOutputNs;
    }



    public void train() throws Exception{
        //Housekeeping Work
        int averageCount = 5;
        int maxEvolutionsAllowed = 100;
        double upperThreshold = 85.0;
        WeightModifier wm;
        ConnectionModifier cm = new ConnectionModifier();
        NeuronModifier nm = new NeuronModifier();
        numOfModifiableConnections = ANNConfiguration.hiddenLNeuronCountConfig*(ANNConfiguration.outputNeuronCountConfig+ANNConfiguration.inputNeuronCountConfig);
//        Configuration conf = new DefaultConfiguration();
//        //FitnessFunction testFunc = new IrisFitnessFunction();
//        FitnessFunction testFunc = new IrisFitnessFunction2();
//        //FitnessFunction testFunc = new MackeyGlassFitnessFunction();
//        conf.setFitnessFunction(testFunc);
        //End Housekeeping
        for (int i = 0; i < maxHiddenNeurons; i++) {
            if (i == 0) {
                ANNConfiguration.hiddenLNeuronCountConfig = i + 1;
                noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
                noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
                noOfOutputNs = ANNConfiguration.outputNeuronCountConfig;
                this.ann = new ANN(noOfInputNs, noOfHiddenNs, noOfOutputNs);
                initConnectionMatrix();
                System.out.println("Number of Hidden Neurons : " + noOfHiddenNs);
            }else{
                ANNConfiguration.hiddenLNeuronCountConfig = i + 1;
                noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
                noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
                noOfOutputNs = ANNConfiguration.outputNeuronCountConfig;
                this.ann = nm.createNewANN(this.bestANN);
                System.out.println("Number of Hidden Neurons : " + noOfHiddenNs);
                
            }
            
            for (int j = 0; j < numOfModifiableConnections; j++) {
                getChromosomeLength();
                wm = new WeightModifier(averageCount, maxEvolutionsAllowed, upperThreshold);
                IChromosome bestWeightChromosome = wm.getBestWeightChromosome(chromosomeLength, populationSize);
                System.out.println("Chromosome Fitness : "+bestWeightChromosome.getFitnessValue());
                int status = cm.returnStatus(bestWeightChromosome);
                if (status == 2) {//status = 2 when the evolver found the minimum 100% fit ann
                    isReachedHundred = true;
                }
                numOfModifiableConnections = ANNConfiguration.hiddenLNeuronCountConfig*(ANNConfiguration.outputNeuronCountConfig+ANNConfiguration.inputNeuronCountConfig);
                if ((status == 0)||(status == 2)) {//status = 2 when the evolver found the minimum 100% fit ann
                    break;
                }
            }
            if (isReachedHundred) {
                break;
            }
            this.bestANN = Stats.bestPerOneNeuronIteration;
            if (this.comparingANN == null) {
                comparingANN = this.bestANN;
            }else{
                if (comparingANN.getFitness()>this.bestANN.getFitness()) {
                    finichsedNeuroniteration = true;
                    break;
                }else{
                    comparingANN = this.bestANN;
                }
            }
        }
        if (isReachedHundred) {
            DataLogger.writeObjectToFile(dataFolder+"bestANN.dat", Stats.getBestOne());
        }else{
            DataLogger.writeObjectToFile(dataFolder+"bestANN.dat", Stats.getBest());
        }


        /////
        
//        Gene[] sampleGenes = new Gene[lengthOfChromosome];
//        for (int i=0; i<lengthOfChromosome; i++){
//            sampleGenes[i] = new DoubleGene(conf, -10.0, 10.0);
//        }
//        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );
//        conf.setSampleChromosome( sampleChromosome );
//
//        conf.setPopulationSize(populationSize);
//
//        Genotype population = Genotype.randomInitialGenotype( conf );
//        IChromosome initialPopSample = population.getFittestChromosome();
//        String [][]stats = EvolverUtility.getANNfromChromosome(initialPopSample).ANNstats();
//        for(int i = 0; i < stats.length ; i++){
//            for(int j = 0 ; j < stats[0].length ; j++){
//                System.out.print(stats[i][j]);
//            }
//            System.out.println("");
//        }
//        System.out.println("Total Chromosome Size : " + initialPopSample.size());
        //###########################################################################################################
//        int increment = 80;
//        //Testing Evolved Data
//        for (int k = 0; k<noOfEvolutions;k++){
//            population.evolve();
//
//
//            if (DEBUG == 0){
//                System.out.println("Population Evolved " + k + " ltimes\n");
//                IChromosome bestSolutionSoFar = population.getFittestChromosome();
//                System.out.println("% Fitness of the best chromosome : " + bestSolutionSoFar.getFitnessValue());
//                fitnessValue[k] = bestSolutionSoFar.getFitnessValue();
//                validationError[k] = new IrisFitnessFunction2().getValidationError(population.getFittestChromosome());
//
//                if(fitnessValue[k]>= increment){
//                    double validationError = new IrisFitnessFunction2().getValidationError(population.getFittestChromosome());
//                    System.out.println("Validation Error at % : " + validationError);
//                    //increment = increment + 10;
//                }
//
//                if(fitnessValue[k]>= 95 && fitnessValue[k-1]<95){
//                    DataLogger.writeToFile("/home/dilmi/Desktop/Variation with number of neurons.txt", k);
//                }
//    //            System.out.println("Chromosome details: " + "Fitness: "+bestSolutionSoFar.getFitnessValue()+" Weights: ");
//    //            for(int i = 0; i <bestSolutionSoFar.getGenes().length;i++){
//    //                System.out.println(bestSolutionSoFar.getGene(i)+" ");
////            }
////                if(validationError[k]==0){
////                    break;
////                }
//            }
//        }
//        DataLogger.writeToFile1D("/home/dilmi/Desktop/Test.txt", validationError);
//        double validationError = new IrisFitnessFunction2().getValidationError(population.getFittestChromosome());
//        System.out.println("Validation Error : " + validationError);
        //########################################################################################################################
        ////////////////////////////////////////////////////////////////////////////
    }

    private void initConnectionMatrix() {
        totalNs = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
        noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
        noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
        for(int i = 0; i < noOfInputNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++){
                this.ann.getConnections()[i][j] = true;
            }
        }
        for(int i = 0; i < noOfHiddenNs; i++){
            for(int j = noOfInputNs; j < totalNs;j++){
                if(noOfInputNs+i == j){
                    continue;
                }else{
                    this.ann.getConnections()[noOfInputNs+i][j] = true;
                }
                    
            }
        }

       ANNConfiguration.connectionsConfig = this.ann.getConnections();
        
    }

    private void getChromosomeLength() {
        totalNs = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
        noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
        noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
        int length = 0;
        for (int i = 0; i < totalNs; i++){
            for (int j = 0; j < totalNs; j++){
                if (this.ann.getConnections()[i][j])
                    length = length +1;
            }

        }
        // Have to add the threasholds to the chromosome
        chromosomeLength = length + noOfHiddenNs + noOfOutputNs;
        System.out.println("Total number of modifiable values in network : "+chromosomeLength);
    }


}
