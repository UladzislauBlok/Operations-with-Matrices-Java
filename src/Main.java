import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = scan.nextInt();
        int col = scan.nextInt();
        Matrix mat = new Matrix(row, col);
        MatrixOperation matOp = new MatrixOperation();
        mat.SetMatrixByUser();
        matOp.printDeterminant(mat);
    }
}