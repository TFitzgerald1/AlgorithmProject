package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DataParallelOnMatrices {

    DataParallelOnMatrices(int[][] MatrixA, int[][] MatrixB) {
        //change the size of this 2x2 if you want to make the array bigger
        double[][] MultipliedMatrix = new double[2][2];
        double[][] InverseArray =  new double[2][2];
        Parallel(MatrixA, MatrixB, MultipliedMatrix, InverseArray);
    }

    static void Parallel(int[][] MatrixA, int[][] MatrixB, double[][] MultipliedMatrix, double[][] InverseArray) {
        Runnable runMultiply;
        Runnable runInverse;
        Runnable runEigen;
        //change the value in the for loop from 2 to the size of the matrix if changed
        runMultiply = () ->
        {
            for(int i = 0; i < 2; i++){
                for(int k = 0; k < 2; k++){
                    for (int l = 0; l < 2; l++) {
                        MultipliedMatrix[i][k] += MatrixA[i][l] * MatrixB[l][k];
                    }
                }
            }
        };
        runInverse = () ->
        {
            double determinant;
            int temp;
            float AxD;
            float BxC;
            AxD = MatrixA[0][0] * MatrixA[1][1];
            BxC = MatrixA[0][1] * MatrixA[1][0];
            determinant = 1 / (AxD - BxC);
            temp = MatrixA[0][0];
            MatrixA[0][0] = MatrixA[1][1];
            MatrixA[1][1] = temp;
            MatrixA[0][1] = -MatrixA[0][1];
            MatrixA[1][0] = -MatrixA[1][0];
            for(int i = 0; i < 2; i++){
                for(int k = 0; k < 2; k++){
                    InverseArray[i][k] = MatrixA[i][k] * determinant;
                }
            }
        };
        AtomicReference<Double> eigenValue1 = new AtomicReference<>((double) 0);
        AtomicReference<Double> eigenValue2 = new AtomicReference<>((double) 0);
        runEigen = () -> {
            int w = MatrixA[0][0];
            int x = MatrixA[0][1];
            int y = MatrixA[1][0];
            int z = MatrixA[1][1];

            int B = -(z+w);
            int C = (w*z)-(x*y);

            eigenValue1.set((-(B) + Math.sqrt((B * B) - 4 * C)) / 2);
            eigenValue2.set((-(B) - Math.sqrt((B * B) - 4 * C)) / 2);
        };
        List<Thread> tasks = new ArrayList<>();
        tasks.add(new Thread(runMultiply));
        tasks.add(new Thread(runInverse));
        tasks.add(new Thread(runEigen));
        try {
            for(Thread thread : tasks) {
                thread.start();
            }
            for (Thread thread : tasks) {
                thread.join();
            }
        } catch (Exception e) {
            System.out.println("Join Error");
        }
        PrintMatrix(MultipliedMatrix);
        PrintMatrix(InverseArray);
        System.out.println("EigenValue 1 is: " + eigenValue1);
        System.out.println("EigenValue 2 is: " + eigenValue2);
    }
    static void PrintMatrix(double[][] MultipliedMatrix){
        //change the value in the for loop from 2 to the size of the matrix if changed
        for(int i = 0; i < 2; i++){
            for(int k = 0; k < 2; k++){
                System.out.print("| " + MultipliedMatrix[i][k] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
