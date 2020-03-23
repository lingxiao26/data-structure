import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Solution_46 {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        generate(nums, 0, new Stack<>());

        return res;
    }

    private void generate(int[] arr, int index, Stack<Integer> item) {
        //System.out.print(genString(index));
        //System.out.println(item);
        if ( index == arr.length ) {
            res.add(new ArrayList<>(item));
            return;
        }

        for ( int i = 0; i < arr.length; i ++ ) {
            if ( !used[i] ) {
                item.push(arr[i]);
                used[i] = true;
                generate(arr, index+1, item);
                item.pop();
                used[i] = false;
            }
        }

    }

    private String genString(int level) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < level; i ++ ) {
            sb.append("--");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Solution_46 sol = new Solution_46();
        List<List<Integer>> res = sol.permute(arr);
        System.out.println(res);
    }
}
