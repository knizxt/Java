import java.util.*;

// Treap - Randomized BST combining BST and heap properties
public class Treap {
    static class Node {
        int key, priority; Node left, right;
        Node(int k) { key=k; priority=(int)(Math.random()*Integer.MAX_VALUE); }
    }

    Node root;

    Node rotateRight(Node y) { Node x=y.left; y.left=x.right; x.right=y; return x; }
    Node rotateLeft(Node x) { Node y=x.right; x.right=y.left; y.left=x; return y; }

    Node insert(Node n, int key) {
        if (n==null) return new Node(key);
        if (key<=n.key) {
            n.left=insert(n.left,key);
            if (n.left.priority>n.priority) n=rotateRight(n);
        } else {
            n.right=insert(n.right,key);
            if (n.right.priority>n.priority) n=rotateLeft(n);
        }
        return n;
    }

    boolean search(Node n, int key) {
        if (n==null) return false;
        if (key==n.key) return true;
        return key<n.key?search(n.left,key):search(n.right,key);
    }

    void inorder(Node n) { if (n==null) return; inorder(n.left); System.out.print(n.key+" "); inorder(n.right); }

    public static void main(String[] args) {
        Treap t=new Treap();
        for (int k:new int[]{5,3,7,1,4,6,8}) t.root=t.insert(t.root,k);
        System.out.print("Inorder: "); t.inorder(t.root); System.out.println();
        System.out.println("Search 4: " + t.search(t.root,4));
        System.out.println("Search 9: " + t.search(t.root,9));
    }
}
