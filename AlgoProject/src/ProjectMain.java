import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.registry.*;
public class ProjectMain {
	
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		try {
		int N = 10000;
		ArrayList<int[][]> AList = new ArrayList<int[][]>();
		ArrayList<int[][]> BList = new ArrayList<int[][]>();
		for (int i = 0; i < N; i++) {
			int[][] matrix1 = {{R(),R()},{R(),R()}};
			AList.add(matrix1);
			int[][] matrix2 = {{R(),R()},{R(),R()}};
			BList.add(matrix2);
		}
		
		Registry registry = LocateRegistry.getRegistry(null);
		//javaRMI stub = (javaRMI) registry.lookup("javaRMI");
		boolean loop = true;
		int userSelection;
		
		javaRMI access = (javaRMI) Naming.lookup("rmi://localhost:1900"+
                    "/geeksforgeeks");
		double startTime = System.nanoTime();
        access.matrixMultiplication(AList, BList);
        access.eigenValueComputation(AList);
        access.matrixInversion(AList);
		double endTime = System.nanoTime();
		double elapsedTime = (endTime - startTime) / Math.pow(10, 9);
		System.out.println("Elapsed Time: " + elapsedTime+ " Seconds");
//		for (int i = 0; i < matrix1.length; i++) {
//        	for (int j = 0; j < matrix1.length; j++) {
//        		System.out.print(matrix1[i][j] +" ");
//        	}
//        	System.out.print("\n");
//        }
/*		while (loop) {
			userSelection = PrintMenu();
			switch (userSelection) {
			case 1:
				stub.matrixMultiplication(matrix1, matrix2);
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				loop = false;
				break;
			default:
				System.out.println("Error Incorrect input, please enter a valid selection.");
			}
				
		} */
		} catch (Exception E) {
			System.err.println("Server exception: " + E.toString()); 
	        E.printStackTrace(); 
		}
		
	}
	static int R() {
		Random x = new Random();
		return (x.nextInt(100) + 1);
	}
	static int PrintMenu() {
		System.out.println();
		System.out.println("1. Enter an Integer Matrix (N by N)");
		System.out.println("2. Display List of Matricies");
		System.out.println("3. Print the list of chores");
		System.out.println("4. Quit");
        System.out.print("Enter a number to select the following menu options: ");
        int output = Integer.parseInt(scan.nextLine());
        return output;
	}
}
