class Solution {
    public int appendCharacters(String s, String t) {
        // greedily match chars of t to chars of s, in order.
        // return the number of remaining chars in t that haven't been matched by the end of s.
        
        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            if(j == t.length()) return 0;
            if(t.charAt(j) == s.charAt(i)) j++;
        }
        
        return t.length() - j;
    }
}
