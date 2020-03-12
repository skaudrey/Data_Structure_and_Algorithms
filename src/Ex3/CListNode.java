package Ex3;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/27 Cycle List Node
 */
public class CListNode {
    int nVal;
    CListNode next;
    public CListNode(int nVal){
        this.nVal = nVal;
        this.next = this;
    }
    public CListNode(){
        this.nVal = Integer.MAX_VALUE;
        this.next = this;
    }

    public CListNode add(CListNode pCur, int n){
        CListNode head = pCur.next;
        CListNode tmp = pCur;
        pCur.next = new CListNode(n);
        tmp = tmp.next;
        tmp.next = head;
        head.next = tmp;
        return pCur.next;
    }

    public static void printList(CListNode pHeader){
        CListNode pCur = pHeader.next;
        while(!(pCur==pHeader)){
            System.out.printf("%d ",pCur.nVal);
            pCur = pCur.next;
        }
        System.out.println("\n");
    }
}
