import java.util.*;

// Persistent Segment Tree - supports version queries
public class PersistentSegmentTree {
    static class Node { int sum; Node left, right; }

    static Node[] roots = new Node[10005]; static int ver=0;

    static Node build(int l, int r) {
        Node n=new Node(); if (l==r) return n;
        int mid=(l+r)/2; n.left=build(l,mid); n.right=build(mid+1,r); return n;
    }

    static Node update(Node prev, int l, int r, int idx, int val) {
        Node n=new Node(); n.left=prev.left; n.right=prev.right; n.sum=prev.sum+val;
        if (l==r) return n;
        int mid=(l+r)/2;
        if (idx<=mid) n.left=update(prev.left,l,mid,idx,val);
        else n.right=update(prev.right,mid+1,r,idx,val);
        return n;
    }

    static int query(Node n, int l, int r, int ql, int qr) {
        if (n==null) return 0;
        if (ql<=l&&r<=qr) return n.sum;
        int mid=(l+r)/2, s=0;
        if (ql<=mid) s+=query(n.left,l,mid,ql,qr);
        if (qr>mid) s+=query(n.right,mid+1,r,ql,qr);
        return s;
    }

    public static void main(String[] args) {
        int n=8;
        roots[0]=build(0,n-1);
        int[] arr={1,2,3,4,5,6,7,8};
        for (int i=0;i<n;i++) roots[i+1]=update(roots[i],0,n-1,i,arr[i]);
        System.out.println("Version 3, sum [0,2]: "+query(roots[3],0,n-1,0,2)); // 1+2+3=6
        System.out.println("Version 5, sum [0,4]: "+query(roots[5],0,n-1,0,4)); // 1+2+3+4+5=15
    }
}
