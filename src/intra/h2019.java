package intra;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/2/15 f2019
 */
public class h2019 {
    /**
     * how to get the median by two heap?
     *
     * Idea: min heap and max heap, try to balance the size of them, let the difference of their size no more than 1.
     * Return: The 1st element in heap with largest size
     *
     * For the following, I will denote min heap by minH, and maxH for max heap, the size of them are denoted by ni, na respectively
     * Call of function delete() can be deleteMin for minH and deleteMax for maxH.
     * The insertion is important:
     * 1) If na==0 then insert(maxH, n, 0);
     * 2) If ni==0 then insert(minH, n, 0);
     * 3) Other situations can be complex, when n<maxH[1], n>minH[1], and else.
     *      a) If n<maxH[1]
     *              a1) If na<=ni then insert(maxH, n, na+1);
     *              a2) else then m = delete(maxH); insert(minH, m, ni+1); insert(maxH, n, na-1);
     *      b) If n>minH[1]
     *      *              b1) If ni<=na then insert(minH, n, ni+1);
     *      *              a2) else then m = delete(minH); insert(maxH, m, na+1); insert(minH, n, ni-1);
     *      c) Else
     *      *              c1) If na<=ni then insert(maxH, n, na+1);
     *      *              c2) else then insert(minH, n, ni+1);
     *
     *  See here for more details.
     *  https://blog.csdn.net/lidalong0408/article/details/7671562
    */

    private int ni;
    private int na;
    private int[] minH;
    private int[] maxH;

    public h2019(int[] arr){
        /* @Description:
         * @param n	The size of array which require to search median.
        * @Return:
        */
        ni=0; na=0;
        minH = new int[arr.length+1];
        maxH = new int[arr.length+1];
        maxH[0] = Integer.MIN_VALUE;
        minH[0] = Integer.MAX_VALUE;

        for(int i: arr){
            insertMinMax(i);
        }
    }

    public void insertMinMax(int n){
        if(na==0) insert(true,n,1);
        else if(ni==0) insert(false,n,1);
        else if(n<maxH[1]){
            if(na<=ni){insert(true,n,na+1);}
            else{
                int m = delete(true);
                insert(false,m,ni+1);
                insert(true,n,na+1);
            }
        }
        else if(n>minH[1]){
            if(ni<=na){insert(false,n,ni+1);}
            else{
                int m = delete(false);
                insert(true,m,na+1);
                insert(false,n,ni+1);
            }
        }
        else{
            if(na<=ni){insert(true,n,na+1);}
            else{
                insert(false,n,ni+1);
            }
        }
    }


    private void insert(boolean isMaxH, int n, int i){
        if(isMaxH) {
            // do for maxH
            if(na==0) {maxH[1] = n; na++; return;}
            else{
                while(i>1 && n>maxH[i/2]){
                    maxH[i] = maxH[i/2];
                    i /= 2;
                }
                maxH[i] = n;
                na++;
                return;
            }
        }
        else{
            // do for minH
            if(ni==0) {
                minH[1] = n; ni++; return;
            }
            else{
                while(i>1 && n<minH[i/2]){
                    minH[i] = minH[i/2];
                    i /= 2;
                }
                minH[i] = n;
                ni++;
                return;
            }
        }
    }

    private int delete(boolean isMaxH){
        int nD = isMaxH? maxH[1]: minH[1];

        if(isMaxH){
            //deleteMax for maxH;
            int i = 1;
            int nTmp = maxH[na];
            int j = minChild(isMaxH,i);
            while (j!=0 && maxH[i] > nTmp){
               maxH[i] = maxH[j];
               i = j;
               j = minChild(isMaxH,i);
            }
            maxH[i] = nTmp;
            na--;
        }
        else{
            //deleteMin for minH;
            int nTmp = minH[ni];
            int i = 1;
            int j = minChild(isMaxH,i);
            while(j!=0 && minH[i]<nTmp){
                minH[i] = minH[j];
                i = j;
                j = minChild(isMaxH,i);
            }
            minH[i] = nTmp;
            ni--;
        }

        return nD;
    }

    private int minChild(boolean isMaxH, int i){
        int j = 0;
        int n = isMaxH?na:ni;
        if(2*i>n){return j;}

        if(isMaxH){
            if(2*i+1<=n && maxH[2*i+1]> maxH[2*i]){ j = 2*i+1;}
            else { j = 2*i;}
        }else{
            if(2*i+1<=n && minH[2*i+1]< minH[2*i]){ j = 2*i+1;}
            else { j = 2*i;}
        }

        return j;
    }

    public double median(){
        // return the 1st element of one heap whose size is largest or return the mean of the 1st elements of both.

        return na>ni?maxH[1]:(na==ni?((minH[1]+maxH[1])*1.0/2):minH[1]);
    }

    public static void main(String[] args){
        int[] arr = {1,2,4,5,6,7,8,9};

        h2019 median = new h2019(arr);
        System.out.println(median.median());
    }

}
