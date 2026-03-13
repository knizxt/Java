import java.util.*;

public class GraphColoring {
    static int V = 4, M = 3;
    static int[][] graph = {{0,1,1,1},{1,0,1,0},{1,1,0,1},{1,0,1,0}};
    static int[] color = new int[V];

    static boolean isSafe(int v, int c) {
        for (int i=0;i<V;i++) if (graph[v][i]==1 && color[i]==c) return false;
        return true;
    }

    static boolean solve(int v) {
        if (v == V) return true;
        for (int c=1;c<=M;c++) {
            if (isSafe(v, c)) {
                color[v] = c;
                if (solve(v+1)) return true;
                color[v] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (solve(0)) {
            System.out.print("Graph coloring (M=" + M + "): ");
            System.out.println(Arrays.toString(color));
        } else System.out.println("No valid coloring with " + M + " colors.");
    }
}
