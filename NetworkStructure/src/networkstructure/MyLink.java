/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package networkstructure;

/**
 *
 * @author sulantha
 */
public class MyLink {
    private double weight;
    private double capacity;
    private int id;

    public MyLink(double weight, int id) {
        this.weight = weight;
        this.id = id;
    }

    public MyLink(double weight, double capacity, int id) {
        this.weight = weight;
        this.capacity = capacity;
        this.id = id;
    }

    public String toString(){
        return String.format("%.2g%n", weight);
    }



}
