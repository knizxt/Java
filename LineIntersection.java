import java.util.*;
public class LineIntersection {
    static long cross(long[] O, long[] A, long[] B) { return (A[0]-O[0])*(B[1]-O[1])-(A[1]-O[1])*(B[0]-O[0]); }
    static boolean onSegment(long[] p, long[] q, long[] r) { return Math.min(p[0],r[0])<=q[0]&&q[0]<=Math.max(p[0],r[0])&&Math.min(p[1],r[1])<=q[1]&&q[1]<=Math.max(p[1],r[1]); }
    static boolean intersects(long[] p1,long[] p2,long[] p3,long[] p4) {
        long d1=cross(p3,p4,p1),d2=cross(p3,p4,p2),d3=cross(p1,p2,p3),d4=cross(p1,p2,p4);
        if (((d1>0&&d2<0)||(d1<0&&d2>0))&&((d3>0&&d4<0)||(d3<0&&d4>0))) return true;
        if (d1==0&&onSegment(p3,p1,p4)) return true;
        if (d2==0&&onSegment(p3,p2,p4)) return true;
        if (d3==0&&onSegment(p1,p3,p2)) return true;
        if (d4==0&&onSegment(p1,p4,p2)) return true;
        return false;
    }
    public static void main(String[] args) {
        long[] p1={1,1},p2={10,1},p3={1,2},p4={10,2};
        System.out.println("Parallel: "+intersects(p1,p2,p3,p4)); // false
        long[] p5={10,0},p6={0,10},p7={0,0},p8={10,10};
        System.out.println("Crossing: "+intersects(p5,p6,p7,p8)); // true
    }
}
