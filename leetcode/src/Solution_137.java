import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class Solution_137 {
    public int[] singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        int[] ret = new int[2];

        for ( int v : nums ) {
            map.merge( v, 1, (oldVal, newVal)->oldVal+1 );
        }

        for ( int k : map.keySet() ) {
            if ( map.get(k) == 1 )
                res.add(k);
        }

        for ( int i = 0; i < res.size(); i ++ )
            ret[i] = res.get(i);

        return ret;

    }
}
