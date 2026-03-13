import java.util.*;

// QuadTree for spatial partitioning
public class QuadTree {
    static class Rect { double x,y,w,h; Rect(double x,double y,double w,double h){this.x=x;this.y=y;this.w=w;this.h=h;} boolean contains(double px,double py){return px>=x&&px<=x+w&&py>=y&&py<=y+h;} }
    static class Point { double x,y; Point(double x,double y){this.x=x;this.y=y;} }

    Rect boundary; int capacity; List<Point> points=new ArrayList<>();
    QuadTree ne,nw,se,sw; boolean divided=false;

    QuadTree(Rect b, int cap) { boundary=b; capacity=cap; }

    void subdivide() {
        double hw=boundary.w/2, hh=boundary.h/2;
        nw=new QuadTree(new Rect(boundary.x,boundary.y,hw,hh),capacity);
        ne=new QuadTree(new Rect(boundary.x+hw,boundary.y,hw,hh),capacity);
        sw=new QuadTree(new Rect(boundary.x,boundary.y+hh,hw,hh),capacity);
        se=new QuadTree(new Rect(boundary.x+hw,boundary.y+hh,hw,hh),capacity);
        divided=true;
    }

    boolean insert(Point p) {
        if (!boundary.contains(p.x,p.y)) return false;
        if (points.size()<capacity) { points.add(p); return true; }
        if (!divided) subdivide();
        return ne.insert(p)||nw.insert(p)||se.insert(p)||sw.insert(p);
    }

    public static void main(String[] args) {
        QuadTree qt=new QuadTree(new Rect(0,0,100,100),4);
        Random rnd=new Random(42);
        for (int i=0;i<20;i++) qt.insert(new Point(rnd.nextDouble()*100,rnd.nextDouble()*100));
        System.out.println("QuadTree built with 20 points successfully.");
    }
}
