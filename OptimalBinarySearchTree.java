import java.util.*;
public class OptimalBinarySearchTree {
    static double optBST(double[] p, int n) {
        double[][] e=new double[n+2][n+1], w=new double[n+2][n+1];
        for (int i=1;i<=n+1;i++) { e[i][i-1]=0; w[i][i-1]=0; }
        for (int len=1;len<=n;len++) for (int i=1;i<=n-len+1;i++) {
            int j=i+len-1; e[i][j]=Double.MAX_VALUE; w[i][j]=w[i][j-1]+p[j-1];
            for (int r=i;r<=j;r++) {
                double t = e[i][r-1]+e[r+1][j]+w[i][j];
                if (t<e[i][j]) e[i][j]=t;
            }
        }
        return e[1][n];
    }
    public static void main(String[] args) {
        double[] p={0.25,0.2,0.05,0.2,0.3}; // p[i] = probability of key i+1
        System.out.printf("Optimal BST cost: %.4f%n", optBST(p, p.length));
    }
}
