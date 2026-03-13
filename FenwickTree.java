import java.util.*;

// Binary Indexed Tree (Fenwick Tree) for prefix sums
public class FenwickTree {
    int[] bit;
    int n;

    FenwickTree(int n) { this.n = n; bit = new int[n+1]; }

    void update(int i, int delta) { for (++i; i<=n; i+=i&(-i)) bit[i]+=delta; }

    int query(int i) { int s=0; for (++i; i>0; i-=i&(-i)) s+=bit[i]; return s; }

    int rangeQuery(int l, int r) { return query(r) - (l>0 ? query(l-1) : 0); }

    public static void main(String[] args) {
        int[] arr = {2,1,1,3,2,3,4,5,6,7,8,9};
        FenwickTree ft = new FenwickTree(arr.length);
        for (int i=0;i<arr.length;i++) ft.update(i, arr[i]);
        System.out.println("Prefix sum [0,5]: " + ft.rangeQuery(0,5)); // 12
        ft.update(3, 6);
        System.out.println("After update, range [0,5]: " + ft.rangeQuery(0,5)); // 18
    }
}
