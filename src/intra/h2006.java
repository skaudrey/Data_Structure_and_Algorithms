package intra;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/2/13 h2006
 */
public class h2006 {
    // problem 2: implement a stack by only one queue.
    private Queue<Integer> sq = new LinkedList<>();
    int nTop = -1;

    public void push(int n){
        sq.offer(n);
        nTop++;
    }
    public int pop(){
        int nD = pop(sq,nTop);
        return nD;
    }



    private int pop(Queue<Integer> q, int i){
        if(i--==0) {int nD = q.poll();return nD;}
        int m = q.poll();
        int n = pop(q,--nTop);
        q.offer(m);
        return n;
    }

    @Override
    public String toString() {
        return sq.toString();
    }

    public static void main(String[] args){
        int[] arr = {1,23,4,6,5,7,8};
        h2006 sq = new h2006();
        for(int i: arr){
            sq.push(i);
        }

        System.out.println(sq.toString());

        System.out.println(sq.pop());
        System.out.println(sq.toString());
        System.out.println(sq.pop());
        System.out.println(sq.toString());
        System.out.println(sq.pop());
        System.out.println(sq.toString());
    }

}
