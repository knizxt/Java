import java.util.*;
public class PointInPolygon {
    // Ray casting algorithm
    static boolean inside(int[][] poly, int x, int y) {
        int n=poly.length; boolean res=false;
        for (int i=0,j=n-1;i<n;j=i++) {
            if (((poly[i][1]>y)!=(poly[j][1]>y))&&(x<(long)(poly[j][0]-poly[i][0])*(y-poly[i][1])/(poly[j][1]-poly[i][1])+poly[i][0]))
                res=!res;
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] poly={{0,0},{10,0},{10,10},{0,10}};
        System.out.println("(5,5) inside: "+inside(poly,5,5)); // true
        System.out.println("(15,5) inside: "+inside(poly,15,5)); // false
    }
}
