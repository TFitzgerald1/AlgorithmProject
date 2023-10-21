import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Algorithm extends UnicastRemoteObject implements javaRMI {
//	@Override
//	public void run() {
//		
//	}
//	@Override
	public Algorithm() throws RemoteException { super();}
	public void matrixMultiplication(ArrayList<int[][]> AList,ArrayList<int[][]> BList) {
		for (int i = 0; i < AList.size(); i++) {
			int[][] matrix1 = AList.get(i);
			int[][] matrix2 = BList.get(i);
			
			int limit = matrix1.length;
			int[][] finalMatrix = new int[limit][limit];
			for (int j = 0; j < limit; j++) {
		    	for (int k = 0; k < limit; k++) {
		    		for (int l = 0; l < limit; l++) {
		    			finalMatrix[j][k] += matrix1[j][l] * matrix2[k][l]; 
		    		}
		    	}
		    }
			
		}
		
	}
	public void eigenValueComputation(ArrayList<int[][]> AList) {
		for (int i = 0; i < AList.size(); i++) {
			int[][] matrix = AList.get(i);
			int w = matrix[0][0];
			int x = matrix[0][1];
			int y = matrix[1][0];
			int z = matrix[1][1];
			
			int B = -(z+w);
			int C = (w*z)-(x*y);
			
			double eigenValue1 = (-(B) + Math.sqrt((B*B) - 4*C))/2;
			double eigenValue2 = (-(B) - Math.sqrt((B*B) - 4*C))/2;
		}
	}
	public void matrixInversion(ArrayList<int[][]> AList) {
		int[][] outMatrix = new int[2][2];
		for (int i = 0; i < AList.size(); i++) {
			int[][] matrix = AList.get(i);
			int w = matrix[0][0];
			int x = matrix[0][1];
			int y = matrix[1][0];
			int z = matrix[1][1];
			int det = ((w*z) - (x*y));
			if (det == 0) {
				System.out.println("Matrix is not invertible");
			} else {
				outMatrix[0][0] = (z / det);
				outMatrix[0][1] = (-x / det);
				outMatrix[1][0] = (-y / det);
				outMatrix[1][1] = (w / det);
			}
			
		}
	}
}
