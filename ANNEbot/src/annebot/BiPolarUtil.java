/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annebot;

/**
 *
 * @author Dilmi
 */
public class BiPolarUtil {
    double[][] boolean2bipolar(boolean[][] booleanInput){
        double[][] bipolarOutput = new double[booleanInput.length][booleanInput[0].length];
        for(int i = 0 ; i < booleanInput.length ; i++){
            for(int j = 0 ; j < booleanInput[0].length ; j++){
                if(booleanInput[i][j])
                    bipolarOutput[i][j] = 1;
                else
                    bipolarOutput[i][j] = -1;
            }
        }
        return bipolarOutput;
    }

    double[] boolean2bipolar(boolean[] booleanInput){
        double[] bipolarOutput = new double[booleanInput.length];
        for(int i = 0 ; i < booleanInput.length ; i++){
            if(booleanInput[i])
                    bipolarOutput[i] = 1;
                else
                    bipolarOutput[i] = -1;
        }
        return bipolarOutput;
    }

    int double2bipolar(double doubleInput){
        int y = (int)doubleInput;
        int bipolarOutput;
        bipolarOutput = (y+1)/2 ;
        return bipolarOutput;
    }
}
