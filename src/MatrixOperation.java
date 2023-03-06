import java.util.Scanner;
import java.lang.Math;

public class MatrixOperation {
    Scanner scan = new Scanner(System.in);
    void cyclicMatrixShift(Matrix sourceMatrix) //Function for cyclic moving of columns by n elements to the right
    {
        System.out.println("Please enter the number k by which to shift the matrix and press <Enter>:");
        int k = scan.nextInt();
        int columnsNum = sourceMatrix.getColumnsNum();
        int rowsNum = sourceMatrix.getRowsNum();
        Matrix tempMatrix = new Matrix(rowsNum, columnsNum); // Create a new matrix of the same size, in which we will write the elements in the new order
        for(int col = 0; col < columnsNum; col++) {
            int newPosCol = col + k; // Find the numbers of the columns in which we will write the elements after the shift
            if (newPosCol >= columnsNum) {
                newPosCol = newPosCol % columnsNum;
            }
            for(int row = 0; row < rowsNum; row++){
                int value = sourceMatrix.getElem(row, col);
                tempMatrix.setElem(row, newPosCol, value);
            }
        }
        tempMatrix.printMatrix();
    }

    int determinant(Matrix sourceMatrix)
    {
        int columnsNum = sourceMatrix.getColumnsNum();
        int rowsNum = sourceMatrix.getRowsNum();
        if (columnsNum == 2) // Calculate the determinant for a 2x2 matrix
        {
            return sourceMatrix.getElem(0,0) * sourceMatrix.getElem(1,1) - sourceMatrix.getElem(0,1) * sourceMatrix.getElem(1,0);
        } else if (columnsNum == 1) { // Calculate the determinant for a 1x1 matrix
            return sourceMatrix.getElem(0,0);
        } else {
            Matrix tempMatrix = new Matrix(rowsNum-1, columnsNum-1); // For a 3x3 or larger matrix, create a new smaller matrix
            int result = 0;
            int matRow, matCol;
            for (int rowFirstLine = 0; rowFirstLine < rowsNum; rowFirstLine++) // Calculate the determinant by the first row
            {
                matRow = 0;
                for (int row = 1; row < rowsNum; row++) // Count the determinants of smaller matrices, starting from the second row
                {
                    matCol = 0;
                    for(int col = 0; col < columnsNum; col++)
                        if (col != rowFirstLine)  // If the element number of the row does not match the number of the element of the first row (which I use to calculate the determinant)
                        {
                            int value = sourceMatrix.getElem(row, col);
                            tempMatrix.setElem(matRow, matCol, value); // then I write this element in a new table
                            matCol++;
                        }
                    matRow++;
                }
                result += Math.pow(-1, rowFirstLine + 2) * sourceMatrix.getElem(0, rowFirstLine) * determinant(tempMatrix); // Recursively calculate the determinant
            }
            return result;
        }
    }

    void printDeterminant(Matrix sourceMatrix)
    {
        if(sourceMatrix.getColumnsNum() == sourceMatrix.getRowsNum()) {
            System.out.println("Determinant of the matrix = " + determinant(sourceMatrix));
        }else {
            System.out.println("The determinant can only be calculated for a square matrix");
        }
    }
}
