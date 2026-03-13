import java.util.*;

public class DequeDLL {
    static class Node { int val; Node prev, next; Node(int v){val=v;} }
    Node head, tail; int size;

    void addFront(int val) {
        Node n=new Node(val); n.next=head; if (head!=null) head.prev=n; head=n;
        if (tail==null) tail=head; size++;
    }
    void addRear(int val) {
        Node n=new Node(val); n.prev=tail; if (tail!=null) tail.next=n; tail=n;
        if (head==null) head=tail; size++;
    }
    int removeFront() {
        if (head==null) throw new NoSuchElementException();
        int v=head.val; head=head.next; if (head!=null) head.prev=null; else tail=null; size--; return v;
    }
    int removeRear() {
        if (tail==null) throw new NoSuchElementException();
        int v=tail.val; tail=tail.prev; if (tail!=null) tail.next=null; else head=null; size--; return v;
    }
    void print() { for (Node n=head;n!=null;n=n.next) System.out.print(n.val+" "); System.out.println(); }

    public static void main(String[] args) {
        DequeDLL dq=new DequeDLL();
        dq.addFront(1); dq.addFront(2); dq.addRear(3); dq.addRear(4);
        dq.print(); // 2 1 3 4
        System.out.println("Remove front: "+dq.removeFront()); // 2
        System.out.println("Remove rear: "+dq.removeRear()); // 4
        dq.print(); // 1 3
    }
}
