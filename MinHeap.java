import java.util.*;

public class MinHeap {
    int[] heap; int size;

    MinHeap(int cap) { heap=new int[cap]; }

    int parent(int i) { return (i-1)/2; }
    int left(int i) { return 2*i+1; }
    int right(int i) { return 2*i+2; }

    void push(int val) {
        heap[size++]=val; int i=size-1;
        while (i>0&&heap[i]<heap[parent(i)]) { int t=heap[i]; heap[i]=heap[parent(i)]; heap[parent(i)]=t; i=parent(i); }
    }

    int pop() {
        int top=heap[0]; heap[0]=heap[--size]; heapify(0); return top;
    }

    void heapify(int i) {
        int sm=i, l=left(i), r=right(i);
        if (l<size&&heap[l]<heap[sm]) sm=l;
        if (r<size&&heap[r]<heap[sm]) sm=r;
        if (sm!=i) { int t=heap[i]; heap[i]=heap[sm]; heap[sm]=t; heapify(sm); }
    }

    public static void main(String[] args) {
        MinHeap h=new MinHeap(10);
        for (int x:new int[]{5,3,8,1,2}) h.push(x);
        while (h.size>0) System.out.print(h.pop()+" "); // 1 2 3 5 8
        System.out.println();
    }
}
