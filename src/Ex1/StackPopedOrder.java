package Ex1;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;

public class StackPopedOrder {
    /*
    * Therefore: A-(push)-> A' -(pop)-> B
    * So, codes below have to push the data and then pop it while stack is full.
    * */

    private static int count = 0;

    public static void main(String[] args) {
        ArrayList<String> pops = popSerials(new char[]{'a', 'b', 'c', 'd'}, 0);
        int i = 0;
        for(String pop : pops){
            i++;
            System.out.println( i + "th: " + pop);
        }
    }

    // push为指定的入栈字符，并且顺序从前至后依次入栈，begin为入栈序列的开始索引
    // 返回所有可能的出栈序列
    public static ArrayList<String> popSerials(char[] push, int begin){
        return popSerials(push, begin, new Stack<Character>(), new StringBuilder());
    }

    private static ArrayList<String> popSerials(char[] push, int begin, Stack<Character> s, StringBuilder sb){
        ArrayList<String> pops = new ArrayList<String>();
        if(push == null){
            return null;
        }
        s.push(push[begin]); // push the unpushed 1st character into stack
        begin++;
        if(begin == push.length){ // For elements already pushed into the stack, just pop it to get sequence.
            while(!s.isEmpty()){ //When stack is not null
                sb.append(s.pop());
            }
            pops.add(sb.toString());
        }else{ // For elements not in the stack, push the rest from the begin position (
            // with a hope that all the data will be pushed in the end), then pop will be done.
            // push
            pops.addAll(popSerials(push, begin, (Stack<Character>)s.clone(), new StringBuilder(sb)));
            // Data already in stack are gonna be used by other recursion level, but data here should not be changed,
            // and thus a copy is required.
            // For data already poped (characters in sb), they also face the same situation, and also requires a copy.
            while(!s.isEmpty()){ //when stack is not null
                sb.append(s.pop()); // pop and the poped one should be saved to sb
                // push
                pops.addAll(popSerials(push, begin, (Stack<Character>)s.clone(), new StringBuilder(sb)));
            }
        }
        return pops;
    }
}
