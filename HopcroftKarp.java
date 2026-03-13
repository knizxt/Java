import java.util.*;

// Hopcroft-Karp for maximum bipartite matching
public class HopcroftKarp {
    static int L, R, NIL = 0;
    static List<Integer>[] adj;
    static int[] matchL, matchR, dist;

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int u = 1; u <= L; u++) {
            if (matchL[u] == NIL) { dist[u] = 0; q.add(u); }
            else dist[u] = Integer.MAX_VALUE;
        }
        dist[NIL] = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (dist[u] < dist[NIL])
                for (int v : adj[u]) {
                    int w = matchR[v];
                    if (dist[w] == Integer.MAX_VALUE) { dist[w] = dist[u]+1; if (w!=NIL) q.add(w); }
                }
        }
        return dist[NIL] != Integer.MAX_VALUE;
    }

    static boolean dfs(int u) {
        if (u == NIL) return true;
        for (int v : adj[u]) {
            int w = matchR[v];
            if (dist[w] == dist[u]+1 && dfs(w)) { matchL[u]=v; matchR[v]=u; return true; }
        }
        dist[u] = Integer.MAX_VALUE; return false;
    }

    public static void main(String[] args) {
        L = 4; R = 4;
        adj = new ArrayList[L+1];
        for (int i = 1; i <= L; i++) adj[i] = new ArrayList<>();
        adj[1].add(2); adj[1].add(3); adj[2].add(1); adj[3].add(2); adj[3].add(3); adj[4].add(2); adj[4].add(4);
        matchL = new int[L+1]; matchR = new int[R+1]; dist = new int[L+1];
        int match = 0;
        while (bfs()) for (int u = 1; u <= L; u++) if (matchL[u]==NIL && dfs(u)) match++;
        System.out.println("Max bipartite matching: " + match);
    }
}
