class Solution_242 {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];

        if (s.length() != t.length())
            return false;

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
            if (freq[t.charAt(i)] == 0)
                return false;
        }

        return true;
    }
}