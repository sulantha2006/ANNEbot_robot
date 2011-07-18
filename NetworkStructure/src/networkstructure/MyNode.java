/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networkstructure;

/**
 *
 * @author sulantha
 */
public class MyNode {
    private int id;
    private double bias;

    public MyNode(int id) {
        this.id = id;
    }

    public MyNode(int id, double bias) {
        this.id = id;
        this.bias = bias;
    }
    
    public String toString(){
        if (bias == 0.0) {
            return "N"+id;
        }else{
            return "N"+id+" B- "+bias;
        }
    }

    public int getId() {
        return id;
    }

}
