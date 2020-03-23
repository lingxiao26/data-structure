import java.util.*;
import java.util.Stack;

@SuppressWarnings("all")
public class Solution_78 {

    private Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> subsets(int[] nums) {
        res.add(new ArrayList<>());

        generate(nums, 0, new Stack<>());

        return new ArrayList<>(res);

    }

    private void generate(int[] nums, int i, Stack<Integer> item) {
        //System.out.print(genString(i));
        //System.out.println(item);
        if ( i >= nums.length )
            return;

        item.push(nums[i]);
        //System.out.println("add "+item+" to res");
        res.add(new ArrayList<>(item));

        System.out.println(genString(i)+i);
        generate(nums, i+1, item);
        System.out.println(genString(i)+i);
        //System.out.println("pop "+item.peek()+" from " + item);
        item.pop();
        System.out.println(genString(i)+i);
        generate(nums, i+1, item);
        System.out.println(genString(i)+i);
    }

    private String genString(int level) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < level; i ++ ) {
            sb.append("--");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Solution_78 sol = new Solution_78();
        List<List<Integer>> res = sol.subsets(arr);
        System.out.println(res);


    }
}
