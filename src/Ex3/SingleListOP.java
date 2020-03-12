package Ex3;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.List;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/28 The operations for single list
 */
public class SingleListOP {
    /**
     * One good way to analyzing the list, do let the next of one node be null if you want to regard it as a single node,
     * as long as you do not set null for the next of it, it is always a list, and thus if you add a node for it, it will
     * always add the node to the end of it.
     * */

    public static void printList(SingleList aList){
        System.out.println(aList.toArray());
    }

    public static SingleList buildTest(int[] arr){
        SingleList aList = new SingleList();
        for(int i: arr){
            aList.add(i);
        }
        return aList;
    }


    /*
    * Exchange list nodes in list
    * */
    public static SingleList exchangeByLinks(SingleList aList, int nPos){
        if(nPos>=aList.getnSize()-2 || nPos<0){return aList;}
        ListNode pHeader = aList.getPheader();
        int nInd = 0;
        ListNode pCur = pHeader.getNext();
        while(nInd<nPos-1){pCur = pCur.getNext(); nInd++; }
//        pCur = pCur.getNext();
        ListNode pTmp = pCur.getNext();

        pCur.setNext(pTmp.getNext());
        pTmp.setNext(null);
//        pCur = pCur.getNext();
        pTmp.setNext(pCur.getNext().getNext());
        pCur.getNext().setNext(pTmp);


        return aList;
    }

    public static SingleList exchangeByContents(SingleList aList, int nPos){
        if(nPos>=aList.getnSize()-2 || nPos<0){return aList;}
        ListNode pHeader = aList.getPheader();
        int nInd = 0;
        ListNode pCur = pHeader.getNext();
        while(nInd<nPos-1){pCur = pCur.getNext(); nInd++; }
        int n1 = pCur.getNext().getnVal();
        pCur = pCur.getNext();
        int n2 = pCur.getNext().getnVal();
        pCur.setnVal(n2);
        pCur.getNext().setnVal(n1);

        return aList;
    }

    /*
    * Reverse list, one by iteration, the other can be done in recursion.
    * */
    public static SingleList reverseInIter(SingleList aList){
        ListNode pCur = aList.getPheader().getNext();
        ListNode pNext = null;
        ListNode pre = null;

        while(pCur!=null){
            pNext = pCur.getNext();
            pCur.setNext(pre);
            pre = pCur;
//            pNext = pCur.getNext();
            pCur = pNext;
        } //find the target node
        aList.setpHeader(pre);
        return aList;
    }

    public static SingleList reverseInRecur(SingleList aList){
        ListNode pHeader = aList.getPheader().getNext();

        ListNode newhead = recurList(pHeader);
        aList.setpHeader(newhead);
//        aList.setpHeader(reversed);
//        System.out.println(aList.toArray());
        return aList;
    }

    public static ListNode recurList(ListNode pCur){
        if(pCur.getNext()==null||pCur==null ){return pCur;}
        ListNode pNext = pCur.getNext();
        pCur.setNext(null); // avoid infinitely recursion and get the single node except for next list.
        ListNode reversed = recurList(pNext); //need to maintain a copy so as to not change the original one until recursived to it.
        pNext.setNext(pCur);
        //Recursive till find the last element of list and save it as pNext, since pCur is the one ahead of it,
        // then pNext.next=pCur.
        return reversed;
    }

    public static SingleList MTF(SingleList aList, int n){
        ListNode pHead = aList.getPheader().getNext();
        ListNode pCur = pHead;
        ListNode pTmp = null;
        while(pCur.getNext().getnVal()!=n && pCur.getNext()!=null){pCur = pCur.getNext();}
        if(pCur==null){return aList;}
        if(pCur.getNext()==null){
            pTmp = pCur.getNext();
            pTmp.setNext(pHead);
            pCur.setNext(null);

        }
        else{
            pTmp = pCur.getNext();
            pTmp.setNext(pTmp.getNext());
            pTmp.setNext(null);
            pCur.setNext(pHead);
        }

        aList.setpHeader(pTmp);
        return aList;
    }

    public static SingleList roll(SingleList aList, int nK, int nOffset){
        if(nK==nOffset)  {return aList;}
        if(nOffset<0){
            return rollPositive(aList,nK,nK+nOffset);
        }
        else{
            return rollPositive(aList,nK,nOffset);
        }
    }

    public static SingleList rollPositive(SingleList aList, int nK, int nOffset){
        //if offset is positive, the head nOffset items will be set at the end; else, the end nOffset items will be move to the front
        // split get nK list, and then split and link again.
        //roll(n,-j)=roll(n,n-j)
        ListNode pHead = aList.getPheader().getNext();
        ListNode l1 = pHead;
        ListNode l2 = null;

        //Get list 1
        int nInd = 1;
        ListNode pCur = l1;
        while(nInd<nOffset){pCur=pCur.getNext(); nInd++;}
        ListNode l1_end = pCur;

        // Get list 2
        l2 = l1_end.getNext();
        nInd++;
//        l1.setNext(null);
        pCur = l2;
        while(nInd<nK){pCur=pCur.getNext(); nInd++;}
        l1_end.setNext(null);
        l1_end.setNext(pCur.getNext());
        pCur.setNext(null);
        pCur.setNext(l1);

        aList.setpHeader(l2);
        return aList;
    }



    public static SingleList rotF(SingleList aList,int nK){
        //MTF, target is the end of the first nKth items. The 1st one will be put at the end.
        ListNode pHead = aList.getPheader().getNext();
        ListNode pCur = pHead;
        ListNode pTmp = null;
        int nId = 1;

        while(nId<nK){pCur = pCur.getNext(); nId++;}
        pTmp = pHead;
        pHead = pHead.getNext();
        pTmp.setNext(null);
        pTmp.setNext(pCur.getNext());
        pCur.setNext(pTmp);

        aList.setpHeader(pHead);
        return aList;
    }

    public static SingleList rotR(SingleList aList,int nK){
        //MTE, target is the beginning of the first nKth items. The last one will be put in the beginning.
        ListNode pHead = aList.getPheader().getNext();
        ListNode pCur = pHead;
        ListNode pTmp = null;
        int nId = 1;
        while(nId<nK-1){pCur = pCur.getNext(); nId++;}

        // change the links
        pTmp = pCur.getNext();
        pCur.setNext(pTmp.getNext());
        pTmp.setNext(null);
        pTmp.setNext(pHead);


        aList.setpHeader(pTmp);
        return aList;
    }

    public static int eval(ListNode N, int x){
        if(N.getNext()==null){
            return N.getnVal();
        }
        return eval(N.getNext(),x)* x + N.getnVal();
    }



    public static void main(String[] args){
//        int[] arr = {1,2,3,4,5,6,7,8};
//        SingleList mylist = buildTest(arr);
//
//        mylist = exchangeByLinks(mylist,3);
//        printList(mylist);
//        mylist = exchangeByContents(mylist,3);
//        printList(mylist);
//
//        mylist = reverseInIter(mylist);
//        printList(mylist);
//        mylist = reverseInRecur(mylist);
//        printList(mylist);
//
//        int[] arr2 = {0,1,2,3,4,5,6,7};
//        SingleList aList = buildTest(arr2);
//        aList = rotF(aList,5);
//        printList(aList);
//        aList = rotR(aList,5);
//        printList(aList);
//        aList = roll(aList,7,3);
//        printList(aList);
//        aList = roll(aList,7,-3);
//        printList(aList);


        System.out.println("Check the answer of A2012-E2-Polynomials");
        int[] polyArr = {7,-1,1,2};
        SingleList pList = buildTest(polyArr);
        System.out.println(eval(pList.getPheader().getNext(),1));
        System.out.println(eval(pList.getPheader().getNext(),2));
        System.out.println(eval(pList.getPheader().getNext(),3));

    }


}
