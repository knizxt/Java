import java.util.*;

// AVL Self-balancing BST
public class AVLTree {
    static class Node {
        int key, height;
        Node left, right;
        Node(int k) { key=k; height=1; }
    }

    static int h(Node n) { return n==null?0:n.height; }
    static int bf(Node n) { return n==null?0:h(n.left)-h(n.right); }
    static void upd(Node n) { n.height=1+Math.max(h(n.left),h(n.right)); }

    static Node rotR(Node y) {
        Node x=y.left, T=x.right; x.right=y; y.left=T; upd(y); upd(x); return x;
    }
    static Node rotL(Node x) {
        Node y=x.right, T=y.left; y.left=x; x.right=T; upd(x); upd(y); return y;
    }

    static Node insert(Node node, int key) {
        if (node==null) return new Node(key);
        if (key<node.key) node.left=insert(node.left,key);
        else if (key>node.key) node.right=insert(node.right,key);
        else return node;
        upd(node);
        int b=bf(node);
        if (b>1&&key<node.left.key) return rotR(node);
        if (b<-1&&key>node.right.key) return rotL(node);
        if (b>1&&key>node.left.key) { node.left=rotL(node.left); return rotR(node); }
        if (b<-1&&key<node.right.key) { node.right=rotR(node.right); return rotL(node); }
        return node;
    }

    static void inorder(Node n) {
        if (n==null) return; inorder(n.left); System.out.print(n.key+" "); inorder(n.right);
    }

    public static void main(String[] args) {
        Node root=null;
        for (int k : new int[]{10,20,30,40,50,25}) root=insert(root,k);
        System.out.print("Inorder (should be sorted): "); inorder(root); System.out.println();
    }
}
