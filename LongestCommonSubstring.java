import java.util.*;
public class LongestCommonSubstring {
    static String lcs(String a, String b) {
        int m=a.length(),n=b.length(),maxLen=0,end=0;
        int[][] dp=new int[m+1][n+1];
        for (int i=1;i<=m;i++) for (int j=1;j<=n;j++) {
            if (a.charAt(i-1)==b.charAt(j-1)) {
                dp[i][j]=dp[i-1][j-1]+1;
                if (dp[i][j]>maxLen) { maxLen=dp[i][j]; end=i; }
            }
        }
        return a.substring(end-maxLen, end);
    }
    public static void main(String[] args) {
        System.out.println("LCS of substrings: " + lcs("OldSite:GeeksforGeeks.org","NewSite:GeeksQuiz.com"));
    }
}
