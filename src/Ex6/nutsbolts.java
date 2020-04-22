package Ex6;

import util.utility;

import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex4
 * @Description: TODO
 * @date 2020/3/28 Matching nuts and bolts by quick sort
 */
public class nutsbolts {
    private int[] nuts;
    private int[] bolts;
    int nSize;
    String formatStr = "\n------%s%d\n";

    public nutsbolts(int[] A){
        nuts = A.clone();
        bolts = A.clone();
        utility.shuffle(nuts);
        utility.shuffle(bolts);

//        int[] a = { 4,5,2,6,1,7,8,9,3};
//        int[] b = { 6,2,1,8,9,7,4,5,3 };
//        nuts = a.clone();
//        bolts = b.clone();
        nSize = nuts.length;

    }

    public void match(){
        quicksort(0,nSize- 1,0);

    }

    private void quicksort(int nl, int nr, int nCount){
        if(nl>nr) return; // nl>nr rather than nl>=nr
        System.out.printf(formatStr,"nuts:",nCount);
        System.out.println(Arrays.toString(nuts));
        System.out.printf(formatStr,"bolts:",nCount);
        System.out.println(Arrays.toString(bolts));
        int i = partition(nl,nr);
        nCount++;
        quicksort(nl+2,i,nCount); //mind the index, very tricky. If you use nl+1,i, you gotta need more loops.
        quicksort(i+1,nr,nCount);
    }

    private int partition(int nl, int nr){
        if(nl>=nr) return nl;
        int pivot1 = nuts[nl];
        int i1 = nl, j1 = nr;
        while (i1 < j1) {
            while (bolts[i1] < pivot1) i1++;
            while (bolts[j1] > pivot1) j1--;
            if (i1 >= j1) break;
            utility.swap(bolts, i1, j1);
        }
        utility.swap(bolts, nl, i1);
//            return j1;

        int pivot2 = bolts[nl+1];
        int i2 = nl+1 , j2 = nr;
        while (i2 < j2) {
            while (nuts[i2] < pivot2) i2++;
            while (nuts[j2] > pivot2) j2--;
//            do{i2++;} while(nuts[i2]<pivot2);
//            do{j2--;} while(nuts[j2]>pivot2);
            if (i2 >= j2) break;
            utility.swap(nuts, i2, j2);
        }
        utility.swap(nuts, nl+1 , i2);
        return i2;
    }

    public static void  main(String[] args){
        int[] A = {1,2,3,4,5,6,7,8,9};
        nutsbolts nb = new nutsbolts(A);
        nb.match();
    }
}
