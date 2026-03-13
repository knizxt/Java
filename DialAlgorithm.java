import java.util.*;

// Dial's Algorithm - Dijkstra with bucket queues, efficient for small weights
public class DialAlgorithm {
    public static void main(String[] args) {
        int V = 4, W = 10;
        int[][][] graph = {
            {{1,2},{2,1}},
            {{2,3},{3,4}},
            {{3,2}},
            {}
        };
        int[] dist = new int[V]; Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        List<Integer>[] bucket = new ArrayList[V*W+1];
        for (int i=0;i<bucket.length;i++) bucket[i]=new ArrayList<>();
        bucket[0].add(0);
        int idx = 0;
        for (int i=0;i<V*W+1;i++) {
            while (!bucket[i].isEmpty()) {
                int u = bucket[i].remove(bucket[i].size()-1);
                if (dist[u] != i) continue; // stale
                for (int[] e : graph[u]) {
                    int v=e[0], w=e[1];
                    if (dist[u]+w < dist[v]) {
                        dist[v]=dist[u]+w;
                        bucket[dist[v]].add(v);
                    }
                }
            }
        }
        System.out.println("Dial distances: " + Arrays.toString(dist));
    }
}
