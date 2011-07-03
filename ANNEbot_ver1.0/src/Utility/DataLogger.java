/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

import ANN.ANN;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author dilmi
 */
public class DataLogger {

    public static void writeToFile1D(String link, double[]data){
        try{
        // Create file
            FileWriter fstream = new FileWriter(link);
            PrintWriter out = new PrintWriter(fstream);
            for(int i = 0 ; i < data.length ; i++){
                out.println(Double.toString(data[i]));

            }
        //Close the output stream
        out.close();
        }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeToFile2D(String link, double[][]data){
        try{
        // Create file
            FileWriter fstream = new FileWriter(link);
            PrintWriter out = new PrintWriter(fstream);
            for(int i = 0 ; i < data.length ; i++){
                for(int j = 0 ; j< data[0].length ; j++){
                    out.println(Double.toString(data[i][j]));
                }
            }
        //Close the output stream
        out.close();
        }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
    }
    
    public static void writeToFile(String link, double data){
        try{
        // Create file
            FileWriter fstream = new FileWriter(link);
            PrintWriter out = new PrintWriter(fstream);
            out.println(data);
            
            //Close the output stream
        out.close();
        }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeObjectToFile(String link, Object object){
        try {
            FileOutputStream fout = new FileOutputStream(link);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
            oos.close();
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ANN readANNObjectFromFile(String link){
        ANN ann = null;
        try{
            FileInputStream fin = new FileInputStream(link);
            ObjectInputStream ois = new ObjectInputStream(fin);
            ann = (ANN) ois.readObject();
            ois.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ann;

    }



}
