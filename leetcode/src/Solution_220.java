
import java.util.TreeSet;

class Solution_220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();

        for ( int i = 0; i < nums.length; i ++ ) {
            /**
             *   v = nums[i]
             *   |v - x| <= t  -->  v-t <= x <= v+t
             *   x is in set
             */
            Long ceil = set.ceiling((long) nums[i] - (long) t); // 在set中找一个大于等于v-t的最小值
            if ( ceil != null && ceil <= (long) nums[i] + (long) t )
                return true;

            set.add( (long) nums[i] );

            if ( set.size() == k+1 )
                set.remove( (long) nums[i-k] ); // remove first element in sliding window
        }

        return false;
    }

    public static void main(String[] args) {

        int[] arr = {0,2147483647};
        int k = 1;
        int t = 2147483647;
        int x = t * 2;
        System.out.println(x);

        Solution_220 sol = new Solution_220();
        System.out.println(sol.containsNearbyAlmostDuplicate(arr, k, t));
    }
}