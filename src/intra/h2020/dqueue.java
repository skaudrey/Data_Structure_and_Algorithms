package intra.h2020;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/3/20 Winter 2020, IFT6002
 */


public class dqueue { // h2020 Q1, build a data structure so that it supports insertFirst(), insertLast(), deleteFirst() and deleteLast()
    int tail = 0;
    int head = 0;

    int[] queue = new int[2];
    int size = 0;

    public void resize() {
        // This version is wrong, while copying, considering it is a double table, one can copy from head till tail.
        // After copying, the value of tail is ok, but for s, it's updated as 0 while copying.
        if (size == queue.length) {
            int[] old = queue.clone();
            queue = new int[2 * size];
            int j = head;
            for (int i=0; i<2*size; i++){
                queue[i] = old[j];
                j = (j+1)%size;
            }
        }
    }

    public void insertFirst(int x) {
        resize();
        head = Math.floorMod((head - 1) ,queue.length);
        queue[head] = x;
        size++;
    }

    public void insertLast(int x) {
        resize();
        tail = Math.floorMod((tail + size) ,queue.length); // tail+size, not tail+1,tail is the last index after s in size count.
        queue[tail] = x;
        size++;
    }

    public Integer deleteFirst() {
        if (head == tail)
            return null;
        int d = queue[head];
        head = Math.floorMod((head + 1) ,queue.length);
        size--;
        return d;
    }

    public Integer deleteLast() {
        if (head == tail)
            return null;
        int d = queue[tail];
        tail = Math.floorMod((tail+size - 1) ,queue.length);// tail+size-1, not tail-1, similar reason while insertLast
        size--;
        return d;
    }

    public static void main(String[] args) {
        dqueue q = new dqueue();
        q.insertFirst(1);
        q.insertFirst(2);
        q.insertLast(3);
        q.insertLast(4);
        q.deleteLast();
        q.deleteFirst();
    }
}
