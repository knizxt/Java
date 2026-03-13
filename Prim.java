import java.util.*;

public class Prim {
    public static void main(String[] args) {
        int n = 5;
        int[][] g = {
            {0,2,0,6,0},
            {2,0,3,8,5},
            {0,3,0,0,7},
            {6,8,0,0,9},
            {0,5,7,9,0}
        };
        boolean[] inMST = new boolean[n];
        int[] key = new int[n], parent = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;
        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            for (int v = 0; v < n; v++)
                if (!inMST[v] && (u == -1 || key[v] < key[u])) u = v;
            inMST[u] = true;
            for (int v = 0; v < n; v++)
                if (g[u][v] != 0 && !inMST[v] && g[u][v] < key[v]) {
                    key[v] = g[u][v]; parent[v] = u;
                }
        }
        int total = 0;
        for (int i = 1; i < n; i++) { System.out.println(parent[i]+" - "+i+" : "+key[i]); total+=key[i]; }
        System.out.println("MST weight: " + total);
    }
}
