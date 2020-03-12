package Ex5;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex5
 * @Description: TODO
 * @date 2020/2/9 Circle d'Eratosthène
 */
public class Eratosthene {

    EratoNode[] arr;


    public Eratosthene(int n){
        arr = new EratoNode[n];
        int nInd = 1;
        arr[0] = new EratoNode(Integer.MAX_VALUE,false);
        nInd++;
        while(nInd<=n){
            arr[nInd-1] = new EratoNode(nInd++, false);
        }
    }

    private List<Integer> findPrime() {
        int n = arr.length;
        List<Integer> primes = new ArrayList<Integer>();
        primes.add(Integer.MAX_VALUE);
        primes.add(2);
        for(int i = 3; i <= n; i++) {
            int tmp = (int)Math.sqrt(i) + 1;
            for(int j = 2; j <= tmp; j++) {
                if(i % j == 0)	break;
                if(j == tmp)	primes.add(i);
            }
        }
        return primes;
    }

    public void delete(){
        List<Integer> ls = findPrime();
        for(int i:ls){
            if(i== Integer.MAX_VALUE)continue;
            int iInd = 0;
            System.out.printf("---- prime: %d ----- deleted-------\n",i);
            while(i*i+iInd*i<=arr.length){
                if(!arr[i*i+iInd*i-1].isDeleted){
                    arr[i*i+iInd*i-1].isDeleted = true;
                    System.out.print(arr[i*i+iInd*i-1].nData + " ");}
                iInd++;
            }
            if(i*i>arr.length) break;
            System.out.print("\n");
        }

        for(EratoNode i: arr){
            if(i.nData==Integer.MAX_VALUE) continue;
            System.out.print(!i.isDeleted? i.nData+" ": "");
        }


    }

    private class EratoNode{
        public int nData;
        public boolean isDeleted;
        public EratoNode(int nData, boolean isDeleted){
            this.nData = nData;
            this.isDeleted = isDeleted;
        }
    }

    public static void main(String[] args){
        String formatStr = "\n---------------%s---------------\n";
        Eratosthene e = new Eratosthene(120);
        System.out.printf(formatStr,"a.5: Crible d’Eratosthène");
        e.delete();
    }

}
