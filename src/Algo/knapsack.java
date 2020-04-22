package Algo;

import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Some
 * @Description: TODO
 * @date 2020/3/6 Knapsack
 */
public class knapsack {
    static wv[] wv;

    public knapsack(int[] w, int[] v){
        wv = new wv[w.length];

        for(int i = 0; i<w.length; i++){
            wv[i] = new wv(w[i],v[i]);
        }
        Arrays.sort(wv);
    }

    public class wv implements Comparable<wv> { //let the array be sorted by value per unit
        int w;
        int v;

        public wv(int w, int v){
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(wv data) {
            double i = 1.0/this.w*this.v - data.v*1.0/data.w;
            int j = i>0? -1:(i==0?0:1);
            return j;
        }
    }

    public static double[] packGreedy(int nW){
        double[] q = new double[wv.length];
        double nV = 0;
        int i = 0;
        while(nW>0 && i<q.length){
            if(nW-wv[i].w>=0){
                q[i] = 1;
                nW -= wv[i].w;
                nV += wv[i].v;
                i++;
            }
            else{
                q[i] = nW*1.0/wv[i].w;
                nW = 0;
            }
        }
        System.out.format("Total value: %.2f\n" , nV);
        return q;
    }

    public static int[] getPlanZeroOne(int[][] dp){
        int[] q = new int[wv.length];
        int i = wv.length-1; int j = dp[0].length-1;
        int n = dp[i][j];
        System.out.println("The maximum value: "+n);

        while(i!=0 && j>=0){
            if(n==dp[i-1][j]) { i--;}
            else if(n==dp[i-1][j-wv[i].w]+wv[i].v){
                q[i] = 1;
                j = j-wv[i].w;
                i--;
                n = dp[i][j];
            }
        }
        q[i] = n>0? 1:0;

        return q;
    }

    public static int[] getPlanComplete(int[][] dp){
        int[] q = new int[wv.length];
        int i = wv.length-1; int j = dp[0].length-1;
        int n = dp[i][j];
        System.out.println("The maximum value: "+n);

        while(i!=0 && j>=0){
            if(n==dp[i][j-wv[i].w]+wv[i].v){
                q[i] += 1;
                j = j-wv[i].w;
                n = dp[i][j];
            }
            else if(n==dp[i-1][j]) { i--;}
        }
        q[i] += n>0? n/wv[i].v:0;

        return q;
    }

    public static int[][] packDPZeroOne(int nW){
        int[][] dp = new int[wv.length][nW+1];
        for(int i=0; i<dp.length;i++)
            dp[i][0] = 0;
        for(int j=1; j<nW+1; j++)
            dp[0][j] = wv[0].w<=j? wv[0].v: Integer.MIN_VALUE;

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<nW+1; j++){
                if( j-wv[i].w<=0 ){
                    dp[i][j] = Math.max(Integer.MIN_VALUE, dp[i-1][j]);
                }
                else{
                    dp[i][j] = Math.max(wv[i].v+dp[i-1][j-wv[i].w], dp[i-1][j]);
                }
            }
        }

        return dp;
    }


    public static int[][] packDPComplete(int nW){
        int[][] dp = new int[wv.length][nW+1];
        for(int i=0; i<dp.length;i++)
            dp[i][0] = 0;
        for(int j=1; j<nW+1; j++)
            dp[0][j] = j-wv[0].w<0? 0: dp[0][j-wv[0].w]+wv[0].v;

        for(int i=1; i<dp.length; i++){
            for(int j=1; j<nW+1; j++){
                if( j-wv[i].w<=0 ){
                    dp[i][j] = Math.max(Integer.MIN_VALUE, dp[i-1][j]);
                }
                else{
                    dp[i][j] = Math.max(wv[i].v+dp[i][j-wv[i].w], dp[i-1][j]);
                }
            }
        }

        return dp;
    }

    public static int knapsackdc(int nW, int nV){
        //main: to get optimal, call it by knapsackdc(10,0)
        int nlow = 0;

        while(nW<wv[nlow].w) nlow++; //get the 1st taken-in object.
        int nw1 = wv[nlow].w, nv1 = wv[nlow].v;
        return knapsackdc(nW-nw1,0 ,nlow) + knapsackdc(nw1,nv1, nlow);
    }
    private static int knapsackdc(int nW, int nV, int low){
        if(nW==0 || low==wv.length) return 0; // deep down to the zero unit remained.
        while(nW<wv[low++].w); // get the 1st taken-in object
        low--; // remove redundant increment of pointer.
        int nv_next = wv[low].v;
        int nw_next = nW-wv[low].w;
        return  nv_next + knapsackdc(nw_next,nV,low);
    }

    public static void main(String[] args){
        int nW = 10;
        int[] w = {2,3,5};
        int[] v = {2,4,6};
        knapsack sac = new knapsack(w,v);

        String formstr = "----------%s----------\n";
//
//        System.out.format(formstr,"greedy algorithm");
//        System.out.println(Arrays.toString(sac.packGreedy(nW)));

//        System.out.format(formstr,"divide and conquer");
//        System.out.println(knapsackdc(nW,0));

        System.out.format(formstr,"zero-one: dp algorithm");
        int[][] dp = sac.packDPZeroOne(nW);
//        System.out.println(Arrays.toString(getPlanZeroOne(dp)));
//        System.out.format(formstr,"Complete: dp algorithm");
//        int[][] dpC = sac.packDPComplete(nW);
//        System.out.println(Arrays.toString(getPlanComplete(dpC)));

    }

}
