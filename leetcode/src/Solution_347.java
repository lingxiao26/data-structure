import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
public class Solution_347 {

//    private class Freq implements Comparable<Freq> {
//        int num, freq;
//
//        public Freq(int num, int freq) {
//            this.num = num;
//            this.freq = freq;
//        }
//
//        @Override
//        public int compareTo(Freq another) {
//            if (this.freq < another.freq)
//                return -1;
//            else if (this.freq > another.freq)
//                return 1;
//            else
//                return 0;
//        }
//    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int key : map.keySet()) {
            if (queue.size() < k)
                queue.add(key);
            else if (map.get(key) > map.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }

        List<Integer> list = new ArrayList<>();
        // queue.getSize()在一次dequeue之后会减一,用在这个循环中是错误的
//        for (int i=0; i<queue.getSize(); i++) {
//            list.add(queue.dequeue().num);
//        }

        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }

        return list;
    }

    public static void main(String[] args) {
        Solution_347 so = new Solution_347();
        int[] arr = {1, 1, 1, 2, 2, 3};
        List<Integer> list = so.topKFrequent(arr, 2);
        System.out.println(list);
    }
}
