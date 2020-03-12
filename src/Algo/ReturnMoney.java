package Algo;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Some
 * @Description: TODO
 * @date 2020/3/6 Return money: greedy and DP
 */
public class ReturnMoney {
    private static Integer[] coinage = {1,5,10,25,100};

    public static void sortCoinage(){
        Arrays.sort(coinage, Collections.reverseOrder());
    }

    public static int[] returnGreedy(int nM){
        int[] q = new int[coinage.length];
        sortCoinage();
        if(nM==0)
            return null;
        int i = 0;
        while(nM>0 && i<coinage.length){
            if(nM-coinage[i]>=0){
                q[i] += 1;
                nM -= coinage[i];
                if(i<coinage.length-1)
                    i++;
            }
            else {
                i++;
            }
        }

        if(i==coinage.length && nM!=0)
            return null;

        return q;
    }

    public static int[] getPlan(int[][] dp){
        int[] q = new int[dp.length];
        int i = dp.length-1; int j = dp[0].length-1;

        int n = dp[i][j];
        while(i>0 && j >0){
            if(n == dp[i-1][j])
                i--;
            else if(n>dp[i-1][j-coinage[i]]){
                q[i] = n - dp[i-1][j-coinage[i]];
                n = dp[i-1][j-coinage[i]];
                j = j-coinage[i];
                i--;
            }
        }
        q[i] = dp[i][j];

        return q;
    }

    public static int[][] returnDP(int nM){
        Arrays.sort(coinage);
        int[][] dp = new int[coinage.length][nM+1];

        for(int i = 0; i<coinage.length;i++){
            dp[i][0] = 0;
        }
        for(int i = 0; i<coinage.length;i++){
            for(int j = 1; j<=nM;j++){
                int nTmp1 = j-coinage[i]<0? Integer.MAX_VALUE:1+dp[i][j-coinage[i]];
                int nTmp2 = i-1<0? Integer.MAX_VALUE:dp[i-1][j];
                dp[i][j] = Math.min(nTmp1,nTmp2);
            }
        }

        return dp;
    }

    public static void main(String[] args){
        String formstr = "----------%s----------\n";
        int nM = 38;

        System.out.format(formstr,"greedy algorithm");
        System.out.println(Arrays.toString(returnGreedy(nM)));

        System.out.format(formstr,"dynamic programming algorithm");
        int[][] dp = returnDP(nM);
        System.out.println(dp[coinage.length-1][nM]);
        System.out.println(Arrays.toString(getPlan(dp)));

    }

}
