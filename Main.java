import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static int R() {
        Random x = new Random();
        return (x.nextInt(100) + 1);
    }

    public static void main(String[] args) {

        ArrayList<int[][]> AList = new ArrayList<>();
        ArrayList<int[][]> BList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            int[][] arr1 = {{R(),R()},{R(),R()}};
            AList.add(arr1);
            int[][] arr2 = {{R(),R()},{R(),R()}};
            BList.add(arr2);
        }

        eigenvalue e = new eigenvalue(AList);
        inversion i = new inversion(AList);
        multiplication m = new multiplication(AList, BList);

        e.start();
        i.start();
        m.start();
    }
}