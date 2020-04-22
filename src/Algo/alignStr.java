package Algo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Algo
 * @Description: TODO
 * @date 2020/4/11 String alignments
 */
public class alignStr {

    static int[][] dp;
    static ArrayList<String[]> ways = new ArrayList<>();

    public static int[][] align_dp(String S, String T) {
        int m = S.length(), n = T.length();
        dp = new int[m + 1][n + 1];

        for (int i = 0; i < n + 1; i++)
            dp[0][i] = i;
        for (int i = 0; i < m + 1; i++)
            dp[i][0] = i;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int nc_ij = S.charAt(i - 1) == T.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(nc_ij, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
            }
        }
        System.out.print("   -  ");
        for(int i=0; i<n; i++){
            System.out.print(T.charAt(i)+"  ");
        }
        System.out.print('\n');
        for (int i = 0; i < m + 1; i++) {
            char tmp = '-';
            if(i>0)
                tmp = S.charAt(i-1);
            System.out.println(tmp+" "+Arrays.toString(dp[i]));
        }

        return dp;
    }


//    private static int findAllWays(String S, String T, int nway, String[] away, int m){
//
//        int i = S.length(), j=T.length();
//
//        while(true){
//            if((i==0 && j==0) && away[0].length()==m){
//                ways.add(away.clone());
//                nway++; //only when it gets to 0 position can it be valid.
////                Arrays.fill(away,"");
//                return nway;
//            }
//            if(i>0&&j>0&&S.charAt(i-1)==T.charAt(j-1)){
//                away[0] += S.charAt(i-1);
//                away[1] += T.charAt(j-1);
//                nway = findAllWays(S.substring(0,i-1),T.substring(0,j-1),nway,away,m);
//                i--;j--;
//            } else{
//                boolean if_r = false, if_d = false; // if_replacement, if_delete
//
//                // replacement
//                if(i>0&&j>0&&dp[i][j] == dp[i-1][j-1]+1){
//                    away[0] += S.charAt(i-1);
//                    away[1] += T.charAt(j-1);
//                    i--; j--;
//                    if_r = true;
//                }
//
//                //delete the element in column.
//                if(i>0&&dp[i][j]==dp[i-1][j]+1){
//                    if(!if_r){
//                        away[0] += S.charAt(i-1);
//                        away[1] += '-';
//                        i--;
//                    }
//                    else{ // one more possibility
//                        String[] away2 = new String[2];
//                        //remove the last operation (replacement) and then change to deletion ops
//                        if(away[0].length()>0){
//                            away2[0] = away[0].substring(0,away[0].length()-1);
//                            away2[1] = away[1].substring(0, away[1].length()-1);
//                        }
//                        // add operation
//                        away2[0] += S.charAt(i);
//                        away2[1] += '-';
//
//                        nway = findAllWays(S.substring(0,i),T.substring(0,j+1),nway,away2,m);
//                    }
//                    if_d = true;
//                }
//                // insertion
//                if(j>0&&dp[i][j]==dp[i][j-1]+1){
//                    if(!if_r && !if_d){
//                        away[0] = away[0] + '-';
//                        away[1] = away[1] + T.charAt(j-1);
//                        j--;
//                    }
//                    else{
//                        String[] away3 = new String[2];
//                        //remove the last operation (replacement) and then change to insertion ops
//                        if(away[0].length()>0){
//                            away3[0] = away[0].substring(0,away[0].length()-1);
//                            away3[1] = away[1].substring(0, away[1].length()-1);
//                        }
//
//                        // add operation
//                        away3[0] += '-';
//                        away3[1] += T.charAt(j);
//
//                        // recursion for next step
//                        nway = findAllWays(S.substring(0,i+1),T.substring(0,j),nway,away3,m);
//                    }
//                }
//            }
//        }
//
////        return nway;
//    }
//
//    public static int getNumOfWays(String S, String T, int nway){
//
//        int i = S.length(), j=T.length();
//
//        while(true){
//            if(i==0 || j==0){
//                nway++; break;
//            }
//            if(S.charAt(i-1)==T.charAt(j-1) ){
//                i--; j--;
//            }else{
//                boolean if_r = false, if_d = false; // if_replacement, if_delete
//
//                // replacement
//                if(dp[i][j] == dp[i-1][j-1]+1){
//                    i--; j--;
//                    if_r = true;
//                }
//                //delete the element in column.
//                if(dp[i][j]==dp[i-1][j]+1){
//                    if(!if_r){ i--; }
//                    else{ // one more possibility
//                        nway = getNumOfWays(S.substring(0,i),T.substring(0,j+1),nway);
//                    }
//                    if_d = true;
//                }
//                // insertion
//                if(dp[i][j]==dp[i][j-1]+1){
//                    if(!if_r && !if_d){ j--;}
//                    else{
//                        // recursion for next step
//                        nway = getNumOfWays(S.substring(0,i+1),T.substring(0,j),nway);
//                    }
//                }
//            }
//        }
//
//        return nway;
//    }

    public static int getNumOfWays() {
        int m = dp.length, n = dp[0].length;
        int[][] dp_num = new int[m][n];

//        Arrays.fill(dp_num[0],1);
        for (int i = 0; i < m; i++)
            dp_num[i][0] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                boolean[] if_add = {
                        (dp[i][j] == (dp[i - 1][j - 1])),
                        (dp[i][j] == (dp[i - 1][j - 1] + 1)),
                        dp[i][j] == (dp[i - 1][j] + 1),
                        dp[i][j] == (dp[i][j - 1] + 1),

                };
                int[] if_num = {
                        dp_num[i - 1][j - 1],
                        dp_num[i - 1][j - 1],
                        dp_num[i - 1][j],
                        dp_num[i][j - 1]
                };
                boolean flag = false;
                for (int k = 1; k < if_num.length; k++) {
                    if (if_add[k]) {
                        flag = true;
                        dp_num[i][j] += if_num[k];
                    }
                }
                if (if_add[0] && !flag)
                    dp_num[i][j] = if_num[0];

            }
        }
        for (int i = 0; i < m; i++)
            System.out.println(Arrays.toString(dp_num[i]));
        return dp_num[m - 1][n - 1];
    }

    private static String reverse(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

//    public static int printAllChanges(String S, String T){
//        String[] away = new String[2];
//        Arrays.fill(away,""); // need initialization otherwise it is null
//        int m = S.length(), n=T.length();
//        m = m>=n? m: n;
//
//        int nway = findAllWays(S,T,0,away,m+1);
//        System.out.println("There are "+ nway + " possible ways to get optimal editing distance");
//
//        int i=1;
//        for(String[] ar: ways){
//            System.out.println(i++ + "-th method:");
//            System.out.println(reverse(ar[0]));
//            System.out.println(reverse(ar[1]));
//        }
//
//        return nway;
//    }


    public static void main(String[] args) {
        String S = "ABACAA";
        String T = "AABAACAAA";
        String S1 = "AGGAGGA";
        String T1 = "AAGCTAAG";
        align_dp(S, T);

//        printAllChanges(S,T);

        System.out.println("get nums");
        System.out.println(getNumOfWays());


    }
}
