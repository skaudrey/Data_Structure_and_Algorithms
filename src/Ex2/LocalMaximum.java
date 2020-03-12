package Ex2;

public class LocalMaximum {

    public static void findLocalMax(int[] arr){
        /*
        * My solution: find the 1st one that make it larger than the next; then check the rest, whether the next is smaller
        * than it; and continue to do it. For one is already confirmed as a local maximum, the next local maximum starts at
        * least from the next 2nd.
        *
        * For the solution given by TA, he use a flag to denote whether the current value is already large than the part before
        * him, then check whether the next is smaller than current while the flag is true. This is clear and the codes are
        * simple, the idea should be maintained /
        * */
        int nSize = arr.length;

        int i=0;
        while(arr[i+1]<=arr[i] && i<=nSize-2){
            i++;
        }
        i++;
        if(i==nSize-1){return;} //If for the end, there are still no arr[i]>arr[i-1], then no local maximum exist.
        int j = i+1;
        for(;j<=nSize-1 && i <= nSize-2;){
            if(arr[i]>arr[j] && arr[i]>arr[i-1]){
                System.out.printf("For sub-array %d%d%d:  ",arr[i-1],arr[i],arr[j]);
                System.out.print(arr[i]+"\n");
                i += 2; j = i+1;
                // Be careful for the increment, for a[i] who has been the local maximum,
                // a[i+1] will never be a local maximum.
            }
            else{
                i++;j++;
            }
        }
        System.out.println("---------Done---------");
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        int[] arr2 = {3,3,4,3,4,3};

        findLocalMax(arr);
        findLocalMax(arr2);
    }
}
