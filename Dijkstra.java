import java.util.*;

public class Dijkstra {
    static final int INF = Integer.MAX_VALUE;

    static int[] dijkstra(int src, List<int[]>[] graph, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1];
            if (d > dist[u]) continue;
            for (int[] e : graph[u]) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 5;
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        g[0].add(new int[]{1, 10}); g[0].add(new int[]{2, 3});
        g[2].add(new int[]{1, 4}); g[1].add(new int[]{3, 2});
        g[2].add(new int[]{3, 8}); g[3].add(new int[]{4, 5});
        int[] dist = dijkstra(0, g, n);
        System.out.println("Shortest distances from node 0:");
        for (int i = 0; i < n; i++)
            System.out.println("  to " + i + " = " + dist[i]);
    }
}
