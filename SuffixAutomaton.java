import java.util.*;
// Suffix Automaton for all distinct substrings
public class SuffixAutomaton {
    static class State { int len,link; Map<Character,Integer> next=new HashMap<>(); }
    static List<State> sa=new ArrayList<>(); static int last;

    static void init() { sa.add(new State()); sa.get(0).link=-1; last=0; }

    static void extend(char c) {
        int cur=sa.size(); sa.add(new State()); sa.get(cur).len=sa.get(last).len+1;
        int p=last;
        while (p!=-1&&!sa.get(p).next.containsKey(c)) { sa.get(p).next.put(c,cur); p=sa.get(p).link; }
        if (p==-1) sa.get(cur).link=0;
        else {
            int q=sa.get(p).next.get(c);
            if (sa.get(p).len+1==sa.get(q).len) sa.get(cur).link=q;
            else {
                int clone=sa.size(); sa.add(new State()); sa.get(clone).len=sa.get(p).len+1;
                sa.get(clone).link=sa.get(q).link; sa.get(clone).next=new HashMap<>(sa.get(q).next);
                while (p!=-1&&sa.get(p).next.getOrDefault(c,-1)==q) { sa.get(p).next.put(c,clone); p=sa.get(p).link; }
                sa.get(q).link=clone; sa.get(cur).link=clone;
            }
        }
        last=cur;
    }

    public static void main(String[] args) {
        init(); String s="abcbc";
        for (char c:s.toCharArray()) extend(c);
        System.out.println("SAM states for '"+s+"': "+sa.size());
    }
}
