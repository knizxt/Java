import java.util.*;
public class ClosestPairOfPoints {
    static double dist(int[][] p, int i, int j) { return Math.hypot(p[i][0]-p[j][0],p[i][1]-p[j][1]); }
    static double bruteForce(int[][] p, int l, int r) {
        double m=Double.MAX_VALUE; for (int i=l;i<r;i++) for (int j=i+1;j<=r;j++) m=Math.min(m,dist(p,i,j)); return m;
    }
    static double stripClosest(int[][] strip, double d) {
        double m=d; Arrays.sort(strip,Comparator.comparingInt(a->a[1]));
        for (int i=0;i<strip.length;i++) for (int j=i+1;j<strip.length&&strip[j][1]-strip[i][1]<m;j++) m=Math.min(m,Math.hypot(strip[i][0]-strip[j][0],strip[i][1]-strip[j][1]));
        return m;
    }
    static double closest(int[][] p, int l, int r) {
        if (r-l<=2) return bruteForce(p,l,r);
        int mid=(l+r)/2; int midX=p[mid][0];
        double d=Math.min(closest(p,l,mid),closest(p,mid+1,r));
        List<int[]> strip=new ArrayList<>();
        for (int i=l;i<=r;i++) if (Math.abs(p[i][0]-midX)<d) strip.add(p[i]);
        return Math.min(d,stripClosest(strip.toArray(new int[0][]),d));
    }
    public static void main(String[] args) {
        int[][] pts={{2,3},{12,30},{40,50},{5,1},{12,10},{3,4}};
        Arrays.sort(pts,Comparator.comparingInt(a->a[0]));
        System.out.printf("Closest pair distance: %.4f%n",closest(pts,0,pts.length-1));
    }
}
