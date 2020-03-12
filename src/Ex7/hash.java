package Ex7;

import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex7
 * @Description: TODO
 * @date 2020/3/10 H0-isUniform; H1-count the non-unique elements; H2: even if reorder the
 */
public class hash {
    /* @Description:
        1. H0: Check the hash value, where x=1,2,...,11, and the hashfunction is x^2+1
        2. H1:
            a) For hash, new a hash table in size n, and the hash function is A[i]%n. Put the conflicted A[i] into an array
            b) Use heap, each time if the inserted node is already in heap, counting.
        3. H2:
            I am not sure the meaning of it, how to reorder by linear measurement. It feels like even if you did
            randomize the permutation of insertion, it still will ends with long bloc as long as they have the same
            hash key.
        4. H3: The average cost for linear search while building hash table
            The one need to be measured: \bar{c} = \frac{1}{n} \sum_{i\colon T[i]\ne\mathsf{null}} c(i)
    */

    private int nM;

    public hash(int M){
        this.nM = M;
    }

    private int hashFunc(int x){
        return x%nM;
    }

    public double countAvgDetect(int[] arr){
        // The space complexity, is it O(1)?? I used O(M) auxiliary space
        //H3-b: while insertion: do change the visited[i] to true if it is filled with one x; while deleting,
        // change it to false.
        boolean[] visited = new boolean[nM];
        Arrays.fill(visited,false);
        int nC = 0;

        int i=0; int j=hashFunc(arr[i]);
        visited[j] = true; i++;
        nC+=1;
        int k = 0;

        while(i<arr.length){
            j = hashFunc(arr[i]);
            while(visited[j]){
                j=(++j)%nM;
                nC++;
            }
            visited[j] = true;
            nC++;
            i++;
        }

        return nC*1.0/arr.length;
    }

    private int hashFucH4(double x, int n){
        return (int)Math.floor(2*n*x);
    }

    public void insertH4(double x, double[] G){
        int n = (G.length)/2;
        int i = hashFucH4(x,n);
        while(G[i]>=0 && G[i]<1){
            i = (i+1)%(2*n);
        }
        G[i] = x;
    }

    public void hashsort(double[] F){
        // According to the formula for linear search, each items requires 5/2 times detects, and thus
        // (5/2)*n+4*n(compare and copy)=6.5n, is linear.
        // For the worst case, to insert each compares n times, then it's theta(n^2)
        double[] G =  new double[2*F.length];
        Arrays.fill(G,Double.NaN);
        int j = 0; int i =0;

        for(double k:F)
            insertH4(k,G);

        while(i<G.length){
            if(G[i]>=0 && G[i]<1)
                F[j++] = G[i];
            i++;
        }

    }

    public boolean isValid(int[] H){
        boolean isValid = true;

        for(int i: H){
            if(i<Integer.MAX_VALUE){
                int j = hashFunc(i);
                if(H[j]==Integer.MAX_VALUE)
                    return !isValid;
                boolean flag = (H[j]==i);
                int k = 1;
                while(!flag && k<=H.length){
                    j = (j+1)%H.length;
                    flag = (H[j]==i) && H[j]<Integer.MAX_VALUE;
                    k++;
                }
                if(k>H.length ){
                    return !isValid;
                }
            }
        }

        return isValid;
    }

    public static void main(String[] args){
        int[] arr = {65,76,66,71,88,70,84,68,78};
        hash hasher = new hash(11);
        String formstr = "\n----------%s----------\n";
        System.out.format(formstr,"H3-a-average detecting number");
        System.out.println(hasher.countAvgDetect(arr));

        System.out.format(formstr,"H4-a-hash sort");
        double[] F = {.32,.36,.31,.99,.15,.97};
        hasher.hashsort(F);
        System.out.println(Arrays.toString(F));

        int[] H = {76,66,88,68,70,71,78,84,Integer.MAX_VALUE,Integer.MAX_VALUE,65};
        int[] H1 = {76,66,88,68,70,71,78,84,Integer.MAX_VALUE,Integer.MAX_VALUE,75};
        System.out.format(formstr,"H5-is Valid");
        System.out.format(formstr,Arrays.toString(H));
        System.out.println(hasher.isValid(H));
        System.out.format(formstr,Arrays.toString(H1));
        System.out.println(hasher.isValid(H1));
    }
}
