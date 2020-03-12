package Ex1;
import java.util.Stack;
public class QueueOfStack {
    /*
    * Implement the queue by stack
    * Tips: push for enqueue; dequeue can be implemented recursively.
    * */
    private static Stack<Integer> s = new Stack<>();
    public QueueOfStack(int[] arr){
        if(arr.length==0){return;}
        else{
            for(int i: arr){
                s.push(i);
            }
        }
    }

    public int enqueue(int pushA){
        s.push(pushA);
        return s.peek();
    }

    public int dequeue(){ // Find the element at the bottom of stack.
        int dequeued = s.pop();
        if(s.isEmpty()){
            return dequeued;
        }
        else{
            int last = dequeue();
            s.push(dequeued); // For the untargeted but poped one, push it back again.
            return last;
        }
    }

    public String toString(){
        String str = "";
        for(int i: s){
            str += i+ " ";
        }

        return str;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        QueueOfStack s = new QueueOfStack(a);
        s.enqueue(6);
        System.out.println(s.toString());
        s.dequeue();
        System.out.println(s.toString());
    }
}
