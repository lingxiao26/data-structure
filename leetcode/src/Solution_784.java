import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class Solution_784 {

    private List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        findPermutation(S, 0, new StringBuilder());

        return res;
    }

    private void findPermutation(String s, int index, StringBuilder sb) {
        System.out.print(generateString(index));
        System.out.println(index + " : " + sb.toString());
        if (index >= s.length()) {
            res.add(sb.toString());
            return;
        }


        char c = s.charAt(index);
        if (c >= '0' && c <= '9') {
            findPermutation(s, index + 1, sb.append(c));
            sb.deleteCharAt(sb.length()-1);
        } else {

            findPermutation(s, index + 1, sb.append(c));

            sb.deleteCharAt(sb.length()-1);
            if (Character.isLowerCase(c))
                c = Character.toUpperCase(c);
            else
                c = Character.toLowerCase(c);
            findPermutation(s, index + 1, sb.append(c));
            sb.deleteCharAt(sb.length()-1);
        }

    }

    private String generateString(int level) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < level; i ++ )
            sb.append("**");
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution_784 sol = new Solution_784();
        List<String> list = sol.letterCasePermutation("a1b2");
        System.out.println(list);


    }
}
