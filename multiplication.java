import java.util.ArrayList;

public class multiplication extends Thread{
    int[][] finalMatrix;

    multiplication(ArrayList<int[][]> AList,ArrayList<int[][]> BList) {
        for (int i = 0; i < AList.size(); i++) {
            int[][] matrix1 = AList.get(i);
            int[][] matrix2 = BList.get(i);

            int limit = matrix1.length;
            finalMatrix = new int[limit][limit];
            for (int j = 0; j < limit; j++) {
                for (int k = 0; k < limit; k++) {
                    for (int l = 0; l < limit; l++) {
                        finalMatrix[j][k] += matrix1[j][l] * matrix2[k][l];
                    }
                }
            }
        }
    }
}
