package Ex2;
import java.lang.reflect.Array;
import java.util.Arrays;
public class ReverseTable {

    public static void reverse(int[] arr){
        // need to change the values at each position.
        int nSize = arr.length;
        int i=0;
        int j = nSize-1;
        while(i<j){// comparision: n/2; setting: n
            int nTemp = arr[i];
            arr[i] = arr[j];
            arr[j] = nTemp;
            i++; j--;
        }
        // O(n)
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        reverse(arr);
        System.out.println(Arrays.toString(arr));
    }
}
