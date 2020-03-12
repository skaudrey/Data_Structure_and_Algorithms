package Ex3;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex3
 * @Description: TODO
 * @date 2020/1/29 Cycle single list opetations
 */
public class CycleListOP {

    public static void printList(CycleList aList){
        System.out.println(aList.toArray());
    }

    public static CycleList buildTest(int[] arr){
        CycleList aList = new CycleList();
        for(int i: arr){
            aList.addAtTail(i);
        }
        return aList;
    }

    public static CycleList merge(CycleList aList, CycleList bList){
//        ListNode pCurA = aList.getpHeader();
        ListNode pCurB = bList.getpHeader();
        ListNode pTailA = aList.getpTail();
        ListNode pTailB = bList.getpTail();

        ListNode ptmp = null;
//        pTailA.setNext(pCurB);
////        ListNode ptmp = pTailA;
//        while(pTailA!=pTailB){
//            pTailA = pTailA.getNext();
////            ptmp = pTailA;
//        }
//        aList.setpTail(pTailA);
//        ptmp.setNext(null);

        while(pCurB!=pTailB && pCurB!=null){
            ptmp = pCurB;
            pCurB = pCurB.getNext();
            ptmp.setNext(null);
            aList.addAtTail(ptmp);
        }
        aList.addAtTail(pCurB);
        return aList;
    }

    public static void main(String[] args){
        int[] arr1 = {1,2,3,4};
        int[] arr2 = {5,6,7,8,9};
        CycleList aList = buildTest(arr1);
        CycleList bList = buildTest(arr2);

        CycleList merged = merge(aList,bList);
        printList(merged);
    }

}
