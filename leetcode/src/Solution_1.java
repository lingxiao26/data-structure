import java.util.HashMap;
import java.util.Map;

class Solution_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // K: nums[i] --> V: i
        int[] res = new int[2];

        for ( int i = 0; i < nums.length; i ++ ) {
            if ( map.containsKey(target-nums[i]) ) {
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                return res;
            }

            map.put(nums[i], i);
        }

        return res;
    }
}