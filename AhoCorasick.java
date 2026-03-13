import java.util.*;
public class AhoCorasick {
    static int[][] go; static int[] fail,out; static int size=1;
    static final int ALPHA=26;
    static void insert(String s, int idx) {
        int cur=0;
        for (char c:s.toCharArray()) {
            int ch=c-'a';
            if (go[cur][ch]==0) go[cur][ch]=size++;
            cur=go[cur][ch];
        }
        out[cur]|=(1<<idx);
    }
    static void build() {
        Queue<Integer> q=new LinkedList<>();
        for (int c=0;c<ALPHA;c++) { if (go[0][c]!=0) { fail[go[0][c]]=0; q.add(go[0][c]); } }
        while (!q.isEmpty()) {
            int u=q.poll();
            out[u]|=out[fail[u]];
            for (int c=0;c<ALPHA;c++) {
                if (go[u][c]!=0) { fail[go[u][c]]=go[fail[u]][c]; q.add(go[u][c]); }
                else go[u][c]=go[fail[u]][c];
            }
        }
    }
    static void search(String txt, String[] patterns) {
        int cur=0;
        for (int i=0;i<txt.length();i++) {
            cur=go[cur][txt.charAt(i)-'a'];
            if (out[cur]!=0) for (int k=0;k<patterns.length;k++) if ((out[cur]&(1<<k))!=0) System.out.println("Pattern '"+patterns[k]+"' found ending at "+i);
        }
    }
    public static void main(String[] args) {
        String[] pats={"he","she","his","hers"}; int n=6;
        go=new int[500][ALPHA]; fail=new int[500]; out=new int[500];
        for (int i=0;i<pats.length;i++) insert(pats[i],i);
        build(); search("ahishers",pats);
    }
}
