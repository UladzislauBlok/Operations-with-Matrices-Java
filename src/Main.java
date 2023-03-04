import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = scan.nextInt();
        int col = scan.nextInt();
        Matrix mat2 = new Matrix(row, col);
        mat2.SetMatrixByRand();
        mat2.printMatrix();
        scan.close();
    }
}