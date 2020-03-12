package Ex3;

import java.util.List;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/29 Cycled list
 */
public class CycleList {
    private ListNode pHeader;
    private ListNode pTail;
//    private ListNode pCur;
    private int nSize;

    /**
     * In the demo given by TA, he set the next of last element as head, and the stop rule becomes if(pCur.next==head_
    */

    public ListNode getpHeader() {
        return pHeader.getNext();
    }

    public int getnSize() {
        return nSize;
    }

    public ListNode getpTail(){
        ListNode pTmp = pTail;
        pTmp.setNext(null);
        return pTmp;
    }

    public CycleList(){
        this.pHeader = new ListNode();
        this.pTail = pHeader;
        this.nSize = 0;
    }
    public CycleList(int n){
        if(this.pHeader==null){
            this.pHeader = new ListNode();
            this.pTail = pHeader;
            this.nSize = 0;
        }
        addAtTail(n); // Size increment and new list node will be done in add function.
    }
    public void addAtTail(int n){
        if(pTail==pHeader){ // For the null list
            ListNode pTmp = new ListNode(n);
            this.pHeader.setNext(pTmp);
            pTmp.setNext(pHeader);
            pTail = pTail.getNext();
//            pCur = pTail;
        }
        else{
            pTail.setNext(new ListNode(n));
            pTail = pTail.getNext();
//            pTail.setNext(pHeader);
        }
        this.nSize++;
    }

    public void addAtTail(ListNode aNode){
        if(aNode.getNext()!=null){
            aNode.setNext(null);
            System.out.println("The node should be a single node which ends with null");
        }

        if(pTail==pHeader){ // For the null list
            this.pHeader.setNext(aNode);
            aNode.setNext(pHeader);
            pTail = pTail.getNext();
//            pCur = pTail;
        }
        else{
            pTail.setNext(aNode);
            while(pTail.getNext()!=null){
            pTail = pTail.getNext();}
//            pTail.setNext(pHeader);
        }
        this.nSize++;
    }

    public void addAtHead(int n){
        if(pTail==pHeader){ // For the null list
            ListNode pTmp = new ListNode(n);
            this.pHeader.setNext(pTmp);
            pTmp.setNext(pHeader);
            pTail = pTail.getNext();
//            pCur = pTail;
        }
        else{
            pHeader.setNext(new ListNode(n));
            pHeader.getNext().setNext(pHeader.getNext());
        }
        this.nSize++;
    }



    public ListNode getAt(int nPos){
        ListNode pCur = pHeader.getNext();
        int nInd = 0;
        while(nInd<nPos){
            pCur = pCur.getNext();
        }
        ListNode pTmp = pCur;
        pTmp.setNext(null);
        return pTmp;
    }

    public ListNode deleteAtHead(){
        ListNode ptmp = pHeader.getNext();
        if (pTail==pHeader){return null;}
        else{
            this.pHeader.setNext(ptmp.getNext());
            ptmp.setNext(null);
            this.nSize--;
        }
        return ptmp;
    }

    public ListNode deleteAtTail(){
        ListNode ptmp = pHeader.getNext();
        if (pTail==pHeader){return null;}
        else{
            while(ptmp.getNext()!=pTail){ptmp = ptmp.getNext();}
            pTail = ptmp;
//            pTail.setNext(pHeader);
            this.nSize--;
        }
        return ptmp;
    }

    public String toArray(){
        ListNode pCur = pHeader.getNext();
        String str = "";
        while(pCur!=pTail){
            str += pCur.getnVal() + " ";
            pCur = pCur.getNext();
        }
        str += pCur.getnVal() + " ";
        return str;
    }

    public void setpTail(ListNode pTail) {
        this.pTail = pTail;
    }
}
