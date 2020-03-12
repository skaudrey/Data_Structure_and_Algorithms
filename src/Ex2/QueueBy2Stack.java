package Ex2;
import java.util.Stack;
public class QueueBy2Stack {
    private Stack<Integer> sHead = new Stack<>();
    private Stack<Integer> sTail = new Stack<>();
    private int nSize = 0;
    private int nTop = 0;

    public QueueBy2Stack(int[] a){
        for(int i: a){
            sHead.push(i);
        }
        this.nSize = this.sHead.size();
        this.nTop = sHead.peek();
    }

    public void enqueue(int n){
        this.sHead.push(n);
       this.nSize++;
    }

    public int dequeue(){
        int nDequeued = this.sHead.peek();
        while(!this.sHead.isEmpty()){
            int tmp = this.sHead.pop();
            this.sTail.push(tmp);
        }
        nDequeued = this.sTail.pop();
        while(!this.sTail.isEmpty()){
            this.sHead.push(this.sTail.pop());
        }
        this.nSize--;
        this.nTop = this.sHead.peek();
        return nDequeued;
    }

    public void delete(int k){
        for(int i=0; i<this.nSize-k;i++){
            this.sTail.push(this.sHead.pop());
        }
        this.sHead.pop();
        while(!this.sTail.isEmpty()){
            this.sHead.push(this.sTail.pop());
        }
        this.nSize--;
        this.nTop = this.sHead.peek();
    }

    public String toArray(){
        String str = "";
        for(int i: this.sHead){
            str += i + " ";
        }
        return str;
    }


    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7};
        QueueBy2Stack q = new QueueBy2Stack(a);
        q.enqueue(8);
        System.out.println(q.toArray());
        q.dequeue();
        System.out.println(q.toArray());
        q.delete(4);
        System.out.println(q.toArray());


        /*
        * For the worst case, enqueue operation is always the same, O(1); but dequeue operation have to pop all the elements
        * till get the last one, and then push them all into the stack again, so the time complexity is O(2n).
        * For delete(), till get the kth element, it need n-k times comparation and n-k+1 times pop,
        * and then n-k-1 times push, and thus the whole time complexity is O(3n-3k+1), which is also O(n);
        *
        * In a word, for enqueue: O(1); for dequeue or delete: O(n).
        * */


    }
}
