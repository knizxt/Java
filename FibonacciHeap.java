import java.util.*;

// Simplified Fibonacci Heap (full implementation sketch)
public class FibonacciHeap {
    static class Node {
        int key, degree; boolean mark;
        Node child, left, right, parent;
        Node(int k) { key=k; left=right=this; }
    }

    Node min=null; int size=0;

    void insert(Node n) {
        if (min==null) { min=n; } else {
            // add n to root list
            n.right=min.right; n.left=min; min.right.left=n; min.right=n;
            if (n.key<min.key) min=n;
        }
        size++;
    }

    Node getMin() { return min; }

    public static void main(String[] args) {
        FibonacciHeap fh=new FibonacciHeap();
        Node a=new Node(3), b=new Node(1), c=new Node(5);
        fh.insert(a); fh.insert(b); fh.insert(c);
        System.out.println("Min: " + fh.getMin().key); // 1
        System.out.println("(Full decrease-key and extract-min operations form the complete structure)");
    }
}
