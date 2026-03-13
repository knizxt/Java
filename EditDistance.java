import java.util.*;
public class EditDistance {
    static int edit(String a, String b) {
        int m=a.length(), n=b.length();
        int[][] dp=new int[m+1][n+1];
        for (int i=0;i<=m;i++) dp[i][0]=i;
        for (int j=0;j<=n;j++) dp[0][j]=j;
        for (int i=1;i<=m;i++) for (int j=1;j<=n;j++)
            dp[i][j] = a.charAt(i-1)==b.charAt(j-1) ? dp[i-1][j-1] : 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
        return dp[m][n];
    }
    public static void main(String[] args) {
        System.out.println("Edit distance (sunday->saturday): " + edit("sunday","saturday"));
    }
}
