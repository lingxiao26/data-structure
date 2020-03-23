import java.util.ArrayList;
import java.util.List;

class Solution_448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        boolean[] isExist = new boolean[n+1];

        for ( int i = 0; i < nums.length; i ++ ) {
            if ( nums[i] >= 1 && nums[i] <= n )
                isExist[nums[i]] = true;
        }

        for ( int i = 1; i < isExist.length; i ++ ) {
            if ( !isExist[i] )
                res.add(i);
        }

        return res;
    }
}