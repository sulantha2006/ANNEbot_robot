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

    private String dataFolder = System.getProperty("user.home") + "\\ANNEbot_Devel\\annebot\\Data\\";
    int DEBUG = 0;
    private ANN ann;
    private ANN bestANN;
    private ANN comparingANN = null;
    private int noOfInputNs;
    private int noOfHiddenNs;
    private int noOfOutputNs;
    private int totalNs;
    private int chromosomeLength = 0;
    private int populationSize = 2;
    private int maxHiddenNeurons = 0;
    private int numOfModifiableConnections = 0;//=noOfHiddenNs*(noOfOutputNs+noOfInputNs) at the perticular instance.
    private boolean isReachedHundred;
    private boolean finichsedNeuroniteration;
    private int WM_averageCount = 5;
    private int WM_maxEvolutionsAllowed = 100;
    private double WM_upperThreshold = 85.0;

    public OptimizedEvolver(int noOfInputNs, int noOfHiddenNs, int noOfOutputNs, int populationSize, int WM_averageCount, int WM_maxEvolutionsAllowed, double WM_upperThreshold, String dataFolder) {
        this.initialize(noOfInputNs, noOfHiddenNs, noOfOutputNs, populationSize, WM_averageCount, WM_maxEvolutionsAllowed, WM_upperThreshold, dataFolder);
    }

    public OptimizedEvolver(int noOfInputNs, int noOfHiddenNs, int noOfOutputNs, int populationSize, int WM_averageCount, int WM_maxEvolutionsAllowed, double WM_upperThreshold) {
        this.initialize(noOfInputNs, noOfHiddenNs, noOfOutputNs, populationSize, WM_averageCount, WM_maxEvolutionsAllowed, WM_upperThreshold, dataFolder);
    }
    
    public OptimizedEvolver(int noOfInputNs, int noOfHiddenNs, int noOfOutputNs, int populationSize) {
        this.initialize(noOfInputNs, noOfHiddenNs, noOfOutputNs, populationSize, WM_averageCount, WM_maxEvolutionsAllowed, WM_upperThreshold, dataFolder);
    }
    private void initialize(int numInputNeurons, int hNCount, int numOutputNeurons, int populationSize, int WM_averageCount, int WM_maxEvolutionsAllowed, double WM_upperThreshold, String dataFolder) {
        ANNConfiguration.inputNeuronCountConfig = numInputNeurons;
        ANNConfiguration.hiddenLNeuronCountConfig = hNCount;
        ANNConfiguration.outputNeuronCountConfig = numOutputNeurons;
        this.noOfInputNs = numInputNeurons;
        this.noOfHiddenNs = hNCount;
        this.maxHiddenNeurons = hNCount;
        this.noOfOutputNs = numOutputNeurons;
        this.totalNs = noOfInputNs + noOfHiddenNs + noOfOutputNs;
        this.populationSize = populationSize;
        this.WM_averageCount = WM_averageCount;
        this.WM_maxEvolutionsAllowed = WM_maxEvolutionsAllowed;
        this.WM_upperThreshold = WM_upperThreshold;
        this.dataFolder = dataFolder;
        Stats.annArray = new ANN[hNCount];

    }

    public void train() throws Exception {

        WeightModifier wm;
        ConnectionModifier cm = new ConnectionModifier();
        NeuronModifier nm = new NeuronModifier();
        numOfModifiableConnections = ANNConfiguration.hiddenLNeuronCountConfig * (ANNConfiguration.outputNeuronCountConfig + ANNConfiguration.inputNeuronCountConfig);

        for (int i = 0; i < maxHiddenNeurons; i++) {
            if (i == 0) {
                ANNConfiguration.hiddenLNeuronCountConfig = i + 1;
                noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
                noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
                noOfOutputNs = ANNConfiguration.outputNeuronCountConfig;
                this.ann = new ANN(noOfInputNs, noOfHiddenNs, noOfOutputNs);
                initConnectionMatrix();
                System.out.println("Number of Hidden Neurons : " + noOfHiddenNs);
            } else {
                ANNConfiguration.hiddenLNeuronCountConfig = i + 1;
                noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
                noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
                noOfOutputNs = ANNConfiguration.outputNeuronCountConfig;
                this.ann = nm.createNewANN(this.bestANN);
                System.out.println("Number of Hidden Neurons : " + noOfHiddenNs);

            }

            for (int j = 0; j < numOfModifiableConnections; j++) {
                getChromosomeLength();
                wm = new WeightModifier(WM_averageCount, WM_maxEvolutionsAllowed, WM_upperThreshold);
                IChromosome bestWeightChromosome = wm.getBestWeightChromosome(chromosomeLength, populationSize);
                System.out.println("Chromosome Fitness : " + bestWeightChromosome.getFitnessValue());
                int status = cm.returnStatus(bestWeightChromosome);
                if (status == 2) {//status = 2 when the evolver found the minimum 100% fit ann
                    isReachedHundred = true;
                }
                numOfModifiableConnections = ANNConfiguration.hiddenLNeuronCountConfig * (ANNConfiguration.outputNeuronCountConfig + ANNConfiguration.inputNeuronCountConfig);
                if ((status == 0) || (status == 2)) {//status = 2 when the evolver found the minimum 100% fit ann
                    break;
                }
            }
            if (isReachedHundred) {
                break;
            }
            this.bestANN = Stats.bestPerOneNeuronIteration;
            if (this.comparingANN == null) {
                comparingANN = this.bestANN;
            } else {
                if ((comparingANN.getFitness() > this.bestANN.getFitness())&&this.bestANN.getFitness()>80.0) {
                    finichsedNeuroniteration = true;
                    break;
                } else {
                    comparingANN = this.bestANN;
                }
            }
        }
        if (isReachedHundred) {
            DataLogger.writeObjectToFile(dataFolder + "bestANN.dat", Stats.getBestOne());
        } else {
            DataLogger.writeObjectToFile(dataFolder + "bestANN.dat", Stats.getBest());
        }



    }

    private void initConnectionMatrix() {
        totalNs = ANNConfiguration.inputNeuronCountConfig + ANNConfiguration.hiddenLNeuronCountConfig + ANNConfiguration.outputNeuronCountConfig;
        noOfInputNs = ANNConfiguration.inputNeuronCountConfig;
        noOfHiddenNs = ANNConfiguration.hiddenLNeuronCountConfig;
        for (int i = 0; i < noOfInputNs; i++) {
            for (int j = noOfInputNs; j < totalNs; j++) {
                this.ann.getConnections()[i][j] = true;
            }
        }
        for (int i = 0; i < noOfHiddenNs; i++) {
            for (int j = noOfInputNs; j < totalNs; j++) {
                if (noOfInputNs + i == j) {
                    continue;
                } else {
                    this.ann.getConnections()[noOfInputNs + i][j] = true;
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
        for (int i = 0; i < totalNs; i++) {
            for (int j = 0; j < totalNs; j++) {
                if (this.ann.getConnections()[i][j]) {
                    length = length + 1;
                }
            }

        }
        chromosomeLength = length + noOfHiddenNs + noOfOutputNs;
        System.out.println("Total number of modifiable values in network : " + chromosomeLength);
    }
}
