package Ex6;

import Ex4.HeapArrSent;
import util.utility;

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
                utility.swap(A,i,nMinInd);
        }
    }

    public static void mergeSort(int[] A, int nl, int nr){
        if(nr-nl<2)return;

        int m = (nl+nr)/2;
        mergeSort(A,nl,m);
        mergeSort(A,m+1,nr);
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
        utility.shuffle(A);
        int i = partition(A,nl,nr);
        quickSort(A,nl,i);
        quickSort(A,i+1,nr);
    }

    private static int partition(int[] A, int nl, int nr){
        int i = nl+1;
        int j = nr-1;
        int pivot = A[nl];
        while(i<j){
            do{i++;} while (A[i]<pivot);
            do{j--;} while(A[j]>pivot && i<j);
            if(i>=j) break;
            utility.swap(A,i,j);
        }
        utility.swap(A,nl,j);
        return i;
    }

    public static int bitonique(int[] A){
        return bitonique(A,0, A.length-1);
    }
    private static int bitonique(int[] A, int low, int high){
        if(high<low)
            return -1;
        int nmid = (low+high)/2;
        if(high-low==1)
            return high;

        if(A[nmid]>A[low] && A[nmid]<=A[high]) low=nmid;
        else if(A[nmid]>A[high]) high=nmid;
        return bitonique(A,low,high);
    }

    public static int[] merge_bitonique(int[] B, int i){
        int[] b = new int[B.length];
        int nid = 0;
        int k=0, m=B.length-1;
        while(nid<B.length-1){
            if(B[k]>B[m]){
                b[nid++] = B[m--];
            }else if(B[k]<B[m]){
                b[nid++] = B[k++];
            }else{
                b[nid++] = B[k++];
                b[nid++] = B[m--];
            }
        }
        b[nid++] = B[i];
        return b;
    }

    private static void heapSort(int[] A, int i, int j){
        // heap sort in the nDelta window which is indicated implicitly by j-i;
        HeapArrSent htmp = new HeapArrSent();
        for(int k=i; k<=j; k++){
            htmp.insert(A[k]);
        }
        int[] r = htmp.heapsort();
        int ri = 0;
        for(int k=i; k<=j; k++){
            A[k] = r[ri++];
        }
    }

    public static void heapsort_local(int[] A, int nDelta){
        int n = A.length;
        for(int i=0; i<n;i++){
            int j = (i+1)*nDelta-1;
            j = j > n-1? n-1: j;
            heapSort(A,i*nDelta, j);
        }
    }

    public static void selectionsort_local(int[] A, int nDelta){
        int n = A.length;
        int i = 0;
        while(i<n){
            int minTmp = i;
            int j = i+nDelta-1;
            j = j>n-1? n-1: j;
            for(;j>i;j--){
                minTmp = A[j]<A[minTmp]? j: minTmp;
            }
            utility.swap(A,i,minTmp);
            i++;
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

//        shuffle(A);
//        System.out.printf(formatStr,"quick sort");
//        quickSort(A,0,8);
//        System.out.println(Arrays.toString(A));

//        int[] B = {1,2,4,5,9,8,7,6,5};
//        System.out.printf(formatStr,"t.2: bitonique--index of maximum");
//        int i = bitonique(B);
//        System.out.println(i);
//        System.out.printf(formatStr,"t.2: bitonique--sorting");
//        int[] b = merge_bitonique(B,i);
//        System.out.println(Arrays.toString(b));

//        int[] C = {4,1,2,5,9,8,7,6,5};
//        int nDelta = 3;
//        System.out.printf(formatStr,"t.3a: selection sort--local");
//        System.out.printf(formatStr,"t.3a: before selection sort (local)");
//        System.out.println(Arrays.toString(C));
//        selectionsort_local(C,nDelta);
//        System.out.printf(formatStr,"t.3a: after selection sort (local)");
//        System.out.println(Arrays.toString(C));
//
//        utility.shuffle(C);
//        System.out.printf(formatStr,"t.3b: sliding heap sort (local)");
//        System.out.printf(formatStr,"t.3b: before sliding heap sort (local)");
//        System.out.println(Arrays.toString(C));
//        heapsort_local(C,nDelta);
//        System.out.printf(formatStr,"t.3b: after sliding heap sort (local)");
//        System.out.println(Arrays.toString(C));


        System.out.printf(formatStr,"t.4b: nuts and bolts (quick sort)");
        int[] D = {1,2,3,4,5,6,7,8,9};
        nutsbolts nb = new nutsbolts(D);
        System.out.printf(formatStr,"t.4b:after sorting: nuts and bolts (quick sort)");
        nb.match();

    }

}
