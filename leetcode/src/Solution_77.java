import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Solution_77 {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> combine(int n, int k) {
        used = new boolean[n + 1];
        Arrays.fill(used, false);
        generateCombine(n, k, new Stack<>(), 1);

        return res;
    }

    private void generateCombine(int n, int k, Stack<Integer> c, int start) {

        if (c.size() == k) {
            res.add(new ArrayList<>(c));
            return;
        }

        for (int i = start; i <= n; i++) {
            c.push(i);
            if (n - start <= k) {
                generateCombine(n, k, c, i + 1);
                c.pop();
            }
        }
    }

    public static void main(String[] args) {
        Solution_77 sol = new Solution_77();
        List<List<Integer>> res = sol.combine(8, 5);
        System.out.println(res);
    }
}
