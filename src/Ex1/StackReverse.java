package Ex1;
import java.util.Stack;
public class StackReverse {

    public static Stack<Integer> reverse(Stack<Integer> s){
        int n = s.pop();
        if(!s.isEmpty()) reverse(s);
        s.push(n);
        return s;
    }


    public static void main(String[] main){
        int[] a  = {1,2,3,4,5};
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<a.length;i++){
            s.push(a[i]);
        }
        s = reverse(s);
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }
}
