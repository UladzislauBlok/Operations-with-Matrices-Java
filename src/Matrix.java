import java.util.Random;
import java.util.Scanner;

public class Matrix {

    private int [][] matrix;
    private int rowsNum;
    private int columnsNum;
    Scanner scan = new Scanner(System.in);
    Matrix(int rowsNum, int columnsNum){
        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
        matrix = new int[rowsNum][columnsNum];
    }

    int getElem(int row, int col)
    {
        return matrix[row][col];
    }

    int getColumnsNum()
    {
        return columnsNum;
    }

    int getRowsNum()
    {
        return  rowsNum;
    }

    void SetMatrixByUser() //Function for setting matrix values through the user
    {
        System.out.println("Enter the element under the index you specified and press <Enter>:");
        for(int row = 0; row < rowsNum; row++)
        {
            for(int col = 0; col < columnsNum; col++)
            {
                System.out.println("a[" + row + "][" + col + "]");
                matrix[row][col] = scan.nextInt();
            }
        }
        printMatrix();
    }

    void SetMatrixByRand() // Function for setting matrix values with random numbers from -number of rows to +number of columns
    {
        System.out.println("This method fills the function with values from -number of rows to +number of columns");
        for(int row = 0; row < rowsNum; row++)
        {
            for(int col = 0; col < columnsNum; col++)
            {
                Random random = new Random();
                matrix[row][col] = random.nextInt(columnsNum+1 + rowsNum) - rowsNum;
                System.out.println("a[" + row + "][" + col + "] -- init");
            }
        }
        printMatrix();
    }

    void setElem(int row, int col, int value)
    {
        matrix[row][col] = value;
    }

    void printMatrix() // Function for outputting matrix values
    {
        for(int row = 0; row < rowsNum; row++)
        {
            for(int col = 0; col < columnsNum; col++)
            {
                System.out.print(matrix[row][col] + "\t");
            }
            System.out.println('\n');
        }
    }
}
