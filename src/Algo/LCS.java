package Algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Algo
 * @Description: TODO
 * @date 2020/4/12 longest common sequence
 */
public class LCS {
    static int[][] dp;
    static ArrayList<String> setOfLCS = new ArrayList<>();

    public static int[][] lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        dp = new int[m + 1][n + 1];
        int[][] direc = new int[m + 1][n + 1]; // denote all the direction in each cell so as to trace back.
        // Concretely, 1 for north-west, 2 for north, and 3 for west.

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    direc[i][j] = 1;
                }
                else if(dp[i-1][j ]< dp[i ][j-1]){
                    dp[i][j] = dp[i][j - 1];
                    direc[i][j] = 2;
                }else{
                    dp[i][j] = dp[i-1][j];
                    direc[i][j] = 3;
                }
            }
        }
        String[] direc_symb = {"NW","N","W"};
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.print(Y.charAt(i) + " &");
        }
        System.out.print('\n');
        for (int i = 1; i < m + 1; i++) {
            char tmp = X.charAt(i - 1);
            System.out.println(tmp + " " + Arrays.toString(dp[i]).replace(',', '&'));
        }

        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.print(Y.charAt(i) + "  ");
        }
        System.out.print('\n');
        for (int i = 1; i < m + 1; i++) {
            char tmp = X.charAt(i - 1);
            String outstr = Arrays.toString(direc[i]).replace(',', ' ');
            outstr = outstr.substring(2);
            outstr = outstr.replace(',',' ');
            outstr = outstr.replace("1","NW");
            outstr = outstr.replace("2","N");
            outstr = outstr.replace("3","W");
            outstr = outstr.substring(0,outstr.length()-1);
            System.out.println(tmp + "" + outstr);
        }

        return direc;
    }

    public static void printOneCLS(int[][] direc, String X, int i, int j){
        if(i==0 || j==0){
            return;
        }
        if(direc[i][j]==1){
            printOneCLS(direc,X,i-1,j-1);
            System.out.print(X.charAt(i-1)+"  ");
        }
        else if(direc[i][j]==2)
            printOneCLS(direc,X,i,j-1);
        else
            printOneCLS(direc, X, i-1, j);
    }



    public static void traceBack(String X, String Y, int i, int j, String lcs_str) {
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs_str += X.charAt(i - 1);
                --i; --j;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) --i;
                else if (dp[i - 1][j] < dp[i][j - 1]) --j;
                else{ // equal
                    traceBack(X, Y, i - 1, j, lcs_str);
                    traceBack(X, Y, i, j - 1, lcs_str);
                    return;
                }
            }
        }
        setOfLCS.add(reverse(lcs_str));
    }

    private static String reverse(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }

    public static boolean isInterLeave(String X, String Y) {
        boolean flag = false;

        ArrayList<String[]> parts = new ArrayList<>();

        for (String s : setOfLCS) {
            String[] tmp = splitByLCS(X, Y, s);
            if (tmp[0] == null)
                continue;
            else {
                parts.add(tmp);
                flag = true;
            }
        }
        if (flag) {
            System.out.println(" The two strings to interleave string X and Y:");
            System.out.println(parts.get(0)[0]);
            System.out.println(parts.get(0)[1]);
        } else {
            System.out.println(" The two strings cannot be interleaved by string X and Y:");
        }

        return flag;
    }

    public static String[] splitByLCS(String X, String Y, String s) {
        String[] asplit = new String[2];
        int i = 0, j = 0,k=0;
        int m = X.length(), n = Y.length();
        if (m != n)
            return asplit;

        String s1 = "", s2 = "";
        while (i<m && k<s.length()){
            if(s.charAt(k)!=X.charAt(i))
                s1 += X.charAt(i);
            else{ k++;}
            i++;
        }
        k=0;
        while (j<n && k<s.length()){
            if(s.charAt(k)!=Y.charAt(j))
                s2 += Y.charAt(j);
            else { k++;}
            j++;
        }

        if(s1.compareTo(s2)==1){
            asplit[0] = s;
            asplit[1] = s1;
        }

        return asplit;
    }

    public static void printLCS() {
        System.out.println("All possible LCS, the possible interleaved strings will be chosen from them");
        for (String s : setOfLCS) {
            System.out.println(s);
        }
    }

    private static int Cmn(int m, int n){
        if(m==n || m==0)
            return 1;
        else
            return Cmn(m,n-1)+Cmn(m-1,n-1);
    }

    public static int ldss(Integer[] X){
        Integer[] Y = X.clone();
        Arrays.sort(Y, Collections.reverseOrder());
        int n = X.length;
        dp = new int[n+1][n+1];

        int posNum = 0;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(X[i-1]==Y[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        System.out.println("The length of longest decreasing sequence: "+ dp[n][n]);

        posNum = Cmn(2,dp[n][n]);
        System.out.println("The number of inversions: "+ posNum);
        return posNum;
    }


    public static void main(String[] args) {
        String X = "GTACA";
        String Y = "AGCAT";

        int[][] direc = lcs(X, Y);
        printOneCLS(direc,X,X.length()-1,Y.length()-1);
//
//        traceBack(X, Y, X.length(), Y.length(), "");
//
//        printLCS();
//        isInterLeave(X,Y);

//        Integer[] A = {9, 1, 4, 2, 7, 3, 6, 5, 8};
//        ldss(A);

    }
}
