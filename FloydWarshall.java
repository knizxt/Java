import java.util.*;

public class FloydWarshall {
    static final int INF = 99999999;

    static int[][] floydWarshall(int[][] dist, int n) {
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) d[i] = dist[i].clone();
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (d[i][k] + d[k][j] < d[i][j])
                        d[i][j] = d[i][k] + d[k][j];
        return d;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dist = {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0}
        };
        int[][] res = floydWarshall(dist, n);
        System.out.println("All-pairs shortest paths:");
        for (int[] row : res)
            System.out.println(Arrays.toString(row));
    }
}
