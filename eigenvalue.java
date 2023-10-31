import java.util.ArrayList;

public class eigenvalue extends Thread {
    double eigenValue1;
    double eigenValue2;

    eigenvalue(ArrayList<int[][]> AList) {
        for (int i = 0; i < AList.size(); i++) {
            int[][] matrix = AList.get(i);
            int w = matrix[0][0];
            int x = matrix[0][1];
            int y = matrix[1][0];
            int z = matrix[1][1];

            int B = -(z+w);
            int C = (w*z)-(x*y);

            eigenValue1 = (-(B) + Math.sqrt((B*B) - 4*C))/2;
            eigenValue2 = (-(B) - Math.sqrt((B*B) - 4*C))/2;
        }
    }
}
