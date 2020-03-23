import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Solution_22 {

    private List<String> parentheses = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate("", n, n);

        return parentheses;
    }

    // left:  剩余可以放置"("的数量
    // right: 剩余可以放置")"的数量
    // "("和")"的数量需满足:
    // 1. "("或")"的数量最多放置n个
    // 2. 如"("的数量 <= ")"的数量, 不可再进行放置")"的递归
    private void generate(String item, int left, int right) {
        // 当左括号和右括号数量相等且都为n时, 此时就是满足条件的有效括号
        if ( left == 0 && right == 0 ) {
            parentheses.add(item);
            return;
        }

        // 如果小于0说明左或者右括号的数量已经超过n了,不再进行递归,直接返回
        // if ( left < 0 || right < 0 )
        //    return;
        if ( left > 0 ) // 还可以放置左括号, 继续递归放置左括号
            generate(item+"(", left-1, right);

        if ( left < right )  // 递归放置右括号
            generate(item+")", left, right-1);
    }

    public static void main(String[] args) {
        Solution_22 sol = new Solution_22();
        List<String> res = sol.generateParenthesis(10);
        System.out.println(res);
    }
}
