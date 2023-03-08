import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        MatrixOperation matOp = new MatrixOperation();
        System.out.println("\t\t\tHello");
        OUT: while (true)
        {
            System.out.println("\tEnter the size of the matrix and its elements(ROWxCOL):");
            int row, col;
            row = scan.nextInt();
            col = scan.nextInt();
            Matrix mat = new Matrix(row, col);

            int typeOfFillMatrix;
            System.out.println("Enter the number and press:");
            System.out.println( "1) Enter the matrix elements manually \n"+
                                "2) Fill in the matrix with random elements");

            while (true)
            {
                typeOfFillMatrix = scan.nextInt();
                if (typeOfFillMatrix > 0 && typeOfFillMatrix < 3) {
                    break;
                }
                else {
                    System.out.println("Incorrect symbol. Repeat the entry:");
                }
            }

            if (typeOfFillMatrix == 1)
            {
                mat.setMatrixByUser();
            }else {
                mat.setMatrixByRand();
            }

            while (true)
            {
                System.out.println("\tWhat operation do you want to perform?\n" +
                        "\tSingle matrix operations:\n" +
                        "1) Taking the minus out of the matrix\n" +
                        "2) Multiplying a matrix by a number\n" +
                        "3) Transpose the matrix\n" +
                        "4) Calculate the determinant of the matrix\n" +
                        "5) Cyclic shift of the matrix by k elements\n" +
                        "6) Finding the matrix of minor\n" +
                        "7) Finding the inverse matrix\n" +
                        "\tOperations with two matrices:\n" +
                        "8) Adding matrices\n" +
                        "9) Subtracting matrices\n" +
                        "10) Matrix multiplication\n" +
                        "Enter the operation number: ");

                int operationSymbol;

                while (true)
                {
                    operationSymbol = scan.nextInt();
                    if (operationSymbol > 0 && operationSymbol < 11) {
                        break;
                    }
                    else {
                        System.out.println("Incorrect symbol. Repeat the entry:");
                    }
                }

                switch (operationSymbol)
                {
                    case 1:
                    {
                        matOp.printMinusMat(mat);
                        break;
                    }
                    case 2:
                    {
                        matOp.printMultiplicationByNum(mat);
                        break;
                    }
                    case 3:
                    {
                        matOp.printTranspose(mat);
                        break;
                    }
                    case 4:
                    {
                        matOp.printDeterminant(mat);
                        break;
                    }
                    case 5:
                    {
                        matOp.cyclicMatrixShift(mat);
                        break;
                    }
                    case 6:
                    {
                        matOp.printMatrixMinor(mat);
                        break;
                    }
                    case 7:
                    {
                        matOp.printInversMat(mat);
                        break;
                    }
                    case 8:
                    {
                        matOp.printAdding(mat);
                        break;
                    }
                    case 9:
                    {
                        matOp.printSubtracting(mat);
                        break;
                    }
                    case 10:
                    {
                        matOp.printMultiplicationByMat(mat);
                        break;
                    }
                }
                System.out.println("\nIs there anything else you want to do with these numbers? \n" +
                        "1 to another operation, or 2 to change the numbers, or 3 to quit");
                int choice;
                do
                {
                    choice = scan.nextInt();
                } while (choice != 1 && choice != 2 && choice != 3);

                if (choice == 2) break;
                if (choice == 3) break OUT;
            }
        }
    }
}