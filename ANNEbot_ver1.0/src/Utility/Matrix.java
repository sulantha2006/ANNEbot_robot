/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

/**
 *
 * @author Dilmi
 */
public class Matrix {

    private int numOfRows = 0;
    private int numOfCols = 0;
    private double[][] matrix ;

    //Creates a Matrix instance when the number of rows and columns are passed
    public Matrix(int myNumOfRows, int myNumOfCols){
        this.numOfRows = myNumOfRows;
        this.numOfCols = myNumOfCols;
        matrix = new double[myNumOfRows][myNumOfCols];
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                this.matrix[i][j] = 0;
            }
        }
        
    }

    //Creates a Matrix instance when a 2 dimensionsl array is passed
    public Matrix(double[][] myMatrix){
        this.matrix = myMatrix;
        this.numOfRows = myMatrix.length;
        this.numOfCols = myMatrix[0].length;
    }

    //Creates a Row Matrix from a one dimensional array
    static Matrix createRowMatrix(double[] myRowMatrix){
        Matrix rowMatrix = new Matrix (1,myRowMatrix.length);
        for(int i = 0 ; i < rowMatrix.numOfCols ; i++){
            rowMatrix.matrix[0][i] = myRowMatrix[i];
        }

        return rowMatrix;
    }

    //Creates a Column Matrix from a one dimensional array
    static Matrix createColumnMatrix(double[] myColMatrix){
        Matrix colMatrix = new Matrix (myColMatrix.length,1);
        for(int i = 0 ; i < colMatrix.numOfRows ; i++){
            colMatrix.matrix[i][0] = myColMatrix[i];
        }

        return colMatrix;
    }


    //Gets the value of a specified cell
    double get(int rowNum, int colNum){
        return this.matrix[rowNum][colNum];
    }

    //Sets the value of a specified cell
    void set(int rowNum, int colNum, double value){
        this.matrix[rowNum][colNum] = value;
    }

    //Gets the number of rows
    int getNumOfRows(){
        return this.numOfRows;
    }

    //Gets the number of cols
    int getNumOfCols(){
        return this.numOfCols;
    }

    //Adds a given value to all the elements in the matrix
    void add(double value2add){
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                double temp = this.matrix[i][j];
                this.matrix[i][j] = temp + value2add;
            }
        }
    }

    //Sets all the values of the elements to zero
    void clear(){
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                this.matrix[i][j] = 0;
            }
        }
    }

    //Creates a copy of the given Matrix
    Matrix cloneMatrix(){
        Matrix clone = new Matrix(this.numOfRows , this.numOfCols);
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                clone.matrix[i][j] = this.matrix[i][j];
            }
        }
        return clone;
    }

    //Checks if every element in two given matrices are equal within a given tolerance level
    boolean equal(Matrix matrix2Compare, double tolerance){
        boolean answer = true;
        double difference = 0;
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                if(this.matrix[i][j]-matrix2Compare.matrix[i][j] < 0){
                    difference = (-1) * (this.matrix[i][j]-matrix2Compare.matrix[i][j]);
                }
                else
                    difference = this.matrix[i][j]-matrix2Compare.matrix[i][j];

                if(difference<=tolerance){
                    continue;
                }
                else if (difference>tolerance){
                    answer = false;
                    break;
                }
            }
            if(answer == false){
                break;
            }
            else{
                continue;
            }
        }
        return answer;
    }
    
    //Returns a specified row of the matrix as a Matrix
    Matrix getRow(int rowNum){
        Matrix rowMatrix = new Matrix(1,this.numOfCols);
        for(int i = 0; i < this.numOfCols ; i++){
                rowMatrix.matrix[0][i] = this.matrix[rowNum][i];
        }
        return rowMatrix;
    }

    //Returns a specified column of the matrix as a Matrix
    Matrix getCol(int colNum){
        Matrix colMatrix = new Matrix(this.numOfRows,1);
        for(int i = 0; i < this.numOfRows ; i++){
            colMatrix.matrix[i][0] = this.matrix[i][colNum];
        }
        return colMatrix;
    }
/*
    //Returns a specified row of the matrix as a Matrix
    Matrix getRows(int rowNum , int offset){
        Matrix rowMatrix = new Matrix(numOfRows,this.numOfCols);
        for(int i = 0; i < this.numOfCols ; i++){
            rowMatrix.matrix[1][i] = this.matrix[rowNum][i];
        }
        return rowMatrix;
    }

    //Returns a specified column of the matrix as a Matrix
    Matrix getCols(int colNum, int offset){
        Matrix colMatrix = new Matrix(this.numOfRows,1);
        for(int i = 0; i < this.numOfRows ; i++){
                colMatrix.matrix[i][1] = this.matrix[i][colNum];
            }
        return colMatrix;
    }
*/
    //Checks of the matrix is a vector i.e. a column matrix or a row matrix
    boolean isVector(){
        boolean answer = false;
            if(this.numOfCols == 1 || this.numOfRows == 1){
                answer = true;
            }
        return answer;
    }

    //Checks if the matrix has rows or columns with all zeros
    boolean isZero(){
        boolean answer = false;
        int loopCounter = 0;
        int zeroCounter = 0;
        while(answer == false && loopCounter < 2 ){

            //checks if there are rows with all zeros
            if(loopCounter == 0){
                for(int i = 0; i < this.numOfRows ; i++){
                    zeroCounter = 0;
                    for(int j = 0; j < this.numOfCols ; j++){
                        if (this.matrix[i][j] == 0){
                            zeroCounter++;
                        }
                    }
                    if(zeroCounter == this.numOfCols){
                        answer = true;
                        break;
                    }
                    else
                        continue;
                }
                loopCounter ++;
            }

            //checks if there are columns with all zeros
            else if(loopCounter == 1)
            {
                for(int i = 0; i < this.numOfCols ; i++){
                    zeroCounter = 0;
                    for(int j = 0; j < this.numOfRows ; j++){
                        if (this.matrix[j][i] == 0){
                            zeroCounter++;
                        }
                    }
                    if(zeroCounter == this.numOfRows){
                        answer = true;
                        break;
                    }
                    else
                        continue;
                }
                loopCounter ++;
            }
        }
        return answer;
    }

    //Adds all the values in the matrix and returns the sum
    double sum(){
        double sum = 0;
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                double temp = sum;
                sum = this.matrix[i][j] + temp;
            }
        }
        return sum;
    }

    //Returns all the values in the matrix packed into a one dimensional array
    double[] toPackedArray(){
        double[] packedArray = new double[this.numOfRows+this.numOfCols];
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                packedArray[i+j] = this.matrix[i][j];
            }
        }
        return packedArray;
    }

    //Prints the values in the matrix
    void printMatrix(){
        for(int i = 0; i < this.numOfRows ; i++){
            for(int j = 0; j < this.numOfCols ; j++){
                System.out.print(this.matrix[i][j]+"\t");
            }
            System.out.println("");
        }
    }
}
