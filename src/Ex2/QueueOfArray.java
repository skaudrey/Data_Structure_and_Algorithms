package Ex2;
import static util.utility.DEV_MODE;
public class QueueOfArray {

    /*
    * In the demo given by TA, there are two pointer: back and front
    * while enqueue: front = (front+1)%arr.length
    * while dequeue: back = (back+1)%arr.length
    *
    * for resizing: use dequeue to copy.
    *
    * So, my solution is not elegant, at least for dequeue, mine is O(n)
    * */

    private int[] arr = new int[10];
    private int nTop = -9999999;
    private int nSize = 10;
    public QueueOfArray(int nSize){
        if(this.nSize!=nSize){
            arr = new int[nSize];
        }
    }
    public QueueOfArray(int[] a){
        if(a.length==0){return;}
        if(this.nSize<a.length*2){
            this.nSize = a.length;
            this.reallocate();
        }
        int j = 0;
        for(int i: a){
            arr[j] = i; j++;
        }
        this.nTop = arr[j-1];

        check();
    }

    public void enqueue(int n){
        if(nSize+1>=arr.length/2){
            this.nSize = nSize+1;
            this.reallocate();
        }
        this.arr[nSize-1] = n;
        this.nTop = n;
//        this.nSize++;
        check();
    }

    public void reallocate(){
        int[] arrtmp = new int[this.nSize*2];
        int nInd = 0;
        for(int i:arr){
            arrtmp[nInd] = i;
            nInd++;
        }
        this.arr = arrtmp;
    }

    public int dequeue(){
        int nDequeue = this.arr[0];
        for(int j=1; j<this.nSize;j++){
            this.arr[j-1] = this.arr[j];
        }
        this.nSize--;
        check();
        return nDequeue;
    }

    public int peek(){
        return this.nTop;
    }

    public int size(){
        return this.nSize;
    }

    public String toArray(){
        String str = "";
        for(int i=0; i<this.nSize; i++){
            str+= this.arr[i] + " ";
        }
        return str;
    }

    private void check(){
        if(DEV_MODE){
            System.out.printf("size: %d\n",this.nSize);
            System.out.printf("top: %d\n",this.nTop);
            System.out.printf("real space size: %d\n",this.realSpaceSize());
            System.out.println("--------------------------------");
        }
    }

    public int realSpaceSize(){
        return this.arr.length;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6};
        QueueOfArray q = new QueueOfArray(a);
        System.out.println(q.toArray());
        q.enqueue(7);
        System.out.println(q.toArray());
        q.dequeue();
        System.out.println(q.toArray());

    }

}
