import java.util.*;
import java.util.Stack;

class Solution_90 {

    private Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        res.add(new ArrayList<>());
        generate(nums, 0, new Stack<>());


        return new ArrayList<>(res);
    }

    @SuppressWarnings("all")
    private void generate(int[] arr, int i, Stack<Integer> item) {
        if ( i >= arr.length )
            return;

        item.push(arr[i]);
        res.add(new ArrayList<>(item));
        generate(arr, i+1, item);

        item.pop();
        generate(arr, i+1, item);
    }
}