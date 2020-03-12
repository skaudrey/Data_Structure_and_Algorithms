package Ex3;

import java.util.List;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/24 mylist
 */
public class ListNode {
    private int nVal;
    private ListNode next;
    public ListNode(int nVal, ListNode next){
        this.nVal = nVal;
        this.next = next;
    }
    public ListNode(int nVal){
        this.nVal = nVal;
        this.next = null;
    }
    public ListNode(){
        this.nVal = 0;
        this.next = null;
    }
    public void setnVal(int nVal){
        this.nVal = nVal;
    }

    public int getnVal(){
        return this.nVal;
    }

    public void setNext(ListNode next){
        this.next = next;
    }
    public ListNode getNext(){
        return this.next;
    }

}
