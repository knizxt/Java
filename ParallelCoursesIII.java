import java.util.*;
public class ParallelCoursesIII {
    static int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] next=new ArrayList[n+1];
        int[] indeg=new int[n+1];
        for (int i=1;i<=n;i++) next[i]=new ArrayList<>();
        for (int[] r:relations) { next[r[0]].add(r[1]); indeg[r[1]]++; }
        int[] dp=new int[n+1];
        Queue<Integer> q=new LinkedList<>();
        for (int i=1;i<=n;i++) if (indeg[i]==0) { q.add(i); dp[i]=time[i-1]; }
        while (!q.isEmpty()) {
            int u=q.poll();
            for (int v:next[u]) {
                dp[v]=Math.max(dp[v],dp[u]+time[v-1]);
                if (--indeg[v]==0) q.add(v);
            }
        }
        int ans=0; for (int x:dp) ans=Math.max(ans,x);
        return ans;
    }
    public static void main(String[] args) {
        int[] time={1,2,3,4,5};
        int[][] rel={{2,1},{3,1},{4,2},{4,3},{5,4}};
        System.out.println("Min time: " + minimumTime(5,rel,time));
    }
}
