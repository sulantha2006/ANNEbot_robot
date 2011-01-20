/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

/**
 *
 * @author Dilmi
 */
public class MatrixMath {

    //Matrix add operation
    static Matrix add(Matrix matA, Matrix matB) throws Exception{
        if(matA.getNumOfRows() == matB.getNumOfRows() && matA.getNumOfCols() == matB.getNumOfCols()){
            Matrix result = new Matrix(matA.getNumOfRows(),matA.getNumOfCols());
            double sum = 0;
            for(int i = 0; i < result.getNumOfRows() ; i++){
                for(int j = 0; j < result.getNumOfCols() ; j++){
                    sum = matA.get(i, j)  +matB.get(i, j);
                    result.set(i, j, sum);
                }
            }
            return result;
        }
        else{
            throw new Exception("MatrixSizeMismatch");
        }
    }

    //Matrix subtract operation
    static Matrix subtract(Matrix matA, Matrix matB) throws Exception{
        if(matA.getNumOfRows() == matB.getNumOfRows() && matA.getNumOfCols() == matB.getNumOfCols()){
            Matrix result = new Matrix(matA.getNumOfRows(),matA.getNumOfCols());
            double difference = 0;
            for(int i = 0; i < result.getNumOfRows() ; i++){
                for(int j = 0; j < result.getNumOfCols() ; j++){
                    difference = matA.get(i, j) - matB.get(i, j);
                    result.set(i, j, difference);
                }
            }
            return result;
        }
        else{
            throw new Exception("MatrixSizeMismatch");
        }
    }

    //Scalar Multiplication
    static Matrix scalarMultiply(Matrix matrix , double scalar){
        Matrix result = new Matrix(matrix.getNumOfRows() , matrix.getNumOfCols());
        for(int i = 0; i < result.getNumOfRows() ; i++){
            for(int j = 0; j < result.getNumOfCols() ; j++){
                double temp = matrix.get(i, j);
                result.set(i, j, (temp * scalar)) ;
            }
        }
        return result;
    }

    //Matrix multiplication operation
    static Matrix multiply(Matrix matA, Matrix matB) throws Exception{
        if(matA.getNumOfCols() == matB.getNumOfRows()){
            Matrix result = new Matrix(matA.getNumOfRows(),matB.getNumOfCols());
            double sumOfMultiples = 0 , multiple = 0 , temp = 0;
            for(int i = 0 ; i < result.getNumOfRows() ; i++){
                for(int j = 0 ; j < result.getNumOfCols() ; j++){
                    sumOfMultiples = 0;
                   for(int k = 0 ; k < matA.getNumOfCols() ; k++){
                       multiple = matA.get(i, k)*matB.get(k, j);
                       temp = sumOfMultiples;
                       sumOfMultiples = multiple + temp;
                   }
                   result.set(i, j, sumOfMultiples);
                }
            }
            return result;
        }
        else{
            throw new Exception("MatrixSizeMismatch");
        }
    }

    //Dot Product of two Vector Matrices
    static double dotProduct(Matrix matA, Matrix matB) throws Exception{
        double dotProduct = 0 , temp = 0 , multiple = 0;
        if(matA.isVector()&&matB.isVector()&&matA.getNumOfRows() == matB.getNumOfRows() && matA.getNumOfCols() == matB.getNumOfCols()){
            for(int i = 0; i < matA.getNumOfRows() ; i++){
                for(int j = 0; j < matA.getNumOfCols() ; j++){
                    multiple = matA.get(i, j)*matB.get(i, j);
                    temp = dotProduct;
                    dotProduct = multiple + temp;
                }
            }
            return dotProduct;
        }
        else if(!matA.isVector())
            throw new Exception("FirstMatrixIsNotAVector");
        else if(!matB.isVector())
            throw new Exception("SecondMatrixIsNotAVector");
        else if(!(matA.getNumOfRows() == matB.getNumOfRows() && matA.getNumOfCols() == matB.getNumOfCols()))
            throw new Exception("MatrixSizeMismatch");
        else
            throw new Exception("UndefinedExcepton");
    }

    //Gives transpose of a given matrix
    static Matrix transpose(Matrix matA){
        Matrix transpose = new Matrix(matA.getNumOfCols() , matA.getNumOfRows());
            for(int i = 0; i < transpose.getNumOfRows() ; i++){
                for(int j = 0; j < transpose.getNumOfCols() ; j++){
                    transpose.set(i, j, matA.get(j, i));
                }
            }
        return transpose;
    }

    //Returns an identity matrix of the specified size
    static Matrix identity(int numOfRows){
        Matrix identity = new Matrix(numOfRows , numOfRows);
        for(int i = 0; i < identity.getNumOfRows() ; i++){
                for(int j = 0; j < identity.getNumOfCols() ; j++){
                    if(i==j)
                        identity.set(i, j, 1);
                    else
                        identity.set(i, j, 0);
                }
            }    
        return identity;
    }

    //Returns the vector length
    static double vectorLength(Matrix vector) throws Exception{
        if(vector.isVector()){
            double vectorLength = 0 , temp = 0 , sumOfSquares = 0;
            double[]vectorArray = vector.toPackedArray();
            for(int i = 0; i < vectorArray.length ; i++){
            temp = sumOfSquares;
            sumOfSquares = temp + (vectorArray[i]*vectorArray[i]);
            }
            vectorLength = Math.sqrt(sumOfSquares);
            return vectorLength;
        }
        else
            throw new Exception("NotAVector");
    }
}
