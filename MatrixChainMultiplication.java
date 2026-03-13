import java.util.*;
public class MatrixChainMultiplication {
    static int mcm(int[] p) {
        int n=p.length-1;
        int[][] dp=new int[n][n];
        for (int len=2;len<=n;len++)
            for (int i=0;i<=n-len;i++) {
                int j=i+len-1; dp[i][j]=Integer.MAX_VALUE;
                for (int k=i;k<j;k++)
                    dp[i][j]=Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+p[i]*p[k+1]*p[j+1]);
            }
        return dp[0][n-1];
    }
    public static void main(String[] args) {
        int[] p={1,2,3,4,3};
        System.out.println("Min multiplications: " + mcm(p));
    }
}
