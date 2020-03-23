import java.util.HashSet;

public class Solution_136 {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for ( int v : nums ) {
            if ( !set.contains(v) )
                set.add(v);
            else
                set.remove(v);
        }

        return set.iterator().next();
    }
}
