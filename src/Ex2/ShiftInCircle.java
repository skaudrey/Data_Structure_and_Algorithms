package Ex2;

public class ShiftInCircle {

    public static int[] shiftInCircle(int[] arr, int nDelta){
        nDelta = nDelta%arr.length; // for nDelta > arr.length
        int nTemp = 0;
        for(int i=0;i<nDelta;i++){ // For nDelta, move nDelta times, each time there will be one step to make it.
            nTemp = arr[arr.length-1]; // Save the last one that is overwritten.
            for(int j=arr.length-2; j>=0; j--){
                arr[j+1]=arr[j];
            }
            arr[0] = nTemp;
        }
        return arr;
    }

    public static int[] shiftRecursive(int[] arr, int nDelta){
        /**
         * In my solution, I use divide-and-conquer to make it, also O(n)
         * But in the demo given by TA,
         * */
        nDelta %= arr.length;
        reverse(arr,0,arr.length-1);
        reverse(arr,0,nDelta-1);
        reverse(arr,nDelta,arr.length-1);
        return arr;
    }

    public static void reverse(int[] arr,int begin, int end){
        int length = end-begin+1;
        int half = length/2;
        for(int i=0; i<half; i++){
            int temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp; // Do not forget this line.
            begin++; end--;
        }
    }

    public static String toArray(int[] arr){
        String str = "";
        for(int i: arr){
            str += i + " ";
        }
        return str;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8};
        int[] newArr = shiftInCircle(arr,2);
        System.out.println(toArray(newArr));
        System.out.println(80*'-');

        int[] arr2 = {1,2,3,4,5,6,7,8};
        int[] newArr2 = shiftRecursive(arr2,2);
        System.out.println(toArray(newArr2));


    }
}
