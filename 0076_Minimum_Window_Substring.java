class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> sCounts = new HashMap<>(), tCounts = new HashMap<>();
        int uniqueCharsInT = 0, numCountsMet = 0;
        
        // populate tCounts and uniqueCharsInT
        for(int i = 0; i < t.length(); i++) {
            if(tCounts.get(t.charAt(i)) == null) {
                uniqueCharsInT++;
                tCounts.put(t.charAt(i), 1);
            } else tCounts.put(t.charAt(i), tCounts.get(t.charAt(i)) + 1); // tCounts[t[i]]++
        }
        
        int start = 0, end = 0; // pointer to s (bounds of substring)
        // expand end until s[start:end] contains all of the chars in t
        while(uniqueCharsInT > numCountsMet && end < s.length()) {
            // sCounts[s[end]]++;
            sCounts.put(s.charAt(end), (sCounts.get(s.charAt(end)) == null) ? 
                        1 : sCounts.get(s.charAt(end)) + 1);
                
            
            // if a new count is fufilled, increment numCountsMet
            if(tCounts.get(s.charAt(end)) != null && 
               tCounts.get(s.charAt(end)).equals(sCounts.get(s.charAt(end)))) numCountsMet++;
               
            end++;
        }
        if(uniqueCharsInT > numCountsMet) return ""; // s doesn't contain all chars in t
        
        // advance end, checking if start can be expanded while maintaining all chars in t
        String minSub = s.substring(start, end);
        while(true) {
            // advance start, if possible
            while(start < end) {
                char startChar = s.charAt(start);
                if(tCounts.get(startChar) == null || tCounts.get(startChar) < sCounts.get(startChar)) {
                    sCounts.put(startChar, sCounts.get(startChar) - 1);
                    start++;
                } else break;
            }
            
            // update minSub
            if(end - start < minSub.length()) minSub = s.substring(start, end);
            
            if(end == s.length()) break;
                
            // sCounts[s[end]]++;
            sCounts.put(s.charAt(end), sCounts.get(s.charAt(end)) == null ? 
                        1 : sCounts.get(s.charAt(end)) + 1);
            end++;
        }
        
        return minSub;
    }
}
