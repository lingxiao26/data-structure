import java.util.*;
import java.util.Stack;

public class Solution_40_2 {

    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        generateSubSet(candidates, 0, 0, target, new Stack<>());

        return new ArrayList<>(res);
    }

    // sum: arr中子集的和
    private void generateSubSet(int[] arr, int i, int sum, int target, Stack<Integer> item) {
        if ( i >= arr.length || sum > target )
            return;

        item.push(arr[i]);
        sum += arr[i];

        if ( sum == target )
            res.add(new ArrayList<>(item));

        generateSubSet(arr, i+1, sum, target, item);
        sum -= arr[i];
        item.pop();
        generateSubSet(arr, i+1, sum, target, item);
    }

    public static void main(String[] args) {
        int[] arr = {2,5,2,1,2};
        int target = 5;
        Solution_40_2 sol = new Solution_40_2();
        List<List<Integer>> res = sol.combinationSum2(arr, target);
        System.out.println(res);
    }
}
