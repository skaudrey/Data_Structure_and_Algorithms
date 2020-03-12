package Ex2;
import java.util.Arrays;

public class DeleteShift {

    public static void delete(int[] arr, int d){
        int i = 0; int j =  arr.length-1;
        while(i<j){
            if(arr[j]==d){ arr[j]=0; j--; }
            else if(arr[i]!=d){i++;}
            else{
                arr[i] = arr[j];
                arr[j] = 0;
                i++; j--;
            }
        }
    }

    public static void deleteShift(int[] arr, int[] d){
        /**
         * Well, what I implemented is getting the indices in the first iteration, then delete and move in the next.
         * but the deleted items are more than one value.
         *
         * The demo given by TA delete both from the start and the end.
         * */

        int[] ind = new int[arr.length];
        Arrays.fill(ind,arr.length);
        int nInd = 0;
        Arrays.sort(d);
        int nComp = 0;
        for(int j=0; j<d.length; j++){ // Transverse to get the index of all fitted elements in arr compared with d.
            nComp = d[j];
            for(int i=0; i<arr.length;i++){
                if(nComp == arr[i]) {
                    ind[nInd] = i; nInd++;
                }
            }

        }
        Arrays.sort(ind);
        // shift and so as to delete
        for(int i=0; i< nInd-1; i++){
            int s = ind[i]; int e = ind[i+1];
            if(e==s+1){continue;}
            for(int n=s+1, k=0;k<=e-s && n<=e; n++,k++){
                arr[k+s-i] = arr[n];
            }
        }
        for(int i=0; i<arr.length-nInd;i++){
            System.out.print(arr[i]+" ");
        }
        for(int j=0; j<nInd;j++){
            System.out.print("? ");
        }
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,1,2,3,1,2,3};
        int[] d = {3,4};
        delete(arr,3);
        System.out.println(Arrays.toString(arr));
        deleteShift(arr,d);
    }
}
