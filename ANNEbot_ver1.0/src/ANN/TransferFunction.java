/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

/**
 *
 * @author Dilmi
 */
public class TransferFunction {
    
    public double stepFunction(double input){
       double answer = 0;
       if (input > 0)
           answer = 1;
       return answer;
    }
    
    public double sigmoidFunction(double input, double constant){
       double answer = 0;
       answer = 1/(1+ Math.exp(input*constant));
       return answer;
    }
    
    public double linear(double input){
        return input;
    }
//    public double sigmoid(){
//       double answer = 0;
//       return answer;
//    }
//    public double sigmoid(){
//       double answer = 0;
//       return answer;
//    }
//    public double sigmoid(){
//       double answer = 0;
//       return answer;
//    }
//    public double sigmoid(){
//       double answer = 0;
//       return answer;
//    }
    
}
