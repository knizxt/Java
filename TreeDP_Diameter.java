import java.util.*;
public class TreeDP_Diameter {
    static List<int[]>[] tree; // [node, weight]
    static int diameter = 0;

    static int dfs(int u, int parent) {
        int max1=0, max2=0;
        for (int[] e : tree[u]) {
            if (e[0]==parent) continue;
            int h = dfs(e[0], u) + e[1];
            if (h>=max1) { max2=max1; max1=h; }
            else if (h>max2) max2=h;
        }
        diameter = Math.max(diameter, max1+max2);
        return max1;
    }

    public static void main(String[] args) {
        int n=6;
        tree=new ArrayList[n]; for(int i=0;i<n;i++) tree[i]=new ArrayList<>();
        int[][] edges={{0,1,1},{0,2,2},{1,3,4},{1,4,3},{2,5,5}};
        for (int[] e:edges) { tree[e[0]].add(new int[]{e[1],e[2]}); tree[e[1]].add(new int[]{e[0],e[2]}); }
        dfs(0,-1);
        System.out.println("Tree diameter: " + diameter);
    }
}
