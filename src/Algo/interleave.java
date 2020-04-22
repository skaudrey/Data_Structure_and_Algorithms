package Algo;

import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Algo
 * @Description: TODO
 * @date 2020/4/12
 */
public class interleave {
    static int[][] dp;

    public static void lcs(String X, String Y){
        int m = X.length();
        int n = Y.length();

        dp =  new int[m+1][n+1];

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(X.charAt(i-1)==Y.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        System.out.print("   ");
        for(int i=0; i<n; i++){
            System.out.print(Y.charAt(i)+" &");
        }
        System.out.print('\n');
        for (int i = 1; i < m + 1; i++) {
            char tmp = X.charAt(i-1);
            System.out.println(tmp+" "+ Arrays.toString(dp[i]).replace(',','&'));
        }

    }



    public static void main(String[] args){
        String X = "GTACA";
        String Y = "AGCAT";

        lcs(X,Y);
    }
}
