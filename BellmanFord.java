import java.util.*;

public class BellmanFord {
    static int[] bellmanFord(int src, int[][] edges, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w;
            }
        }
        // check for negative cycles
        for (int[] e : edges) {
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                System.out.println("Negative cycle detected!");
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,-1},{0,2,4},{1,2,3},{1,3,2},{1,4,2},{3,2,5},{3,1,1},{4,3,-3}};
        int[] d = bellmanFord(0, edges, 5);
        for (int i = 0; i < 5; i++)
            System.out.println("dist[" + i + "] = " + d[i]);
    }
}
