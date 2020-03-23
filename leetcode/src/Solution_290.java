import java.util.HashMap;
import java.util.Map;

class Solution_290 {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<String, Integer>    m2 = new HashMap<>();

        if ( pattern.length() != str.split(" ").length )
            return false;

        for ( int i = 0; i < pattern.length(); i ++ ) {
            Integer i1 = m1.get(pattern.charAt(i));
            Integer i2 = m2.get(str.split(" ")[i]);

            // "  a  b   b   a"
            // "dog cat cat fish"
            if ( i1 != null && i2 != null && !i1.equals(i2) )
                return false;

            if ( (i1 != null && i2 == null) || (i1 == null && i2 != null) )
                return false;

            m1.put( pattern.charAt(i), i+1 );
            m2.put( str.split(" ")[i], i+1 );
        }

        return true;
    }
}
