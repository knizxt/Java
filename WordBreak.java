import java.util.*;
public class WordBreak {
    static boolean wordBreak(String s, List<String> dict) {
        Set<String> wordSet = new HashSet<>(dict);
        int n=s.length(); boolean[] dp=new boolean[n+1]; dp[0]=true;
        for (int i=1;i<=n;i++)
            for (int j=0;j<i;j++)
                if (dp[j] && wordSet.contains(s.substring(j,i))) { dp[i]=true; break; }
        return dp[n];
    }
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("leet","code","lee","t");
        System.out.println(wordBreak("leetcode", dict)); // true
        System.out.println(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat"))); // false
    }
}
