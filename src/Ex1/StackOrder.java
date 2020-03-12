package Ex1;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.Stack;

public class StackOrder {

    /**
     *Write an algorithm to define whether the order poped is right for a given pushed sequence.
     * */

    public static boolean isPopedRight(int[] original, int[] poped){
        /*
        * Tips: use a stack to maintain the pushed items. Push an element in original to the stack, then compare it with
        * poped array, if it is equal, poped the one just been pushed, continue until the top of stack is not equal to
        * the element in poped array. Then take one step ahead for original, continue pushed and pop. At last, determine
        * whether the stack is empty. If it is, then it means the poped array is possible given pushed sequence original.
        * */

        if(original==null || poped == null){ return false; }

        else {
            Stack<Integer> test = new Stack<Integer>();
            int sizeOriginal = original.length;
            int sizePoped = poped.length;

            int in = 0;
            boolean flag = false;
            for(int i=0; i<sizeOriginal; i++){
                while(test.push(original[i])==poped[in]){
                    flag=true;
                    test.pop();
                    in++;
                }
                if(flag){test.pop(); flag = false;}// to determine whether break while loop, another unuseful push is done.
                // And thus, to undo this extra push, after while loop, have to pop.
            }
            //Case when poped array is not transversed all.
            while(in<sizePoped && test.pop()==poped[in]){
                in++;
            }
            return test.isEmpty();
        }
    }

    public static void main(String[] args){
        int[] a = { 1, 2, 3, 4, 5 };  // caution: to initialize an array, you need {} not [] in python.
        int[] b = { 4, 5, 3, 2, 1 };
        int[] c = { 4, 3, 5, 1, 2 };

        System.out.println(isPopedRight(a,b));
        System.out.println(isPopedRight(a,c));

    }
}
