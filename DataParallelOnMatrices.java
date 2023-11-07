import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataParallelOnMatrices extends Thread{
	ArrayList<int[][]> AList;
	ArrayList<int[][]> BList;
	CountDownLatch latch;
    DataParallelOnMatrices(ArrayList<int[][]> aList, ArrayList<int[][]> bList, CountDownLatch inLatch) {
        AList = aList;
        BList = bList;
        latch = inLatch;
    }

    public void run() {
        	for (int i = 0; i < AList.size(); i++) {
        		//Matrix Multiplication
    			int[][] matrix1 = AList.get(i);
    			int[][] matrix2 = BList.get(i);
    			
    			int limit = matrix1.length;
    			int[][] MultipliedMatrix = new int[limit][limit];
    			for (int j = 0; j < limit; j++) {
    		    	for (int k = 0; k < limit; k++) {
    		    		for (int l = 0; l < limit; l++) {
    		    			MultipliedMatrix[j][k] += matrix1[j][l] * matrix2[k][l]; 
    		    		}
    		    	}
    		    }
    			//Eigen Value Computation
    			int[][] matrix = AList.get(i);
    			int w = matrix[0][0];
    			int x = matrix[0][1];
    			int y = matrix[1][0];
    			int z = matrix[1][1];
    			
    			int B = -(z+w);
    			int C = (w*z)-(x*y);
    			
    			double eigenValue1 = (-(B) + Math.sqrt((B*B) - 4*C))/2;
    			double eigenValue2 = (-(B) - Math.sqrt((B*B) - 4*C))/2;
    			
    			// Matrix Inversion
    			int[][] outMatrix = new int[2][2];
    			int[][] matrixDos = AList.get(i);
    			int w2 = matrixDos[0][0];
    			int x2 = matrixDos[0][1];
    			int y2 = matrixDos[1][0];
    			int z2 = matrixDos[1][1];
    			int det = ((w2*z2) - (x2*y2));
    			if (det == 0) {
    				//System.out.println("Matrix is not invertible");
    			} else {
    				outMatrix[0][0] = (z2 / det);
    				outMatrix[0][1] = (-x2 / det);
    				outMatrix[1][0] = (-y2 / det);
    				outMatrix[1][1] = (w2 / det);
    			}
    			
    		}
        	latch.countDown();
    }
}
