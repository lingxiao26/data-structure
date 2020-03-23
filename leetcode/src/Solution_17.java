import java.util.ArrayList;
import java.util.List;

class Solution_17 {

    private List<String> res = new ArrayList<>();

    private final String[] letterMap = {
            " ",     // 0
            "",      // 1
            "abc",   // 2
            "def",   // 3
            "ghi",   // 4
            "jkl",   // 5
            "mno",   // 6
            "pqrs",  // 7
            "tuv",   // 8
            "wxyz"   // 9
    };

    public List<String> letterCombinations(String digits) {

        findCombinations(digits, 0, "");

        return res;

    }

    private void findCombinations(String digits, int index, String item) {
        if ( index >= digits.length() ) {
            res.add(item);
            return;
        }
        int digit = digits.charAt(index);
        String str = letterMap[digit-'0'];

        for ( int i = 0; i < str.length(); i ++ ) {
            findCombinations(digits, index+1, item+str.charAt(i));
        }
    }


    public static void main(String[] args) {
        Solution_17 sol = new Solution_17();
        List<String> strings = sol.letterCombinations("23");
        System.out.println(strings);
    }
}