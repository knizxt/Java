import java.util.*;

public class CircularQueue {
    int[] arr; int front, rear, size, capacity;

    CircularQueue(int cap) { capacity=cap; arr=new int[cap]; front=0; rear=-1; size=0; }

    void enqueue(int val) {
        if (size==capacity) { System.out.println("Queue full"); return; }
        rear=(rear+1)%capacity; arr[rear]=val; size++;
    }

    int dequeue() {
        if (size==0) throw new NoSuchElementException("Queue empty");
        int v=arr[front]; front=(front+1)%capacity; size--; return v;
    }

    int peek() { if (size==0) throw new NoSuchElementException(); return arr[front]; }
    boolean isEmpty() { return size==0; }

    public static void main(String[] args) {
        CircularQueue q=new CircularQueue(5);
        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        System.out.println("Dequeue: "+q.dequeue()); // 1
        q.enqueue(4); q.enqueue(5); q.enqueue(6);
        q.enqueue(7); // full
        while (!q.isEmpty()) System.out.print(q.dequeue()+" ");
        System.out.println();
    }
}
