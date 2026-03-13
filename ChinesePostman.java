import java.util.*;

// Chinese Postman Problem (Route Inspection for undirected graphs)
public class ChinesePostman {
    static int[] match;
    static int[][] dist;
    static int n;

    // Floyd-Warshall for all-pairs
    static void floyd() {
        for (int k=0;k<n;k++) for (int i=0;i<n;i++) for (int j=0;j<n;j++)
            if (dist[i][k]+dist[k][j]<dist[i][j]) dist[i][j]=dist[i][k]+dist[k][j];
    }

    public static void main(String[] args) {
        n=4; dist=new int[n][n];
        int INF=99999;
        for (int[] r:dist) Arrays.fill(r,INF);
        for (int i=0;i<n;i++) dist[i][i]=0;
        int[][] edges={{0,1,1},{1,2,1},{2,3,1},{3,0,1},{0,2,2}};
        int totalEdgeWeight=0;
        for (int[] e:edges) { dist[e[0]][e[1]]=e[2]; dist[e[1]][e[0]]=e[2]; totalEdgeWeight+=e[2]; }
        floyd();
        // Find odd-degree vertices
        int[] deg=new int[n];
        for (int[] e:edges) { deg[e[0]]++; deg[e[1]]++; }
        List<Integer> odd=new ArrayList<>();
        for (int i=0;i<n;i++) if (deg[i]%2!=0) odd.add(i);
        System.out.println("Odd degree vertices: " + odd);
        System.out.println("Total edge weight: " + totalEdgeWeight);
        // Minimum weight perfect matching on odd vertices gives extra weight
        System.out.println("(Minimum perfect matching on odd vertices gives the extra traversal cost)");
    }
}
