import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class inversion extends Thread{
    int[][] outMatrix = new int[2][2];
    ArrayList<int[][]> AList;
    CountDownLatch latch;
    
    inversion(CountDownLatch inLatch, ArrayList<int[][]> inList) {
        AList = inList;
        latch = inLatch;
    }
    public void run() {
    	for (int i = 0; i < AList.size(); i++) {
            int[][] matrix = AList.get(i);
            int w = matrix[0][0];
            int x = matrix[0][1];
            int y = matrix[1][0];
            int z = matrix[1][1];
            int det = ((w*z) - (x*y));
            if (det == 0) {
                //System.out.println("Matrix is not invertible");
            } else {
                outMatrix[0][0] = (z / det);
                outMatrix[0][1] = (-x / det);
                outMatrix[1][0] = (-y / det);
                outMatrix[1][1] = (w / det);
            }

        }
    	latch.countDown();
    }
}
