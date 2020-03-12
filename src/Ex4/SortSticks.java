package Ex4;

/**
 * @author Miao
 * @version V1.0
 * @program: ift6002
 * @Package: Ex4
 * @Description: TODO
 * @date 2020/1/31 Sort sticks by the position of the right
 */
public class SortSticks {
    // How??

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        int[] b = new int[5];
        int j=0;
        for(int i=0; i<5; ){
            b[j++] = arr[i++];
//            j++;
        }

        for(int i=0; i<5; i++){
            System.out.println(b[i]);
        }
    }
}
