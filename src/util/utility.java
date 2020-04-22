package util;

import java.util.Random;

public class utility {
    public static final boolean DEV_MODE = true;

//    public static boolean isDebugMode(Context context){
//        return (context.get)
//    }

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
}
