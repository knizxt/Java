import java.util.*;
public class PalindromePairs {
    static List<List<Integer>> palindromePairs(String[] words) {
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i<words.length;i++) map.put(words[i],i);
        List<List<Integer>> res=new ArrayList<>();
        for (int i=0;i<words.length;i++) {
            for (int j=0;j<=words[i].length();j++) {
                String l=words[i].substring(0,j), r=words[i].substring(j);
                if (isPalin(l)) {
                    String revR=new StringBuilder(r).reverse().toString();
                    if (map.containsKey(revR)&&map.get(revR)!=i) res.add(Arrays.asList(map.get(revR),i));
                }
                if (r.length()!=0&&isPalin(r)) {
                    String revL=new StringBuilder(l).reverse().toString();
                    if (map.containsKey(revL)&&map.get(revL)!=i) res.add(Arrays.asList(i,map.get(revL)));
                }
            }
        }
        return res;
    }
    static boolean isPalin(String s) { int i=0,j=s.length()-1; while (i<j) if (s.charAt(i++)!=s.charAt(j--)) return false; return true; }
    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}
