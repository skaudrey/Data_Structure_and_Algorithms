package Ex6;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex6
 * @Description: TODO
 * @date 2020/2/15 Sorting methods
 */
public class sort {
    // The result will be an ascending array.
    public static int[] bubbleSort(int[] A){
        int n = A.length;
        for(int i=0; i<n;i++){
            for(int j = i+1; j<n; j++){
                if(A[i]>A[j]){
                    int nTmp = A[i];
                    A[i] = A[j];
                    A[j] = nTmp;
                }
            }
        }
        return A;
    }

    public static void insertSort(int[] A){
        // copy and shift all the elements that larger than A[i] rightly, and let j denotes the position for nTmp
        int nSize = A.length;
        for(int i = 0; i<nSize; i++){
            int nTmp = A[i];
            int j = i;
            while(j>0 && A[j-1]>nTmp){
                A[j] = A[j-1];
                --j;
            }
            A[j] = nTmp;
        }
    }

    public static void selectSort(int[] A){
        //For elements in A[i], find the min one from A[i+1], then exchange them.

        int nSize = A.length;

        for(int i = 0; i<nSize; i++){
            int nMinInd = i;

            for(int j = i+1; j<nSize; j++){
                if(A[j]<A[nMinInd])
                    nMinInd=j;
            }
            if(nMinInd!=i)
                swap(A,i,nMinInd);

        }
    }

    public static void mergeSort(int[] A, int nl, int nr){
        if(nr-nl<2)return;

        int m = (nl+nr)/2;
        mergeSort(A,nl,m);
        mergeSort(A,m,nr);
        merge(A,nl,m,nr);
    }
    public static void merge(int[] A, int nl, int nM, int nr){
        int[] aux =  new int[A.length];
        int i = nl;
        while(i<nM){aux[i] = A[i]; i++;}
        for(int j = nM; j<nr; j++){aux[nr-1+nM-j] = A[j];}
        i = nl; int j = nr-1; int k=nl;
        while(k<nr){
            if(aux[i]<=aux[j]){
                A[k] = aux[i];
                i++;
            }
            else{
                A[k] = aux[j];
                j--;
            }
            k++;
        }
    }

    public static void quickSort(int[] A, int nl, int nr){
        if(nr-nl<2)return;
        int i = partition(A,nl,nr);
        quickSort(A,nl,i);
        quickSort(A,i+1,nr);
    }

    public static int partition(int[] A, int nl, int nr){
        int i = nl-1;
        int j = nr-1;
        int pivot = A[nr-1];
        while(i<j){
            do{i++;} while (A[i]<pivot);
            do{j--;} while(A[j]>pivot && i<j);
            if(i>=j) break;
            swap(A,i,j);
        }
        swap(A,i,nr-1);
        return i;
    }

    public static void swap(int[] A, int i, int j){
        int nTmp = A[i];
        A[i] = A[j];
        A[j] = nTmp;
    }

    public static void shuffle(int[] A){
        Random rander = new Random();
        for(int i=A.length; i>0;i--){
            int nTmp = rander.nextInt(i);
            swap(A,nTmp, i-1);
        }
    }


    public static void main(String[] args){
        int[] A = {1,2,4,5,6,7,8,9};
        String formatStr = "\n---------------%s---------------\n";

//        System.out.printf(formatStr,"bubble sort");
//        System.out.println(Arrays.toString(bubbleSort(A)));
//
//        shuffle(A);
////        System.out.println(Arrays.toString(A));
//
//        System.out.printf(formatStr,"insert sort");
//        insertSort(A);
//        System.out.println(Arrays.toString(A));

//        shuffle(A);
//        System.out.printf(formatStr,"select sort");
//        selectSort(A);
//        System.out.println(Arrays.toString(A));

//        shuffle(A);
//        System.out.printf(formatStr,"merge sort");
//        mergeSort(A,0,8);
//        System.out.println(Arrays.toString(A));

        shuffle(A);
        System.out.printf(formatStr,"quick sort");
        quickSort(A,0,8);
        System.out.println(Arrays.toString(A));


        
    }

}
