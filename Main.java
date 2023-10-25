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

        ArrayList<Matrix> AList = new ArrayList<>();
        ArrayList<Matrix> BList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            int[][] arr1 = {{R(),R()},{R(),R()}};
            Matrix m1 = new Matrix(arr1, i);
            AList.add(m1);
            int[][] arr2 = {{R(),R()},{R(),R()}};
            Matrix m2 = new Matrix(arr2, i);
            BList.add(m2);
        }

        for(int i = 0; i < 100; i++) {
            AList.get(i).start();
        }

        for(int i = 0; i < 100; i++) {
            AList.get(i).print();
        }


        /*
        int menu = -1;
        while(menu != 4) {
            System.out.println("""
                    1. input matrix one
                    2. input matrix two
                    3. multiply matrices
                    4. end program""");
            menu = menuSelection.nextInt();

            switch (menu) {
                case 1:
                    userInput = sc.Next;
                    break;
            }
        }
        */
    }
}