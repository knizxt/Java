import java.util.*;

public class LCA_BinaryLifting {
    static final int MAXLOG = 20;
    static int[][] up;
    static int[] depth;
    static List<Integer>[] tree;
    static int n;

    static void dfs(int u, int p, int d) {
        up[u][0] = p; depth[u] = d;
        for (int k=1;k<MAXLOG;k++) up[u][k] = up[up[u][k-1]][k-1];
        for (int v : tree[u]) if (v != p) dfs(v, u, d+1);
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t=u; u=v; v=t; }
        int diff = depth[u] - depth[v];
        for (int k=0;k<MAXLOG;k++) if (((diff>>k)&1)==1) u=up[u][k];
        if (u == v) return u;
        for (int k=MAXLOG-1;k>=0;k--) if (up[u][k]!=up[v][k]) { u=up[u][k]; v=up[v][k]; }
        return up[u][0];
    }

    public static void main(String[] args) {
        n = 7;
        tree = new ArrayList[n]; for (int i=0;i<n;i++) tree[i]=new ArrayList<>();
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
        for (int[] e : edges) { tree[e[0]].add(e[1]); tree[e[1]].add(e[0]); }
        up = new int[n][MAXLOG]; depth = new int[n];
        dfs(0, 0, 0);
        System.out.println("LCA(3,4) = " + lca(3,4));
        System.out.println("LCA(3,6) = " + lca(3,6));
        System.out.println("LCA(5,6) = " + lca(5,6));
    }
}
