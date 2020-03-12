package Ex3;

import java.util.List;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/28 single list
 */
public class SingleList {
    private ListNode pHeader;
    private ListNode pCur;
    private int nSize;

    public SingleList(){
        this.pHeader = new ListNode();
        this.nSize = 0;
        this.pCur = this.pHeader;
    }

    public ListNode getPheader(){
        return this.pHeader;
    }

    public void add(int nVal){
        this.pCur.setNext(new ListNode(nVal));
        pCur = pCur.getNext();
        this.nSize++;
    }
    public ListNode delete(){
        if (this.pHeader.getNext()==null && this.nSize==0){return null;} // For null list, return directly.

        ListNode tmp = this.pHeader.getNext().getNext();
        ListNode deleted = this.pHeader.getNext();
        deleted.setNext(null);
        this.pHeader.setNext(tmp);
        this.nSize--;
        return deleted;
    }

    public ListNode deleteAt(ListNode pHeader,int nPos){
        nPos -=1;
        if (this.pHeader.getNext()==null && this.nSize==0){return null;} // For null list, return directly.
        if(nPos<0 || nPos>nSize-1){return null;}
        ListNode pCur = pHeader.getNext();
        int nInd = 0;
        while(nInd<nPos){pCur = pCur.getNext();}

        ListNode tmp = pCur.getNext().getNext();
        ListNode deleted = pCur.getNext();
        deleted.setNext(null);
        pCur.setNext(tmp);
        this.nSize--;
        return deleted;
    }

    public String toArray(){
        ListNode pCur = pHeader.getNext();
        String str = "";
        while(pCur!=null){
            str += pCur.getnVal() + " ";
            pCur = pCur.getNext();
        }
        return str;
    }

    public int getnSize(){
        return this.nSize;
    }

    public void setpHeader(ListNode node){this.pHeader.setNext(node);}
}
