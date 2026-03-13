import java.util.*;

public class ArticulationPoints {
    static int[] disc, low, parent;
    static boolean[] ap, vis;
    static List<Integer>[] g;
    static int timer;

    static void dfs(int u) {
        vis[u] = true; disc[u] = low[u] = timer++;
        int children = 0;
        for (int v : g[u]) {
            if (!vis[v]) {
                children++; parent[v] = u; dfs(v);
                low[u] = Math.min(low[u], low[v]);
                if (parent[u] == -1 && children > 1) ap[u] = true;
                if (parent[u] != -1 && low[v] >= disc[u]) ap[u] = true;
            } else if (v != parent[u]) low[u] = Math.min(low[u], disc[v]);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        g = new ArrayList[n]; for (int i=0;i<n;i++) g[i]=new ArrayList<>();
        g[1].add(0); g[0].add(1); g[0].add(2); g[2].add(0); g[1].add(2); g[2].add(1);
        g[0].add(3); g[3].add(0); g[3].add(4); g[4].add(3);
        disc=new int[n]; low=new int[n]; parent=new int[n]; ap=new boolean[n]; vis=new boolean[n];
        Arrays.fill(parent,-1);
        for (int i=0;i<n;i++) if (!vis[i]) dfs(i);
        System.out.print("Articulation points: ");
        for (int i=0;i<n;i++) if (ap[i]) System.out.print(i+" ");
        System.out.println();
    }
}
