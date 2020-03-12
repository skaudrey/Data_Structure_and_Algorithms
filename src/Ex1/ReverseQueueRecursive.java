package Ex1;

import java.util.LinkedList;
import java.util.Queue;

public class ReverseQueueRecursive {

    public static Queue<Integer> reverse(Queue<Integer> arr){
        int n = arr.remove();
        if(!arr.isEmpty()) {reverse(arr);}
        arr.add(n); //When the stopped step is finally caught, the arr is null, and n= the last one in arr, and thus
        // arr.add get {4}
        return arr;
    }

    public static void main(String[] args){
        Queue<Integer> arr = new LinkedList<Integer>();

        for(int i=0; i<5; i++){
            arr.add(i);
        }
        System.out.println(arr.toString());

        reverse(arr);
        System.out.println(arr.toString());
    }

}
