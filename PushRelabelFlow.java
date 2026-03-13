import java.util.*;

// Push-Relabel Max Flow (O(V^2 * E))
public class PushRelabelFlow {
    static int n;
    static int[][] cap, flow;
    static int[] height, excess;

    static void push(int u, int v) {
        int d = Math.min(excess[u], cap[u][v]-flow[u][v]);
        flow[u][v] += d; flow[v][u] -= d;
        excess[u] -= d; excess[v] += d;
    }

    static void relabel(int u) {
        int minH = Integer.MAX_VALUE;
        for (int v=0;v<n;v++) if (cap[u][v]-flow[u][v]>0) minH=Math.min(minH,height[v]);
        height[u] = minH + 1;
    }

    static int maxflow(int s, int t) {
        height[s]=n; excess[s]=Integer.MAX_VALUE;
        for (int v=0;v<n;v++) if (cap[s][v]>0) push(s,v);
        boolean changed=true;
        while (changed) {
            changed=false;
            for (int u=0;u<n;u++) {
                if (u==s||u==t||excess[u]<=0) continue;
                boolean pushed=false;
                for (int v=0;v<n;v++) if (cap[u][v]-flow[u][v]>0 && height[u]==height[v]+1) { push(u,v); pushed=true; }
                if (!pushed) { relabel(u); changed=true; }
                else changed=true;
            }
        }
        return excess[t];
    }

    public static void main(String[] args) {
        n=6; cap=new int[n][n]; flow=new int[n][n]; height=new int[n]; excess=new int[n];
        cap[0][1]=15; cap[0][2]=4; cap[1][2]=5; cap[1][3]=12; cap[2][4]=10; cap[3][5]=7; cap[4][3]=3; cap[4][5]=10;
        System.out.println("Max flow (Push-Relabel): " + maxflow(0,5));
    }
}
