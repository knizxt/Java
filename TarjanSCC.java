import java.util.*;

// Tarjan's algorithm for Strongly Connected Components
public class TarjanSCC {
    static int[] disc, low, stack;
    static boolean[] onStack;
    static int timer = 0, top = 0;
    static List<Integer>[] graph;
    static List<List<Integer>> sccs = new ArrayList<>();

    static void dfs(int u) {
        disc[u] = low[u] = timer++;
        stack[top++] = u;
        onStack[u] = true;
        for (int v : graph[u]) {
            if (disc[v] == -1) { dfs(v); low[u] = Math.min(low[u], low[v]); }
            else if (onStack[v]) low[u] = Math.min(low[u], disc[v]);
        }
        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int v = stack[--top]; onStack[v] = false; scc.add(v);
                if (v == u) break;
            }
            sccs.add(scc);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        graph[0].add(2); graph[2].add(1); graph[1].add(0);
        graph[0].add(3); graph[3].add(4);
        disc = new int[n]; low = new int[n]; stack = new int[n]; onStack = new boolean[n];
        Arrays.fill(disc, -1);
        for (int i = 0; i < n; i++) if (disc[i] == -1) dfs(i);
        System.out.println("SCCs found: " + sccs.size());
        for (List<Integer> scc : sccs) System.out.println(scc);
    }
}
