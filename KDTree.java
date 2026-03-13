import java.util.*;

// 2D KD-Tree for nearest neighbor search
public class KDTree {
    static class Node {
        int[] point; Node left, right;
        Node(int[] p) { point=p; }
    }

    Node root;

    void insert(int[] point) { root=insertRec(root, point, 0); }

    Node insertRec(Node n, int[] p, int depth) {
        if (n==null) return new Node(p);
        int cd=depth%2;
        if (p[cd]<n.point[cd]) n.left=insertRec(n.left,p,depth+1);
        else n.right=insertRec(n.right,p,depth+1);
        return n;
    }

    double dist(int[] a, int[] b) { return Math.sqrt(Math.pow(a[0]-b[0],2)+Math.pow(a[1]-b[1],2)); }

    int[] nearest;
    double minDist;

    void search(Node n, int[] target, int depth) {
        if (n==null) return;
        double d=dist(n.point,target);
        if (d<minDist) { minDist=d; nearest=n.point; }
        int cd=depth%2;
        Node first=target[cd]<n.point[cd]?n.left:n.right;
        Node second=first==n.left?n.right:n.left;
        search(first,target,depth+1);
        if (Math.abs(target[cd]-n.point[cd])<minDist) search(second,target,depth+1);
    }

    int[] nearestNeighbor(int[] target) {
        nearest=null; minDist=Double.MAX_VALUE; search(root,target,0); return nearest;
    }

    public static void main(String[] args) {
        KDTree kdt=new KDTree();
        int[][] pts={{1,3},{5,4},{9,6},{4,7},{8,1},{7,2}};
        for (int[] p:pts) kdt.insert(p);
        int[] nn=kdt.nearestNeighbor(new int[]{9,2});
        System.out.println("Nearest to (9,2): "+Arrays.toString(nn)); // [8,1] or [7,2]
    }
}
