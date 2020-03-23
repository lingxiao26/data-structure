import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parentheses {

    private List<String> parentheses = new ArrayList<>();

    // 2n: 生成的字符串长度,每个字符由'('或者')'组成
    public List<String> generateParentheses(int n) {
        generate("", n);

        return parentheses;
    }

    private void generate(String item, int n) {
        if ( item.length() == 2*n ) {
            if ( isValid(item) )
                parentheses.add(item);
            return;
        }

        generate(item+"(", n);
        generate(item+")", n);
    }

    private boolean isValid(String item) {
        Stack<Character> stack = new Stack<>();

        for ( int i = 0; i < item.length(); i ++ ) {
            char c = item.charAt(i);
            if ( c == '(' )
                stack.push(c);
            else {
                if ( stack.isEmpty() )
                    return false;
                char topChar = stack.pop();
                if ( c == ')' && topChar != '(' )
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Parentheses sol = new Parentheses();
        List<String> pas = sol.generateParentheses(3);
        System.out.println(pas);
    }
}
