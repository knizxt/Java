import java.util.*;

public class TopologicalSort {
    // Kahn's BFS-based topological sort
    static List<Integer> topoSort(int n, List<Integer>[] graph) {
        int[] indegree = new int[n];
        for (int u = 0; u < n; u++)
            for (int v : graph[u]) indegree[v]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) if (indegree[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll(); order.add(u);
            for (int v : graph[u]) if (--indegree[v] == 0) q.add(v);
        }
        return order.size() == n ? order : Collections.emptyList(); // empty means cycle
    }

    public static void main(String[] args) {
        int n = 6;
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        g[5].add(2); g[5].add(0); g[4].add(0); g[4].add(1); g[2].add(3); g[3].add(1);
        List<Integer> order = topoSort(n, g);
        System.out.println("Topological order: " + order);
    }
}
