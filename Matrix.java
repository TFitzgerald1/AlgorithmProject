import java.util.Random;
public class Matrix extends Thread {
    int[][] arr;
    double eigenValue1;
    double eigenValue2;
    int[][] outMatrix = new int[2][2];
    int count;
    boolean invertible;

    Matrix(int[][] arr, int i ) {
        this.arr = arr;
        count = i;
    }

    void printMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.printf(" %-4d ", arr[i][j]);
            }
            System.out.println();
        }
    }

    void printOutMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.printf(" %-4d ", outMatrix[i][j]);
            }
            System.out.println();
        }
    }

    void print() {
        System.out.println("Matrix "+count+": ");
        printMatrix();
        System.out.println("Eigenvalue 1: "+eigenValue1);
        System.out.println("Eigenvalue 2: "+eigenValue2);
        if(invertible) {
            System.out.println("Inverse Matrix: ");
            printOutMatrix();
        } else {
            System.out.println("Matrix is not invertible");
        }
        System.out.println("----------");
    }

    void eigenValueComputation() {
        int w = arr[0][0];
        int x = arr[0][1];
        int y = arr[1][0];
        int z = arr[1][1];

        int B = -(z + w);
        int C = (w * z) - (x * y);

        eigenValue1 = (-(B) + Math.sqrt((B * B) - 4 * C)) / 2;
        eigenValue2 = (-(B) - Math.sqrt((B * B) - 4 * C)) / 2;
    }

    void matrixInversion() {
        int w = arr[0][0];
        int x = arr[0][1];
        int y = arr[1][0];
        int z = arr[1][1];
        int det = ((w * z) - (x * y));
        if (det == 0) {
            invertible = false;
        } else {
            outMatrix[0][0] = (z / det);
            outMatrix[0][1] = (-x / det);
            outMatrix[1][0] = (-y / det);
            outMatrix[1][1] = (w / det);
        }
    }

    public void run() {
        eigenValueComputation();
        matrixInversion();
    }
}
