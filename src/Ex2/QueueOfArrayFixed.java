package Ex2;
import static util.utility.DEV_MODE;

public class QueueOfArrayFixed {
    protected int[] arr = new int[2];

    private int nSize=0;
    private int front = 0;
    private int back = 0;

    public QueueOfArrayFixed(int[] a){
        for(int i: a) enqueue(i);
    }

    public int dequeue(){
        int nC;
        if(nSize!=0){
            nC = arr[back];
            back = (back+1)%arr.length;
            nSize--;
        }
        else nC = Integer.MIN_VALUE;

        resize();

        return nC;
    }

    public void enqueue(int nVal){
        resize();
        arr[front] = nVal;
        front = (front+1)%arr.length;
        nSize++;
    }

    public boolean isEmpty(){
        if(nSize==0) return true;
        else return false;
    }

    private boolean resize(){
        boolean flag = false;
        if(nSize*2>=arr.length) {
            flag = true;}
        else if(nSize<(arr.length/4)) {
            flag = false;}
        else return false;

        int[] qq;

        if(flag) qq = new int[arr.length*2];
        else qq = new int[arr.length/2];
        int n = 0;
        while(!this.isEmpty()){
            qq[n++] = this.dequeue();
        }

        nSize += n;
        front = n;
        back = 0;
        this.arr = qq;

        return true;
    }

    public String toString(){
        String str = "";
        for(int i=0; i<nSize; i++){
            str += arr[i] + " ";
        }

        return str;
    }


    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6};
        QueueOfArrayFixed q = new QueueOfArrayFixed(a);
        System.out.println(q.toString());
        q.enqueue(7);
        System.out.println(q.toString());
        q.dequeue();
        System.out.println(q.toString());

    }

}
