import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class multiplication extends Thread{
    int[][] finalMatrix;
    ArrayList<int[][]> AList;
    ArrayList<int[][]> BList;
    CountDownLatch latch;
    
    multiplication(CountDownLatch inLatch, ArrayList<int[][]> inList1,ArrayList<int[][]> inList2) {
        AList = inList1;
        BList = inList2;
        latch = inLatch;
    }
    public void run() {
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
    	latch.countDown();
    }
}
