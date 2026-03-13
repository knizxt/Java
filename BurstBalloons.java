import java.util.*;
// interval DP - last balloon to burst in range
public class BurstBalloons {
    static int maxCoins(int[] nums) {
        int n=nums.length;
        int[] arr=new int[n+2]; arr[0]=1; arr[n+1]=1;
        for (int i=0;i<n;i++) arr[i+1]=nums[i];
        int m=n+2;
        int[][] dp=new int[m][m];
        for (int len=2;len<m;len++)
            for (int i=0;i<m-len;i++) {
                int j=i+len;
                for (int k=i+1;k<j;k++)
                    dp[i][j]=Math.max(dp[i][j], dp[i][k]+arr[i]*arr[k]*arr[j]+dp[k][j]);
            }
        return dp[0][m-1];
    }
    public static void main(String[] args) {
        System.out.println("Max coins: " + maxCoins(new int[]{3,1,5,8})); // 167
    }
}
