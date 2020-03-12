package Ex4;
import java.util.ArrayList;
/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex4
 * @Description: TODO
 * @date 2020/1/30 Priority queue implemented by ordered array.
 */
public class PqByArray {

    private ArrayList<Integer> pq = new ArrayList<>(10);
    private int nSize;

//    public PqByArray(int nSize){
//        pq = new ArrayList<>(nSize);
//    }

    public PqByArray(int[] arr){
        for(int i: arr){
            insert(i);
        }
    }

    public void insert(int nVal){
        // time complexity: O(n)
        pq.add(0);
        if(pq.size()==0){pq.set(0,nVal);}
        else{
            int i=0;
            while(i<nSize && nVal>pq.get(i)){ i++; }
            if(i==nSize){
                pq.set(i,nVal);
            }else{
                for(int j=nSize;j>i;j--){
                    pq.set(j,pq.get(j-1));
                }
                pq.set(i,nVal);
            }
        }
        this.nSize++;
    }

    public int deleteMin(){
        //time complexity: O(n) cause move elements.
        int nTmp = pq.get(0);
        for(int i=0; i<nSize-1;i++){
            pq.set(i,pq.get(i+1));
        }
        return nTmp;
    }

    public static void main(String[] args){
        int[] arr = {3,5,4,2,6,1};
        PqByArray pq = new PqByArray(arr);

        int a = pq.deleteMin();
        System.out.println(a);

    }

}
