import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.rmi.Naming;
public class ProjectMain {
	
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		try {
		int epochs = 1000;
		ArrayList<int[][]> AList = new ArrayList<int[][]>();
		ArrayList<int[][]> BList = new ArrayList<int[][]>();
		boolean loop = true;
		int userSelection;
		int N = 0;
		while (loop) {
			userSelection = PrintMenu();
			switch (userSelection) {
			case 1:
				System.out.print("N matrices: ");
		        N = Integer.parseInt(scan.nextLine());
				AList = new ArrayList<int[][]>();
				BList = new ArrayList<int[][]>();
				for (int i = 0; i < N; i++) {
					int[][] matrix1 = {{R(),R()},{R(),R()}};
					AList.add(matrix1);
					int[][] matrix2 = {{R(),R()},{R(),R()}};
					BList.add(matrix2);
				}
				break;
			//Data Parallel Method
			case 2:
				ArrayList<Double> averages = new ArrayList<Double>();
				for (int epochNum = 0; epochNum < epochs; epochNum++) {
				double startTime2 = System.nanoTime();
				CountDownLatch latch2 = new CountDownLatch(5);
				int chunkSize = N / 5;
				ArrayList<int[][]> Achunk1 = new ArrayList<int[][]>(AList.subList(0, chunkSize - 1));
				ArrayList<int[][]> Achunk2 = new ArrayList<int[][]>(AList.subList(chunkSize, (2 * chunkSize) - 1));
				ArrayList<int[][]> Achunk3 = new ArrayList<int[][]>(AList.subList((2 * chunkSize), (3 * chunkSize) - 1));
				ArrayList<int[][]> Achunk4 = new ArrayList<int[][]>(AList.subList((3 * chunkSize), (4 * chunkSize) - 1));
				ArrayList<int[][]> Achunk5 = new ArrayList<int[][]>(AList.subList((4 * chunkSize), (5 * chunkSize) - 1));
				
				ArrayList<int[][]> Bchunk1 = new ArrayList<int[][]>(BList.subList(0, chunkSize - 1));
				ArrayList<int[][]> Bchunk2 = new ArrayList<int[][]>(BList.subList(chunkSize, (2 * chunkSize) - 1));
				ArrayList<int[][]> Bchunk3 = new ArrayList<int[][]>(BList.subList((2 * chunkSize), (3 * chunkSize) - 1));
				ArrayList<int[][]> Bchunk4 = new ArrayList<int[][]>(BList.subList((3 * chunkSize), (4 * chunkSize) - 1));
				ArrayList<int[][]> Bchunk5 = new ArrayList<int[][]>(BList.subList((4 * chunkSize), (5 * chunkSize) - 1));
				
				DataParallelOnMatrices run1 = new DataParallelOnMatrices(Achunk1,Bchunk1, latch2);
				DataParallelOnMatrices run2 = new DataParallelOnMatrices(Achunk2,Bchunk2, latch2);
				DataParallelOnMatrices run3 = new DataParallelOnMatrices(Achunk3,Bchunk3, latch2);
				DataParallelOnMatrices run4 = new DataParallelOnMatrices(Achunk4,Bchunk4, latch2);
				DataParallelOnMatrices run5 = new DataParallelOnMatrices(Achunk5,Bchunk5, latch2);
				
				run1.start();
				run2.start();
				run3.start();
				run4.start();
				run5.start();
				latch2.await();
				double endTime2 = System.nanoTime();
				double elapsedTime2 = (endTime2- startTime2) / Math.pow(10, 9);
				
				averages.add(elapsedTime2);
				}
				double sum = 0;
				for (Double entry : averages) {
					sum += entry;
				}
				sum = sum / averages.size();
				System.out.println("Average Elapsed Time: " + sum + " Seconds");
				break;
			//Task Graph Model
			case 3:
				ArrayList<Double> averages3 = new ArrayList<Double>();
				for (int epochNum = 0; epochNum < epochs; epochNum++) {
				double startTime3 = System.nanoTime();
				CountDownLatch latch3 = new CountDownLatch(3);
				eigenvalue e = new eigenvalue(latch3, AList);
		        inversion i = new inversion(latch3, AList);
		        multiplication m = new multiplication(latch3, AList, BList);
		        e.start();
		        i.start();
		        m.start();
		        latch3.await();
		        double endTime3 = System.nanoTime();
				double elapsedTime3 = (endTime3 - startTime3) / Math.pow(10, 9);
				
				averages3.add(elapsedTime3);
				}
				double sum3 = 0;
				for (Double entry : averages3) {
					sum3 += entry;
				}
				sum3 = sum3 / averages3.size();
				System.out.println("Average Elapsed Time: " + sum3 + " Seconds");
					break;
			// Java RMI method
			case 4:
				ArrayList<Double> averages4 = new ArrayList<Double>();
				for (int epochNum = 0; epochNum < epochs; epochNum++) {
				javaRMI access = (javaRMI) Naming.lookup("rmi://localhost:1900"+
	                    "/geeksforgeeks");
				double startTime = System.nanoTime();
				access.matrixMultiplication(AList, BList);
				access.eigenValueComputation(AList);
	        	access.matrixInversion(AList);
				double endTime = System.nanoTime();
				double elapsedTime4 = (endTime - startTime) / Math.pow(10, 9);

				averages4.add(elapsedTime4);
				}
				double sum4 = 0;
				for (Double entry : averages4) {
					sum4 += entry;
				}
				sum4 = sum4 / averages4.size();
				System.out.println("Average Elapsed Time: " + sum4 + " Seconds");
				
				break;
			case 5:
				loop = false;
				break;
			default:
				System.out.println("Error Incorrect input, please enter a valid selection.");
			}
				
		}
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
		System.out.println("1. Create N matrices");
		System.out.println("2. Data Parallel Method");
		System.out.println("3. Task Graph Model");
		System.out.println("4. Remote Method Invocation");
		System.out.println("5. Quit");
        System.out.print("Enter a number to select the following menu options: ");
        int output = Integer.parseInt(scan.nextLine());
        return output;
	}
}
