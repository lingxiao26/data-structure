import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
class Solution_49 {

    /**
     *  使用映射
     *  键为输入的字符串数组中的字符串按照每一个字符排序之后得到的新的字符串
     *  值为一个list, list里面存放排序后键相同的字符串
     *
     *  例如: 对于输入的字符串数组 ["nat","tan"]
     *  K: "ant" --> V: ["nat","tan"]
     *
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            String sortedStr = sortStr(str);

            if (!map.containsKey(sortedStr)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortedStr, list);
            } else {
                map.get(sortedStr).add(str);
            }
        }

        return new ArrayList<>(map.values());
    }

    private String sortStr(String str) {
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

    public static void main(String[] args) {
        String str = "hello";
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        String sortedStr = new String(chs);
        System.out.println(str);
        System.out.println(sortedStr);

    }
}