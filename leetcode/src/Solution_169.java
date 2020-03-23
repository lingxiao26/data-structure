import java.util.HashMap;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 *  你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *  示例 1:
 *  输入: [3,2,3]
 * 输出: 3
 *
 *  示例 2:
 *  输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 *  Related Topics 位运算 数组 分治算法
 */
class Solution_169 {
    public int majorityElement(int[] nums) {
        int v = nums.length / 2;

        HashMap<Integer, Integer> map = new HashMap<>();

        for ( int val : nums ) {
            map.merge( val, 1, (oldVal, newVal)->oldVal+1 );

            if (map.get(val) > v)
                return val;
        }

        throw new IllegalArgumentException("cannot found majority element.");

    }
}

