package Ex4;
import java.util.Arrays;
/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex4
 * @Description: TODO
 * @date 2020/1/30 Priority queue implemented with an array with sentinelles.
 */
public class HeapArrSent {
    private int[] heap = new int[10];
    private int nSize=0;
    private final static int c = 2;
    private int nPriority = 1; // 1 for min-heap; 0 for max-heap;
//    private int nCur = 0;

    public HeapArrSent(){
        Arrays.fill(heap,Integer.MAX_VALUE);
        heap[0] = Integer.MIN_VALUE;
//        nCur = 1;
    }

    public HeapArrSent(int[] arr, int nPriority){
        /* @Description:
         * @param arr: initialize a heap by an integer array.
         * @param nPriority: flag of priority, 0 for max-heap, 1 for min-heap
        * @Return:
        */
        Arrays.fill(heap,Integer.MAX_VALUE);
        heap[0] = Integer.MIN_VALUE;
        this.nPriority = nPriority;
        for(int i:arr){ insert(i); }
    }

    public void setnPriority(int nPriority){
        this.nPriority = nPriority;
    }

    public void insert(int nVal){
        if(heap.length-1<(nSize+1)*c){reallocate();} //one of the item is for sentinelle, so it should be ignored.
        if(nSize==0){heap[1] = nVal;}
        else{
            swim(nVal,nSize+1);
        }
        nSize++;
    }

    private void swim(int nVal, int nPos){
        while(nPos!=1){
            int nTmp = heap[nPos/2];
            if(nPriority==1){
                if(nTmp>nVal){
                    heap[nPos] = nTmp;
                    heap[nPos/2] = nVal;
                    nPos /= 2;
                }
                else{heap[nPos] = nVal;break;}
            }
            else{
                if(nTmp<nVal){
                    heap[nPos] = nTmp;
                    heap[nPos/2] = nVal;
                    nPos /= 2;
                }
                else{heap[nPos] = nVal;break;}
            }

        }
    }

    public int deletePeak(){
        if(nPriority==1){return deleteMin();}
        else {return deleteMax();}
    }

    private int deleteMin(){
        //used while the heap is min-heap, rather than max-heap
        int nDel = heap[1];
        if(nSize==1){heap[1] = Integer.MAX_VALUE;}
        else{
            int nTmp = heap[nSize];
            int i = 1;
            while(nTmp>heap[i] && 2*i < nSize){
                int nC1 = heap[2*i];
                int nC2 = heap[2*i+1];
                int j = nC1<nC2? 2*i : 2*i+1;
                heap[i] = heap[j];
                i = j;
            }
            heap[i] = nTmp;
            heap[nSize] = Integer.MAX_VALUE;
        }
        nSize--;
        return nDel;
    }

    private int deleteMax(){
        //used while the heap is min-heap, rather than max-heap
        int nDel = heap[1];
        if(nSize==1){heap[1] = Integer.MAX_VALUE;}
        else{
            int nTmp = heap[nSize];
            int i = 1;
            while(nTmp<heap[i] && 2*i < nSize){
                int nC1 = heap[2*i];
                int nC2 = heap[2*i+1];
                int j = nC1>nC2? 2*i : 2*i+1;
                heap[i] = heap[j];
                i = j;
            }
            heap[i] = nTmp;
            heap[nSize] = Integer.MAX_VALUE;
        }
        nSize--;
        return nDel;
    }



    private void reallocate(){
        int[] anew = new int[(nSize+1)*c+1];
        Arrays.fill(anew,Integer.MAX_VALUE);
        anew[0] = Integer.MIN_VALUE;
        int j = 1;
        for(int i=1; i<=nSize; i++){
            anew[j] = heap[i]; j++;
        }
        heap = anew;
    }

    public String toArray(){
        String str = "";
        for(int i=1; i<=nSize; i++){
            str += heap[i] + " ";
        }
        return str;
    }

    public void getFirstK(int k, int nPos){
        if(k==0 || nSize == 0){
            return;
        }
        int nInd = 1;
        while(nInd<k){
            System.out.print(heap[nInd]+" ");
            nInd++;
        }
        System.out.println(" ");
    }

    public int minmax(int d){
        /* @Description: The problem in IFT6002, Exercise priority list, the pq.5
         * After analysis, I found that the value exist in the top d/2 elements, from heap[n-d+1], to heap[(n-d+1)/2].
         * @param d: the value of window size.
        * @Return:
        */

        int i = nSize - d + 1;
        int j = i%2==0? i/2:i/2+1 ;
        int n = heap[i];
        i--;
        while(i!=j){
            n = heap[i]>n? heap[i]:n;
            i--;
        }

        return n;
    }

    public static void main(String[] args){
        int[] arr = {3,5,4,2,6,1};
        System.out.printf("%10s%s%10s\n","-","build a min-heap","-");
        HeapArrSent pq = new HeapArrSent(arr,1);
        System.out.println(pq.toArray());
        System.out.printf("%10s%s%10s\n","-","delete minimal value of min-heap","-");
        System.out.println(pq.deletePeak());// delete peak, min for the min-heap; max for the max-heap.
        System.out.printf("%10s%s%10s\n","-","heap after delete operation","-");
        System.out.println(pq.toArray());
        System.out.printf("%10s%s%10s\n","-","Get the top k priority elements","-");
        pq.getFirstK(5,1);
//        System.out.println(Arrays.toString(b));
        System.out.printf("%10s%s%10s\n","-","build a min-heap","-");
        HeapArrSent minHeap = new HeapArrSent(arr,1);
        System.out.println(minHeap.toArray());
        System.out.printf("%10s%s%10s\n","-","build a max-heap","-");
        HeapArrSent maxHeap = new HeapArrSent(arr,0);
        System.out.println(maxHeap.toArray());
        System.out.printf("%10s%s%10s\n","-","delete maximum value of max-heap","-");
        maxHeap.deletePeak();
        System.out.println(maxHeap.toArray());

        System.out.printf("%10s%s%10s\n","-","minmax","-");
        System.out.println(minHeap.minmax(5 ));


    }
}
