package Algo;

import java.util.Arrays;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Algo
 * @Description: TODO
 * @date 2020/4/18 For directed graph, invalid for graph with negative cycle, but works for graph with negative edge
 */
public class floyd {

//    static int[][] adjMat =;

    public static void floyd(int[][] adjMat){
//        this.adjMat = adjMat.clone();
        printMat(adjMat,0);


        int n = adjMat.length;
        int[] visit = new int[n];
        for(int k = 0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    int tmp1 = adjMat[i][j];
                    int tmp2 = adjMat[i][k]+adjMat[k][j];
                    if(adjMat[i][k]==Integer.MAX_VALUE || adjMat[k][j]==Integer.MAX_VALUE)
                        tmp2 = Integer.MAX_VALUE;
                    if(tmp1>tmp2){
                        adjMat[i][j]=tmp2;
                        visit[j] = k;
                    }
                }
            }
            printMat(adjMat,k);
        }
        System.out.println("The preceding node for each node");
        System.out.println(Arrays.toString(visit));
    }

    private static void printMat(int[][] mat, int id){
        System.out.println("D_"+(id+1));
        for(int i=0; i<mat.length; i++)
            System.out.println(Arrays.toString(mat[i]));

    }

    public static void main(String[] args){
        int[][] D = {
                {0,5,Integer.MAX_VALUE,Integer.MAX_VALUE,},
                {50,0,15,5},
                {30,Integer.MAX_VALUE,0,15},
                {15,Integer.MAX_VALUE,5,0}};
        floyd(D);
    }
}
