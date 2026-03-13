import java.util.*;
public class CountDifferentPalindromicSubsequences {
    static final int MOD = 1_000_000_007;
    static int countPalindromicSubsequences(String s) {
        int n=s.length();
        long[][] dp=new long[n][n];
        for (int i=0;i<n;i++) dp[i][i]=1;
        for (int len=2;len<=n;len++) for (int i=0;i<=n-len;i++) {
            int j=i+len-1;
            if (s.charAt(i)==s.charAt(j)) {
                int lo=i+1, hi=j-1;
                while (lo<=hi && s.charAt(lo)!=s.charAt(i)) lo++;
                while (lo<=hi && s.charAt(hi)!=s.charAt(j)) hi--;
                if (lo>hi) dp[i][j]=(dp[i+1][j-1]*2+2)%MOD;
                else if (lo==hi) dp[i][j]=(dp[i+1][j-1]*2+1)%MOD;
                else dp[i][j]=(dp[i+1][j-1]*2-dp[lo+1][hi-1]+MOD)%MOD;
            } else dp[i][j]=(dp[i+1][j]+dp[i][j-1]-dp[i+1][j-1]+MOD)%MOD;
        }
        return (int)dp[0][n-1];
    }
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb")); // 6
    }
}
