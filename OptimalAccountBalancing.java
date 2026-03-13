import java.util.*;
public class OptimalAccountBalancing {
    static int minTransfers(int[][] transactions) {
        Map<Integer,Integer> bal=new HashMap<>();
        for (int[] t:transactions) {
            bal.merge(t[0],-t[2],Integer::sum);
            bal.merge(t[1],t[2],Integer::sum);
        }
        List<Integer> debt=new ArrayList<>();
        for (int b:bal.values()) if (b!=0) debt.add(b);
        return dfs(debt, 0);
    }
    static int dfs(List<Integer> debt, int s) {
        while (s<debt.size() && debt.get(s)==0) s++;
        if (s==debt.size()) return 0;
        int res=Integer.MAX_VALUE;
        for (int i=s+1;i<debt.size();i++) {
            if ((long)debt.get(i)*debt.get(s)<0) {
                debt.set(i, debt.get(i)+debt.get(s));
                res=Math.min(res, 1+dfs(debt,s+1));
                debt.set(i, debt.get(i)-debt.get(s));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] t={{0,1,10},{2,0,5}};
        System.out.println("Min transactions: " + minTransfers(t)); // 2
    }
}
