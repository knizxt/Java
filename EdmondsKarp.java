import java.util.*;

public class EdmondsKarp {
    static int[][] cap;
    static int N;

    static int bfs(int s, int t, int[] parent) {
        Arrays.fill(parent, -1); parent[s] = s;
        Queue<int[]> q = new LinkedList<>(); q.add(new int[]{s, Integer.MAX_VALUE});
        while (!q.isEmpty()) {
            int[] cur = q.poll(); int u = cur[0], flow = cur[1];
            for (int v = 0; v < N; v++) {
                if (parent[v] == -1 && cap[u][v] > 0) {
                    parent[v] = u;
                    int newFlow = Math.min(flow, cap[u][v]);
                    if (v == t) return newFlow;
                    q.add(new int[]{v, newFlow});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        N = 6; cap = new int[N][N];
        cap[0][1]=16; cap[0][2]=13; cap[1][2]=10; cap[1][3]=12;
        cap[2][1]=4; cap[2][4]=14; cap[3][2]=9; cap[3][5]=20; cap[4][3]=7; cap[4][5]=4;
        int flow = 0, s = 0, t = 5; int[] parent = new int[N];
        int newFlow;
        while ((newFlow = bfs(s, t, parent)) != 0) {
            flow += newFlow; int cur = t;
            while (cur != s) { int prev = parent[cur]; cap[prev][cur] -= newFlow; cap[cur][prev] += newFlow; cur = prev; }
        }
        System.out.println("Max flow (Edmonds-Karp): " + flow);
    }
}
