import java.util.*;

// Min-Cost Max-Flow using SPFA (Bellman-Ford based)
public class MinCostMaxFlow {
    static final int INF = Integer.MAX_VALUE/2;
    static int n;
    static List<int[]>[] g; // [to, cap, cost, rev_idx]

    static void addEdge(int u, int v, int cap, int cost) {
        g[u].add(new int[]{v, cap, cost, g[v].size()});
        g[v].add(new int[]{u, 0, -cost, g[u].size()-1});
    }

    static int[] mcmf(int s, int t) {
        int totalFlow=0, totalCost=0;
        while (true) {
            int[] dist=new int[n]; Arrays.fill(dist,INF); dist[s]=0;
            boolean[] inQ=new boolean[n]; inQ[s]=true;
            int[] prevv=new int[n], preve=new int[n]; Arrays.fill(prevv,-1);
            Queue<Integer> q=new LinkedList<>(); q.add(s);
            while (!q.isEmpty()) {
                int u=q.poll(); inQ[u]=false;
                for (int i=0;i<g[u].size();i++) {
                    int[] e=g[u].get(i);
                    if (e[1]>0 && dist[u]+e[2]<dist[e[0]]) {
                        dist[e[0]]=dist[u]+e[2]; prevv[e[0]]=u; preve[e[0]]=i;
                        if (!inQ[e[0]]) { q.add(e[0]); inQ[e[0]]=true; }
                    }
                }
            }
            if (dist[t]==INF) break;
            int d=INF;
            for (int v=t;v!=s;v=prevv[v]) d=Math.min(d,g[prevv[v]].get(preve[v])[1]);
            for (int v=t;v!=s;v=prevv[v]) {
                int[] e=g[prevv[v]].get(preve[v]); e[1]-=d;
                g[v].get(e[3])[1]+=d;
            }
            totalFlow+=d; totalCost+=d*dist[t];
        }
        return new int[]{totalFlow,totalCost};
    }

    public static void main(String[] args) {
        n=4; g=new ArrayList[n]; for (int i=0;i<n;i++) g[i]=new ArrayList<>();
        addEdge(0,1,3,1); addEdge(0,2,2,2); addEdge(1,3,2,3); addEdge(2,3,3,1);
        int[] res=mcmf(0,3);
        System.out.println("Max flow: "+res[0]+", Min cost: "+res[1]);
    }
}
