package Algo;

import java.util.Arrays;

import util.utility;
import util.utility.*;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Algo
 * @Description: TODO
 * @date 2020/4/2 divide and conquer
 */
public class dc {

    public static int[] merge(int[] a, int[] b){
        // No sentinels in array a and b, do merge.
        int[] T = new int[a.length+b.length];
        int i=0, j=0;
        int n = 0;
        while(i<a.length && j<b.length){
            if(a[i]<=b[j])
                T[n++] = a[i++];
            else
                T[n++] = b[j++];
        }
        while(i<a.length)
            T[n++] = a[i++];
        while(j<b.length)
            T[n++] = b[j++];
        return T;
    }

    private static void rotate(int[] T, int beg, int end, int nlen){
        nlen %= (end-beg+1);
        reverse(T,beg,end-nlen);
        reverse(T,end-nlen+1,end);
        reverse(T,beg,end);
    }
    private static void reverse(int[] T, int beg, int end){
        while(beg<end){
            int nTmp = T[beg];
            T[beg] = T[end];
            T[end] = nTmp;
            beg++; end--;}
    }
    public static void mergeInPlace(int[] T,int nk){        // T[1...k] and T[k+1...n] are already sorted. How to sort the whole T in place in linear time?
        int i=0, j=nk+1,end=T.length-1;
        while (i<=end && j<=end && i<j){
            while(i<=end && T[i]<T[j]) i++;
            int k = j;
            while (j<=end && T[j]<T[i]) j++;
            if(j>k){
                rotate(T,i,j-1,j-k);
            }
            i += (j-k+1);
        }
    }

    public static int bindex(int[] L, int i, int j, int a){
        // get the index in L where L[i..j] in [a..b], L is already sorted ascendly.
        String formatStr = "i = %d, j = %d, k= %d, with the value of L[i] = %d, L[j] = %d, L[k]= %d\n";
        while(j-i>1 ) {
            int k = (i + j) / 2;
            System.out.format(formatStr,i,j,k,L[i],L[j],L[k]);

            if (L[k] <= a )
                i = k+1;

            else if (L[k] > a)
                j = k;
        }
        System.out.println(i);
        return i;
    }




    public static void main(String[] args){
        int[] a = {1,3,5};
        int[] b = {2,3,4,5,6,7,8,9};
//        System.out.println(Arrays.toString(merge(a,b)));
        int[] c = {4,6,7,8,2,3,4,5,6,7,8,9};
        int nk = 3;
//        mergeInPlace(c,nk);
//        System.out.println(Arrays.toString(c));

        int[] L = {1, 2, 4, 5, 5, 6, 7, 8, 8, 9};
        System.out.println("search a");
        int i1 = bindex(L,0,L.length-1,3);
        System.out.println("search b");
        int l2 = bindex(L,i1,L.length-1,8);
    }


}
