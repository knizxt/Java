import java.util.*;
public class WordSearchII {
    static class TrieNode { TrieNode[] ch=new TrieNode[26]; String word; }
    static int[] dx={0,0,1,-1}, dy={1,-1,0,0};

    static void dfs(char[][] board,TrieNode node,int r,int c,List<String> res) {
        char cur=board[r][c]; if (cur=='#'||node.ch[cur-'a']==null) return;
        node=node.ch[cur-'a'];
        if (node.word!=null) { res.add(node.word); node.word=null; }
        board[r][c]='#';
        for (int d=0;d<4;d++) { int nr=r+dx[d],nc=c+dy[d]; if (nr>=0&&nr<board.length&&nc>=0&&nc<board[0].length) dfs(board,node,nr,nc,res); }
        board[r][c]=cur;
    }

    static List<String> findWords(char[][] board,String[] words) {
        TrieNode root=new TrieNode();
        for (String w:words) { TrieNode cur=root; for (char c:w.toCharArray()) { int i=c-'a'; if (cur.ch[i]==null) cur.ch[i]=new TrieNode(); cur=cur.ch[i]; } cur.word=w; }
        List<String> res=new ArrayList<>();
        for (int r=0;r<board.length;r++) for (int c=0;c<board[0].length;c++) dfs(board,root,r,c,res);
        return res;
    }

    public static void main(String[] args) {
        char[][] b={{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        System.out.println(findWords(b,new String[]{"oath","pea","eat","rain"}));
    }
}
