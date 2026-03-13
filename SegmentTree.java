import java.util.*;

// Segment Tree for Range Sum Query with Lazy Propagation
public class SegmentTree {
    int[] tree, lazy;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4*n];
        lazy = new int[4*n];
        build(arr, 0, 0, n-1);
    }

    void build(int[] arr, int node, int s, int e) {
        if (s == e) { tree[node] = arr[s]; return; }
        int mid = (s+e)/2;
        build(arr, 2*node+1, s, mid);
        build(arr, 2*node+2, mid+1, e);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    void updateRange(int node, int s, int e, int l, int r, int val) {
        if (lazy[node] != 0) {
            tree[node] += (e-s+1)*lazy[node];
            if (s != e) { lazy[2*node+1] += lazy[node]; lazy[2*node+2] += lazy[node]; }
            lazy[node] = 0;
        }
        if (r < s || e < l) return;
        if (l <= s && e <= r) {
            tree[node] += (e-s+1)*val;
            if (s != e) { lazy[2*node+1] += val; lazy[2*node+2] += val; }
            return;
        }
        int mid = (s+e)/2;
        updateRange(2*node+1, s, mid, l, r, val);
        updateRange(2*node+2, mid+1, e, l, r, val);
        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    int query(int node, int s, int e, int l, int r) {
        if (lazy[node] != 0) {
            tree[node] += (e-s+1)*lazy[node];
            if (s != e) { lazy[2*node+1] += lazy[node]; lazy[2*node+2] += lazy[node]; }
            lazy[node] = 0;
        }
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return tree[node];
        int mid = (s+e)/2;
        return query(2*node+1, s, mid, l, r) + query(2*node+2, mid+1, e, l, r);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,11};
        SegmentTree st = new SegmentTree(arr);
        System.out.println("Sum [1,3]: " + st.query(0, 0, 5, 1, 3)); // 15
        st.updateRange(0, 0, 5, 1, 3, 10);
        System.out.println("Sum [1,3] after +10: " + st.query(0, 0, 5, 1, 3)); // 45
    }
}
