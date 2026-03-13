import java.util.*;

// HLD for path queries on trees
public class HeavyLightDecomposition {
    static int[] parent, depth, heavy, head, pos, cur_pos;
    static int[] val, seg; // segment tree
    static List<Integer>[] tree;
    static int n;

    static int dfs(int v, int p, int d) {
        parent[v]=p; depth[v]=d;
        int sz=1, maxSz=0; heavy[v]=-1;
        for (int u : tree[v]) if (u!=p) {
            int s=dfs(u,v,d+1); sz+=s;
            if (s>maxSz) { maxSz=s; heavy[v]=u; }
        }
        return sz;
    }

    static void decompose(int v, int h) {
        head[v]=h; pos[v]=cur_pos[0]++;
        if (heavy[v]!=-1) decompose(heavy[v],h);
        for (int u : tree[v]) if (u!=parent[v] && u!=heavy[v]) decompose(u,u);
    }

    static int query(int u, int v) {
        int res=0;
        while (head[u]!=head[v]) {
            if (depth[head[u]]<depth[head[v]]) { int t=u; u=v; v=t; }
            res += rangeQuery(pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u]>depth[v]) { int t=u; u=v; v=t; }
        res += rangeQuery(pos[u], pos[v]);
        return res;
    }

    static int rangeQuery(int l, int r) { return r - l + 1; /* placeholder */ }

    public static void main(String[] args) {
        n = 7;
        tree = new ArrayList[n]; for (int i=0;i<n;i++) tree[i]=new ArrayList<>();
        parent=new int[n]; depth=new int[n]; heavy=new int[n]; head=new int[n]; pos=new int[n]; cur_pos=new int[]{0};
        int[][] edges={{0,1},{0,2},{1,3},{1,4},{4,5},{4,6}};
        for (int[] e:edges){tree[e[0]].add(e[1]);tree[e[1]].add(e[0]);}
        dfs(0,-1,0); decompose(0,0);
        System.out.println("HLD positions: " + Arrays.toString(pos));
        System.out.println("Path query (3,6): " + query(3,6));
    }
}
