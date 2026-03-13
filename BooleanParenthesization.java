import java.util.*;
public class BooleanParenthesization {
    static long countWays(String sym, String op) {
        int n=sym.length();
        long[][] T=new long[n][n], F=new long[n][n];
        for (int i=0;i<n;i++) { T[i][i]=sym.charAt(i)=='T'?1:0; F[i][i]=sym.charAt(i)=='F'?1:0; }
        for (int len=2;len<=n;len++) for (int i=0;i<=n-len;i++) {
            int j=i+len-1;
            for (int k=i;k<j;k++) {
                long lt=T[i][k],lf=F[i][k],rt=T[k+1][j],rf=F[k+1][j];
                char o=op.charAt(k);
                if (o=='&') { T[i][j]+=lt*rt; F[i][j]+=lf*rt+lt*rf+lf*rf; }
                else if (o=='|') { T[i][j]+=lt*rt+lt*rf+lf*rt; F[i][j]+=lf*rf; }
                else { T[i][j]+=lt*rf+lf*rt; F[i][j]+=lt*rt+lf*rf; }
            }
        }
        return T[0][n-1];
    }
    public static void main(String[] args) {
        System.out.println("Ways: " + countWays("TTFT","^&|")); // 4
    }
}
