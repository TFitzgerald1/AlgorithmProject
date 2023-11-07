import java.rmi.*;
import java.util.ArrayList;
public interface javaRMI extends Remote
{
	public void matrixMultiplication(ArrayList<int[][]> AList,
			ArrayList<int[][]> BList) throws RemoteException;
	public void eigenValueComputation(ArrayList<int[][]> AList) 
			throws RemoteException;
	public void matrixInversion(ArrayList<int[][]> AList) 
			throws RemoteException;
}
