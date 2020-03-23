import java.util.ArrayList;
import java.util.List;

class Solution_442 {
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = nums.length;
        int[] freq = new int[n+1];

        for ( int i = 0; i < n; i ++ ) {
            freq[nums[i]] ++;
        }

        for ( int i = 1; i < freq.length; i ++ ) {
            if ( freq[i] == 2 )
                res.add(i);
        }

        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(1);
        list.add(item);
        System.out.println(list);

        item.add(2);
        list.add(item);
        System.out.println(list);
    }
}