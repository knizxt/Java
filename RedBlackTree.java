import java.util.*;

// Red-Black Tree insertion
public class RedBlackTree {
    static final boolean RED=true, BLACK=false;

    static class Node {
        int key; boolean color=RED; Node left,right,parent;
        Node(int k) { key=k; }
    }

    Node root=null;
    static final Node NIL=new Node(0); static { NIL.color=BLACK; }

    void insert(int key) {
        Node z=new Node(key); z.left=NIL; z.right=NIL;
        Node y=null; Node x=root;
        while (x!=null&&x!=NIL) { y=x; x=key<x.key?x.left:x.right; }
        z.parent=y;
        if (y==null) root=z;
        else if (key<y.key) y.left=z; else y.right=z;
        if (z.parent==null) { z.color=BLACK; return; }
        if (z.parent.parent==null) return;
        fixInsert(z);
    }

    void fixInsert(Node k) {
        while (k.parent!=null&&k.parent.color==RED) {
            Node u;
            if (k.parent==k.parent.parent.right) {
                u=k.parent.parent.left;
                if (u!=null&&u.color==RED) { u.color=BLACK; k.parent.color=BLACK; k.parent.parent.color=RED; k=k.parent.parent; }
                else { if (k==k.parent.left) { k=k.parent; rotateRight(k); } k.parent.color=BLACK; k.parent.parent.color=RED; rotateLeft(k.parent.parent); }
            } else {
                u=k.parent.parent.right;
                if (u!=null&&u.color==RED) { u.color=BLACK; k.parent.color=BLACK; k.parent.parent.color=RED; k=k.parent.parent; }
                else { if (k==k.parent.right) { k=k.parent; rotateLeft(k); } k.parent.color=BLACK; k.parent.parent.color=RED; rotateRight(k.parent.parent); }
            }
            if (k==root) break;
        }
        root.color=BLACK;
    }

    void rotateLeft(Node x) { Node y=x.right; x.right=y.left; if (y.left!=null) y.left.parent=x; y.parent=x.parent; if (x.parent==null) root=y; else if (x==x.parent.left) x.parent.left=y; else x.parent.right=y; y.left=x; x.parent=y; }
    void rotateRight(Node x) { Node y=x.left; x.left=y.right; if (y.right!=null) y.right.parent=x; y.parent=x.parent; if (x.parent==null) root=y; else if (x==x.parent.right) x.parent.right=y; else x.parent.left=y; y.right=x; x.parent=y; }

    void inorder(Node n) { if (n==null) return; inorder(n.left); System.out.print(n.key+"("+(n.color==RED?"R":"B")+") "); inorder(n.right); }

    public static void main(String[] args) {
        RedBlackTree rbt=new RedBlackTree();
        for (int k:new int[]{8,18,5,15,17,25,40,80}) rbt.insert(k);
        rbt.inorder(rbt.root); System.out.println();
    }
}
