import java.util.*;

// Tutte Polynomial via deletion-contraction (exponential, demonstration only)
public class TuttePolynomial {
    static Map<String,long[][]> memo = new HashMap<>();

    // edges as list of [u,v] pairs
    static long[][] tutte(List<int[]> edges, int n, int x, int y) {
        String key = edges.toString();
        if (memo.containsKey(key)) return memo.get(key);
        long[][] res = new long[][]{{1}};
        if (edges.isEmpty()) { memo.put(key,res); return res; }
        int[] e = edges.get(0);
        // check if bridge or loop (simplified)
        boolean loop = (e[0]==e[1]);
        List<int[]> rest = new ArrayList<>(edges.subList(1,edges.size()));
        if (loop) {
            long[][] sub = tutte(rest,n,x,y);
            res = scalarMul(sub, y);
        } else {
            // deletion
            long[][] del = tutte(rest,n,x,y);
            // contraction (merge e[0] and e[1])
            List<int[]> cont = new ArrayList<>();
            for (int[] ed : rest) {
                int a=ed[0]==e[1]?e[0]:ed[0], b=ed[1]==e[1]?e[0]:ed[1];
                cont.add(new int[]{a,b});
            }
            long[][] con = tutte(cont,n-1,x,y);
            res = add(del, con);
        }
        memo.put(key, res);
        return res;
    }

    static long[][] scalarMul(long[][] m, int s) { return new long[][]{{m[0][0]*s}}; }
    static long[][] add(long[][] a, long[][] b) { return new long[][]{{a[0][0]+b[0][0]}}; }

    public static void main(String[] args) {
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{0,1}); edges.add(new int[]{1,2}); edges.add(new int[]{0,2});
        long[][] T = tutte(edges, 3, 2, 2);
        System.out.println("Tutte polynomial value at (2,2): " + T[0][0]);
    }
}
