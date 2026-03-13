import java.util.*;

public class CentroidDecomposition {
    static List<Integer>[] tree;
    static int[] sz; static boolean[] removed;
    static int n;

    static int getSize(int u, int p) {
        sz[u]=1;
        for (int v:tree[u]) if (v!=p && !removed[v]) sz[u]+=getSize(v,u);
        return sz[u];
    }

    static int getCentroid(int u, int p, int treeSize) {
        for (int v:tree[u]) if (v!=p && !removed[v] && sz[v]>treeSize/2) return getCentroid(v,u,treeSize);
        return u;
    }

    static void decompose(int u, int p) {
        int treeSize = getSize(u, p);
        int centroid = getCentroid(u, p, treeSize);
        removed[centroid] = true;
        System.out.println("Centroid: " + centroid);
        for (int v : tree[centroid]) if (!removed[v]) decompose(v, centroid);
    }

    public static void main(String[] args) {
        n=7; tree=new ArrayList[n]; for (int i=0;i<n;i++) tree[i]=new ArrayList<>();
        int[][] e={{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
        for (int[] edge:e){tree[edge[0]].add(edge[1]);tree[edge[1]].add(edge[0]);}
        sz=new int[n]; removed=new boolean[n];
        decompose(0,-1);
    }
}
