import java.util.*;
public class PolygonArea {
    // Shoelace formula for polygon area
    static double area(int[][] pts) {
        int n=pts.length; long area=0;
        for (int i=0;i<n;i++) {
            int j=(i+1)%n;
            area+=(long)pts[i][0]*pts[j][1];
            area-=(long)pts[j][0]*pts[i][1];
        }
        return Math.abs(area)/2.0;
    }
    public static void main(String[] args) {
        int[][] poly={{0,0},{4,0},{4,3},{0,3}};
        System.out.println("Area of rectangle: "+area(poly)); // 12.0
        int[][] tri={{0,0},{5,0},{0,5}};
        System.out.println("Area of triangle: "+area(tri)); // 12.5
    }
}
