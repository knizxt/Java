import java.util.*;
public class ConvexHull {
    static long cross(int[] O, int[] A, int[] B) { return (long)(A[0]-O[0])*(B[1]-O[1])-(long)(A[1]-O[1])*(B[0]-O[0]); }
    static List<int[]> convexHull(int[][] pts) {
        int n=pts.length; if (n<3) return Arrays.asList(pts);
        Arrays.sort(pts,(a,b)->a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]);
        int[][] hull=new int[2*n][2]; int k=0;
        for (int i=0;i<n;i++) { while (k>=2&&cross(hull[k-2],hull[k-1],pts[i])<=0) k--; hull[k++]=pts[i]; }
        for (int i=n-2,t=k+1;i>=0;i--) { while (k>=t&&cross(hull[k-2],hull[k-1],pts[i])<=0) k--; hull[k++]=pts[i]; }
        List<int[]> res=new ArrayList<>(); for (int i=0;i<k-1;i++) res.add(hull[i]); return res;
    }
    public static void main(String[] args) {
        int[][] pts={{0,3},{1,1},{2,2},{4,4},{0,0},{1,2},{3,1},{3,3}};
        List<int[]> hull=convexHull(pts);
        System.out.println("Convex hull points:");
        for (int[] p:hull) System.out.println("  "+Arrays.toString(p));
    }
}
