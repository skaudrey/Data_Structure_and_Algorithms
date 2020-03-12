package intra;
import org.omg.PortableInterceptor.INACTIVE;
import java.util.Stack;

import java.util.LinkedList;
/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/2/9 intra-H2012-E2-even-odd
 */
public class h2012 {
    private node pHead;
    public h2012(int[] arr){
        pHead = new node(Integer.MIN_VALUE);
        node pCur = pHead;
        for(int i: arr){
            pCur.next = new node(i);
            pCur = pCur.next;
        }
    }

    public node getpHead(){
        return this.pHead;
    }

    public node deleteOdd(node N){
        if(N==null) return N;
        node aHead = N;
        if(aHead.nData%2!=0)
            N = deleteOdd(aHead.next); // record the header.
        else {
            if(aHead.next!=null && aHead.next.nData%2!=0){
                aHead.next = aHead.next.next;
            } // jump the next odd.
            deleteOdd(aHead.next);
        }

        return N;
    }


    public String toString(node p) {
        String str = "";
        while(p!=null){
            str += p.nData + " ";
            p = p.next;
        }
        return str;
    }

    private class node{
        public int nData;
        public node next;

        public node(int nData){
            this.nData = nData;
            this.next = null;
        }

    }

    public static void main(String[] args){
        int[] arr = {1,9,6,3};
        h2012 myls = new h2012(arr);

        int[] arr2 = {2,1,4,6,3};
        h2012 myls2 = new h2012(arr2);

        node tmp = myls.deleteOdd(myls.pHead.next);
        System.out.println(myls.toString(tmp));

        node tmp2 = myls2.deleteOdd(myls2.pHead.next);
        System.out.println(myls2.toString(tmp2));
    }
}
