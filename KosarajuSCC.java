import java.util.*;

public class KosarajuSCC {
    static List<Integer>[] g, rg;
    static boolean[] vis;
    static Stack<Integer> order = new Stack<>();

    static void dfs1(int u) {
        vis[u] = true;
        for (int v : g[u]) if (!vis[v]) dfs1(v);
        order.push(u);
    }

    static void dfs2(int u, List<Integer> comp) {
        vis[u] = true; comp.add(u);
        for (int v : rg[u]) if (!vis[v]) dfs2(v, comp);
    }

    public static void main(String[] args) {
        int n = 5;
        g = new ArrayList[n]; rg = new ArrayList[n];
        for (int i = 0; i < n; i++) { g[i] = new ArrayList<>(); rg[i] = new ArrayList<>(); }
        int[][] edges = {{0,2},{2,1},{1,0},{0,3},{3,4}};
        for (int[] e : edges) { g[e[0]].add(e[1]); rg[e[1]].add(e[0]); }
        vis = new boolean[n];
        for (int i = 0; i < n; i++) if (!vis[i]) dfs1(i);
        vis = new boolean[n];
        int count = 0;
        while (!order.isEmpty()) {
            int u = order.pop();
            if (!vis[u]) {
                List<Integer> comp = new ArrayList<>();
                dfs2(u, comp);
                System.out.println("SCC " + (++count) + ": " + comp);
            }
        }
    }
}
