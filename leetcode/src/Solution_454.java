import java.util.HashMap;
import java.util.Map;

class Solution_454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        Map<Integer, Integer> map = new HashMap<>(); // sum -> count(sum)

        for (int v1 : C) {
            for (int v2 : D)
                map.merge(v1 + v2, 1, (oldVal, newVal) -> oldVal + 1);
        }

        int res = 0;

        for (int v1 : A) {
            for (int v2 : B) {
                if ( map.containsKey(-v1-v2) )
                    res += map.get(-v1-v2);
            }
        }

        return res;
    }
}