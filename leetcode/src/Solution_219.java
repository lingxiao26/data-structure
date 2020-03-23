import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiFunction;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
class Solution_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for ( int i = 0; i < nums.length; i ++ ) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);

            if ( set.size() == k+1 )
                set.remove( nums[i-k] );
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,3};
        int k = 2;

        HashSet<Integer> set = new HashSet<>();
        set.add(3);
        set.add(5);
        set.add(1);
        set.add(9);
        set.add(11);
        System.out.println(Collections.min(set));
        System.out.println(Collections.max(set));

        Solution_219 sol = new Solution_219();
        System.out.println(sol.containsNearbyDuplicate(arr, k));
    }
}