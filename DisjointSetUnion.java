import java.util.*;

public class DisjointSetUnion {
    int[] parent, rank, size;

    DisjointSetUnion(int n) {
        parent=new int[n]; rank=new int[n]; size=new int[n];
        for (int i=0;i<n;i++) { parent[i]=i; size[i]=1; }
    }

    int find(int x) {
        if (parent[x]!=x) parent[x]=find(parent[x]); // path compression
        return parent[x];
    }

    boolean union(int a, int b) {
        int pa=find(a), pb=find(b);
        if (pa==pb) return false;
        if (rank[pa]<rank[pb]) { int t=pa; pa=pb; pb=t; }
        parent[pb]=pa; size[pa]+=size[pb];
        if (rank[pa]==rank[pb]) rank[pa]++;
        return true;
    }

    int componentSize(int x) { return size[find(x)]; }

    public static void main(String[] args) {
        DisjointSetUnion dsu = new DisjointSetUnion(5);
        dsu.union(0,1); dsu.union(1,2); dsu.union(3,4);
        System.out.println("find(0): " + dsu.find(0));
        System.out.println("find(2): " + dsu.find(2));
        System.out.println("Same component (0,2): " + (dsu.find(0)==dsu.find(2)));
        System.out.println("Same component (0,3): " + (dsu.find(0)==dsu.find(3)));
        System.out.println("Component size of 0: " + dsu.componentSize(0));
    }
}
