import java.util.*;
// already written as TravelingSalesmanExact.java - this version includes path reconstruction
public class BitmaskTSP {
    public static void main(String[] args) {
        int[][] d = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
        int n=4, FULL=(1<<n)-1;
        int[][] dp=new int[1<<n][n]; int[][] nxt=new int[1<<n][n];
        for (int[] r:dp) Arrays.fill(r,Integer.MAX_VALUE/2);
        for (int[] r:nxt) Arrays.fill(r,-1);
        dp[1][0]=0;
        for (int mask=1;mask<(1<<n);mask++)
            for (int u=0;u<n;u++) {
                if ((mask&(1<<u))==0 || dp[mask][u]==Integer.MAX_VALUE/2) continue;
                for (int v=0;v<n;v++) {
                    if ((mask&(1<<v))!=0) continue;
                    int nm=mask|(1<<v), cost=dp[mask][u]+d[u][v];
                    if (cost<dp[nm][v]) { dp[nm][v]=cost; nxt[mask][u]=v; }
                }
            }
        int ans=Integer.MAX_VALUE, last=-1;
        for (int u=1;u<n;u++) if (dp[FULL][u]+d[u][0]<ans) { ans=dp[FULL][u]+d[u][0]; last=u; }
        // reconstruct path
        List<Integer> path=new ArrayList<>(); path.add(0);
        int mask=1, cur=0;
        while (nxt[mask][cur]!=-1) {
            int nx=nxt[mask][cur]; path.add(nx); mask|=(1<<nx); cur=nx;
        }
        path.add(0);
        System.out.println("TSP cost: "+ans+", path: "+path);
    }
}
