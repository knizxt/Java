import java.util.*;
public class Combinations {
    static void combine(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size()==k) { result.add(new ArrayList<>(current)); return; }
        for (int i=start;i<=n-(k-current.size())+1;i++) {
            current.add(i); combine(n,k,i+1,current,result); current.remove(current.size()-1);
        }
    }
    public static void main(String[] args) {
        List<List<Integer>> res=new ArrayList<>();
        combine(5,3,1,new ArrayList<>(),res);
        System.out.println("C(5,3): "+res.size()+" combinations");
        res.forEach(System.out::println);
    }
}
