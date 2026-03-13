import java.util.*;

// Hierholzer's algorithm for Eulerian path/circuit
public class EulerianPath {
    static List<Integer>[] graph;
    static int[] edgeIndex;
    static List<Integer> path = new ArrayList<>();

    static void dfs(int u) {
        while (edgeIndex[u] < graph[u].size()) {
            int v = graph[u].get(edgeIndex[u]++);
            dfs(v);
        }
        path.add(u);
    }

    public static void main(String[] args) {
        int n = 5;
        graph = new ArrayList[n]; for (int i=0;i<n;i++) graph[i]=new ArrayList<>();
        int[][] edges = {{0,1},{0,2},{1,2},{1,3},{2,4},{3,4}};
        for (int[] e : edges) { graph[e[0]].add(e[1]); graph[e[1]].add(e[0]); }
        edgeIndex = new int[n];
        // start from node 0 (assuming Eulerian circuit)
        dfs(0);
        Collections.reverse(path);
        System.out.println("Eulerian circuit: " + path);
    }
}
