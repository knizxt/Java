import java.util.*;
public class TextJustification {
    static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res=new ArrayList<>(); int i=0,n=words.length;
        while (i<n) {
            int j=i,len=0;
            while (j<n&&len+words[j].length()+(j>i?1:0)<=maxWidth) { len+=words[j].length()+(j>i?1:0); j++; }
            int numSpaces=maxWidth-len+(j-i-1), extra=j-i>1?numSpaces%(j-i-1):0, gaps=j-i-1;
            StringBuilder sb=new StringBuilder();
            for (int k=i;k<j;k++) {
                sb.append(words[k]);
                if (k<j-1) {
                    int sp=gaps>0?numSpaces/(j-i-1)+(k-i<extra?1:0):maxWidth-len;
                    for (int s=0;s<=sp;s++) sb.append(' ');
                }
            }
            while (sb.length()<maxWidth) sb.append(' ');
            res.add(sb.toString()); i=j;
        }
        return res;
    }
    public static void main(String[] args) {
        String[] words={"This","is","an","example","of","text","justification."};
        for (String line:fullJustify(words,16)) System.out.println("|"+line+"|");
    }
}
