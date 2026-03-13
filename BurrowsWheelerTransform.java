import java.util.*;
public class BurrowsWheelerTransform {
    static String bwt(String s) {
        s+="$"; int n=s.length();
        String[] rotations=new String[n];
        for (int i=0;i<n;i++) rotations[i]=s.substring(i)+s.substring(0,i);
        Arrays.sort(rotations);
        StringBuilder sb=new StringBuilder();
        for (String r:rotations) sb.append(r.charAt(n-1));
        return sb.toString();
    }
    static String ibwt(String t) {
        int n=t.length(); String[] table=new String[n];
        for (int i=0;i<n;i++) table[i]="";
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) table[j]=t.charAt(j)+table[j];
            Arrays.sort(table);
        }
        for (String row:table) if (row.endsWith("$")) return row.substring(0,row.length()-1);
        return "";
    }
    public static void main(String[] args) {
        String original="banana"; String transformed=bwt(original);
        System.out.println("BWT: "+transformed);
        System.out.println("Inverse BWT: "+ibwt(transformed));
    }
}
