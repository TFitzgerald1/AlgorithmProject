package com.company;

import java.util.ArrayList;
import java.util.List;

public class DataParallelOnMatrices {

    DataParallelOnMatrices(int[][] MatrixA, int[][] MatrixB) {
        long start = System.nanoTime() / 1000000;
        int[][] MultipliedMatrix = new int[3][3];
        Parallel(MatrixA, MatrixB, MultipliedMatrix, start);
    }

    static void Parallel(int[][] MatrixA, int[][] MatrixB, int[][] MultipliedMatrix, long start) {
        Runnable run;
        run = () ->
        {
            for(int i = 0; i < 3; i++){
                for(int k = 0; k < 3; k++){
                    for (int l = 0; l < 3; l++) {
                        MultipliedMatrix[i][k] += MatrixA[i][l] * MatrixB[l][k];
                    }
                }
            }
        };
        List<Thread> tasks = new ArrayList<>();
        tasks.add(new Thread(run));
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
        PrintMatrix(MultipliedMatrix, start);
    }
    static void PrintMatrix(int[][] MultipliedMatrix, long start){
        long end = System.nanoTime() / 1000000;
        long Time = end - start;
        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 3; k++){
                System.out.print("| " + MultipliedMatrix[i][k] + " | ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Run Time in MilliSeconds: " + Time);
    }
}
