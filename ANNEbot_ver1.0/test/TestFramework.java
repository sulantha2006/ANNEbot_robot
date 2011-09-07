
import ANNEvolver.OptimizedEvolver;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dilmi
 */
public class TestFramework {
    
    public static void main(String[] args) throws Exception {
        OptimizedEvolver optEvolver = new OptimizedEvolver(4, 3, 3, 20, 5, 20, 85.0);
        optEvolver.train();
    }
    
    
}
