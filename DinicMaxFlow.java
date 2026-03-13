import java.util.*;

public class DinicMaxFlow {
    static int N;
    static int[][] cap;
    static List<Integer>[] g;
    static int[] level, iter;

    static void addEdge(int u, int v, int c) {
        g[u].add(v); g[v].add(u);
        cap[u][v] += c;
    }

    static boolean bfs(int s, int t) {
        level = new int[N]; Arrays.fill(level, -1); level[s] = 0;
        Queue<Integer> q = new LinkedList<>(); q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g[u]) if (cap[u][v] > 0 && level[v] < 0) { level[v] = level[u]+1; q.add(v); }
        }
        return level[t] >= 0;
    }

    static int dfs(int u, int t, int f) {
        if (u == t) return f;
        for (; iter[u] < g[u].size(); iter[u]++) {
            int v = g[u].get(iter[u]);
            if (cap[u][v] > 0 && level[v] == level[u]+1) {
                int d = dfs(v, t, Math.min(f, cap[u][v]));
                if (d > 0) { cap[u][v] -= d; cap[v][u] += d; return d; }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        N = 6; cap = new int[N][N];
        g = new ArrayList[N];
        for (int i = 0; i < N; i++) g[i] = new ArrayList<>();
        addEdge(0,1,10); addEdge(0,2,10); addEdge(1,2,2); addEdge(1,3,4);
        addEdge(1,4,8); addEdge(2,4,9); addEdge(3,5,10); addEdge(4,3,6); addEdge(4,5,10);
        long flow = 0; int s = 0, t = 5;
        while (bfs(s, t)) { iter = new int[N]; int f; while ((f = dfs(s,t,Integer.MAX_VALUE)) > 0) flow += f; }
        System.out.println("Max flow: " + flow);
    }
}
