import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 示例 1:
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 */
class Solution_451 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>(); // K: c --> V: freq
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < s.length(); i ++ ) {
            map.merge(s.charAt(i), 1, (oldVal, newVal) -> oldVal + 1);
        }

        pq.addAll(map.keySet());

        while ( !pq.isEmpty() ) {
            char c = pq.remove();
            for ( int i = 0; i < map.get(c); i++ )
                sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append('c' * 3);
        System.out.println(sb);
    }
}