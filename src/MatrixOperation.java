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

    Matrix minusMat(Matrix sourceMatrix) // function to take the minus out of the matrix
    {
        Matrix mat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum());
        for (int row = 0; row < sourceMatrix.getRowsNum(); row++)
        {
            for (int col = 0; col < sourceMatrix.getColumnsNum(); col++) {
                mat.setElem(row, col, -sourceMatrix.getElem(row, col));
            }
        }
        return mat;
    }

    void printMinusMat(Matrix sourceMatrix)
    {
        minusMat(sourceMatrix).printMatrix();
    }

    Matrix multiplicationByNum(Matrix sourceMatrix) // function for multiplying a matrix by a number
    {
        Matrix mat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum());

        System.out.println("Enter the number by which you want to multiply the matrix: ");
        int num;
        num = scan.nextInt();

        for (int row = 0 ; row < sourceMatrix.getRowsNum(); row++)
        {
        for (int col = 0 ; col < sourceMatrix.getColumnsNum(); col++) {
            mat.setElem(row, col, sourceMatrix.getElem(row, col) * num);
        }}
        return mat;
    }

    void printMultiplicationByNum(Matrix sourceMatrix)
    {
        multiplicationByNum(sourceMatrix).printMatrix();
    }

    Matrix transpose(Matrix sourceMatrix)
    {
        Matrix mat = new Matrix(sourceMatrix.getColumnsNum(), sourceMatrix.getRowsNum()); // Swap row and col to create a transpose matrix

        for (int row = 0; row < sourceMatrix.getRowsNum(); row++)
        {
        for (int col = 0; col < sourceMatrix.getColumnsNum(); col++) {
            mat.setElem(col, row, sourceMatrix.getElem(row, col)); // Assign elements from rows to columns
        }}
        return mat;
    }

    void printTranspose(Matrix sourceMatrix)
    {
        transpose(sourceMatrix).printMatrix();
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

    Matrix minor(Matrix sourceMatrix) // Function for calculating a matrix of minors
    {
        Matrix minorMat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum()); // Create a new matrix (minority matrix)

        for (int rowMinorMat = 0; rowMinorMat < sourceMatrix.getRowsNum(); rowMinorMat++)
        {
            for (int colMinorMat = 0; colMinorMat < sourceMatrix.getColumnsNum(); colMinorMat++) // Go through it and set each element
            {
                Matrix smalMat = new Matrix(sourceMatrix.getRowsNum() - 1, sourceMatrix.getColumnsNum() - 1); // To calculate the determinant, create a smaller matrix
                int rowSmalMat, colSmalMat;
                rowSmalMat = 0;
                for (int rowSourceMat = 0; rowSourceMat < sourceMatrix.getRowsNum(); rowSourceMat++) // Iterate over the source matrix
                {
                    colSmalMat = 0;
                    for (int colSourceMat = 0; colSourceMat < sourceMatrix.getColumnsNum(); colSourceMat++)
                    {
                        if (rowSourceMat != rowMinorMat && colSourceMat != colMinorMat) // If the row and column number of this element does not match the row and column number of the element we are counting, we write this element in a matrix(mat)
                        {
                            smalMat.setElem(rowSmalMat, colSmalMat, sourceMatrix.getElem(rowSourceMat, colSourceMat));
                            colSmalMat++;
                            if (colSmalMat == smalMat.getColumnsNum()) // If you have filled in a line, move to a new line
                                rowSmalMat++;
                        }
                    }
                }
                minorMat.setElem(rowMinorMat, colMinorMat, determinant(smalMat)); // Write the determinant of the smaller matrix into the matrix of minors
            }
        }
        return minorMat;
    }

    void printMatrixMinor(Matrix sourceMatrix)
    {
        if(sourceMatrix.getColumnsNum() == sourceMatrix.getRowsNum()) {
            minor(sourceMatrix).printMatrix();
        }else {
            System.out.println("The determinant can only be calculated for a square matrix");
        }
    }

    Matrix algebraComplementMat(Matrix sourceMatrix) // Function for finding an algebraic addition matrix
    {
        Matrix mat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum());
        for (int row = 0; row < sourceMatrix.getRowsNum(); row++) {
            for (int col = 0; col < sourceMatrix.getColumnsNum(); col++) {
            mat.setElem(row, col, sourceMatrix.getElem(row, col) * (int)Math.pow(-1, row + col));
        }}

        return mat;
    }

    void printInversMat(Matrix sourceMatrix) // Function for calculating the inverse matrix
    {
        if (sourceMatrix.getColumnsNum() == sourceMatrix.getRowsNum())
        {
            int det = determinant(sourceMatrix);

            Matrix minorMat = minor(sourceMatrix);
            Matrix algebraComplementMat = (algebraComplementMat(minorMat));		// Find the transpose algebraic addition matrix

            Matrix mat = transpose(algebraComplementMat);

            if (det == 0) {
                System.out.println("The determinant is zero. There is no inverse matrix");
            } else {
                System.out.print("1/" + det + " * M\n\n M = \n");
                mat.printMatrix();
            }
        }
        else
            System.out.println("This operation is only possible with a square matrix");
    }

    Matrix adding(Matrix sourceMatrix) // Function for adding matrices
    {
        Matrix secondMat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum());
        secondMat.setMatrixByUser();

        for (int row = 0; row < secondMat.getRowsNum(); row++)
        {
        for (int col = 0; col < secondMat.getColumnsNum(); col++) {
            secondMat.setElem(row, col, (sourceMatrix.getElem(row, col) + secondMat.getElem(row, col))); // Overwrite the matrix "mat" elements with the sums
        }}
        return secondMat;
    }

    void printAdding(Matrix sourceMatrix)
    {
        System.out.println( "This operation is only possible with matrices of the same size\n" +
                            "Enter the elements of the second matrix");

        adding(sourceMatrix).printMatrix();
    }

    Matrix subtracting(Matrix sourceMatrix) // Function for subtracting matrices
    {
        Matrix secondMat = new Matrix(sourceMatrix.getRowsNum(), sourceMatrix.getColumnsNum());

        for (int row = 0; row < secondMat.getRowsNum(); row++){
        for (int col = 0; col < secondMat.getColumnsNum(); col++) {
            secondMat.setElem(row, col, (sourceMatrix.getElem(row, col) - secondMat.getElem(row, col))); // Overwrite the matrix "mat" elements with subtract
        }}
        return secondMat;
    }

    void printSubtracting(Matrix sourceMatrix)
    {
        System.out.println( "This operation is only possible with matrices of the same size\n" +
                            "Enter the elements of the second matrix");

        subtracting(sourceMatrix).printMatrix();
    }

    Matrix multiplicationByMat(Matrix sourceMatrix, int rowSecondMatrix, int colSecondMatrix) // function for multiplying a matrix by a matrix
    {
        Matrix secondMat = new Matrix(rowSecondMatrix, colSecondMatrix);
        secondMat.setMatrixByUser();

        Matrix result = new Matrix(sourceMatrix.getRowsNum(), secondMat.getColumnsNum()); // Create a matrix with the number of rows from the first matrix and the number of columns from the second matrix

        for (int row = 0; row < result.getRowsNum(); row++){
        for (int col = 0; col < result.getColumnsNum(); col++)
        {
            int temp = 0; //the value of each element will be read into "temp" and then transferred to the matrix
            for (int count = 0; count < sourceMatrix.getColumnsNum(); count++) // it makes no difference whether to take the number of columns of the first matrix or the number of rows of the second, because they are equal
            {
                temp += sourceMatrix.getElem(row, count) * secondMat.getElem(count, col);
            }
            result.setElem(row, col, temp);
        }}
        return result;
    }

    void printMultiplicationByMat(Matrix sourceMatrix)
    {
        System.out.println("The operation is only possible if the number of columns of the first matrix equals the number of rows of the second matrix");
        int rowSecondMat, colSecondMat;
        System.out.println("Enter the dimensions of the second matrix(ROWxCOL)");
        rowSecondMat = scan.nextInt();
        colSecondMat = scan.nextInt();
        if (sourceMatrix.getColumnsNum() != rowSecondMat)
        {
            System.out.println( "The number of columns of the first matrix does not equal the number of rows of the second matrix.\n" +
                                "The operation is not possible");
            return;
        }
        else
        {
            System.out.println("Enter the elements of the second matrix");
            multiplicationByMat(sourceMatrix, rowSecondMat, colSecondMat).printMatrix();
        }
    }
}
