import java.util.Random;
import java.util.Scanner;

public class Matrix {
    int [][] matrix;
    int rowsNum;
    int columnsNum;
    Matrix(int rowsNum, int columnsNum){
        this.rowsNum = rowsNum;
        this.columnsNum = columnsNum;
        matrix = new int[rowsNum][columnsNum];
    }

    void SetMatrixByUser()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the element under the index you specified and press <Enter>:");
        for(int row = 0; row < rowsNum; row++)
        {
            for(int col = 0; col < columnsNum; col++)
            {
                System.out.println("a[" + row + "][" + col + "]");
                matrix[row][col] = scan.nextInt();
            }
        }
        scan.close();
    }

    void SetMatrixByRand()
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
    }

    void printMatrix()
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
