package intra;
import java.util.Stack;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: intra
 * @Description: TODO
 * @date 2020/2/13 Winter 2009
 */
public class h2009 {

    private Stack<Integer> s = new Stack<>();


    public h2009(){
        s.push(2);
        s.push(3);
    }

    public void add2End(Stack<Integer> tmp,int n){
       if(tmp.isEmpty()){tmp.push(n); return;}
       int m = s.pop();
       add2End(s,n);
       s.push(m);

    }

    public static int[] getTopMinK(int k, int[] arr, int n){
        int[] hk = new int[k];
        int hk_size = 0;
        hk[hk_size++] = arr[0];

        for(int i=1; i<n; i++){
            if(hk_size<k) {
                swim(hk,i,hk_size++);
            }
            else{
                if(i<hk[k-1]){
                    hk[k-1]=i;
                    hk = swim(hk,i,k+1);
                }
                else{
                    hk = sink(hk,i);
                }
            }
        }

        return hk;
    }

    private static int[] swim(int[] h, int n, int i){
        // i is limited
        int k = h.length;
//        int nTmp = h[i-1]; // caution: i start from 1, to add in h, need to subtract 1
        while(i!=2 && h[i/2-1]>n){
            if(i<=k){
                h[i-1] = h[i/2-1];
            }
            i /= 2;
//            nTmp = h[i-1];
        }
        if(i>k){h[k/2-1]=n;}
        else{
            h[i] = n;
        }

        return h;

    }

    private static int[] sink(int[] h, int n){
        int k = h.length;
        int i = 1;
        int j = 0;

        while(i*2+1<k){
            int h1 = h[2*i-1];
            int h2= h[2*i];
            j = h1<h2? 2*i: 2*i+1;
//            if(j==k && h[j]>n){h[j] = n;}
            i = j;
        }
        if(i>k && h[i]>n){h[k-1] = n;}
        else{h[i-1] = n;}

        return h;
    }

    @Override
    public String toString() {
        String str = "";

        str = s.toString();
//        System.out.println(s);
        return str;
    }

    public static void main(String[] args){
        h2009 mytest = new h2009();
        mytest.add2End(mytest.s,8);
        System.out.println(mytest.toString());
    }
}
