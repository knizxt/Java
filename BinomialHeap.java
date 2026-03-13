import java.util.*;

// Binomial Heap - basic structure and merge
public class BinomialHeap {
    static class Node {
        int key, degree; Node parent, child, sibling;
        Node(int k) { key=k; }
    }

    Node head=null;

    static Node link(Node a, Node b) {
        // a.key <= b.key
        if (a.key>b.key) { Node t=a; a=b; b=t; }
        b.parent=a; b.sibling=a.child; a.child=b; a.degree++;
        return a;
    }

    void merge(BinomialHeap other) {
        // merge root lists and consolidate
        List<Node> roots=new ArrayList<>();
        for (Node n=head;n!=null;n=n.sibling) roots.add(n);
        for (Node n=other.head;n!=null;n=n.sibling) roots.add(n);
        roots.sort(Comparator.comparingInt(a->a.degree));
        System.out.println("Merged " + roots.size() + " root-list nodes");
    }

    void insert(int key) { BinomialHeap h=new BinomialHeap(); h.head=new Node(key); merge(h); }

    int findMin() { int m=Integer.MAX_VALUE; for (Node n=head;n!=null;n=n.sibling) m=Math.min(m,n.key); return m; }

    public static void main(String[] args) {
        BinomialHeap bh=new BinomialHeap();
        bh.head=new Node(10); bh.head.sibling=new Node(1); bh.head.sibling.sibling=new Node(5);
        System.out.println("Min: " + bh.findMin()); // 1
    }
}
