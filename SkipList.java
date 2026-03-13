import java.util.*;

public class SkipList {
    static final int MAX_LEVEL = 16;
    static final double P = 0.5;
    static Random rnd = new Random();

    static class Node {
        int key; Node[] next;
        Node(int k, int lvl) { key=k; next=new Node[lvl+1]; }
    }

    Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    int level = 0;

    int randomLevel() {
        int lvl=0; while (rnd.nextDouble()<P && lvl<MAX_LEVEL) lvl++; return lvl;
    }

    void insert(int key) {
        Node[] update = new Node[MAX_LEVEL+1]; Node cur=head;
        for (int i=level;i>=0;i--) {
            while (cur.next[i]!=null && cur.next[i].key<key) cur=cur.next[i];
            update[i]=cur;
        }
        int lvl=randomLevel();
        if (lvl>level) { for (int i=level+1;i<=lvl;i++) update[i]=head; level=lvl; }
        Node n=new Node(key,lvl);
        for (int i=0;i<=lvl;i++) { n.next[i]=update[i].next[i]; update[i].next[i]=n; }
    }

    boolean search(int key) {
        Node cur=head;
        for (int i=level;i>=0;i--) while (cur.next[i]!=null&&cur.next[i].key<key) cur=cur.next[i];
        cur=cur.next[0];
        return cur!=null && cur.key==key;
    }

    public static void main(String[] args) {
        SkipList sl = new SkipList();
        sl.insert(3); sl.insert(6); sl.insert(7); sl.insert(9); sl.insert(12);
        System.out.println("Search 6: " + sl.search(6));  // true
        System.out.println("Search 5: " + sl.search(5));  // false
    }
}
