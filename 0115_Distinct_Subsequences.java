// recursive solution (exponential time)
class Solution {
    private int numDistinct(String s, String t, int sStart, int tStart) {
        if(tStart == t.length()) return 1; // all chars matched 
        if(sStart == s.length()) return 0; // some chars unmatched 
        
        if(s.charAt(sStart) == t.charAt(tStart)) {
            return numDistinct(s, t, sStart + 1, tStart + 1) + // match
                   numDistinct(s, t, sStart + 1, tStart);      // skip
        }
        
        return numDistinct(s, t, sStart + 1, tStart); // s[sStart] does not match, so we must skip
    }
    
    public int numDistinct(String s, String t) {
        return numDistinct(s, t, 0, 0);
    }
}

// dynamic programming matrix (O(|s| * |t|))
class Solution {
    public int numDistinct(String s, String t) {
        // each column represents a char in t, each row represents a char in s
        int[][] subSolutions = new int[s.length()][t.length()];
        
        int r, c;
        
        // fill rightmost row (add 1 if the chars match, otherwise set the cell to the value below)
        c = t.length() - 1;
        for(r = s.length() - 1; r >= 0; r--) {
            if(s.charAt(r) == t.charAt(c)) {
                subSolutions[r][c] = 1;
            }
            if(r != s.length() - 1) subSolutions[r][c] += subSolutions[r + 1][c];
        }
        
        // fill the rest of the array
        for(c = t.length() - 2; c >= 0; c--) {
            for(r = s.length() - (t.length() - c); r >= 0; r--) {
                // (skip substrings of s that are too small to match t)
                if(s.charAt(r) == t.charAt(c)) 
                    subSolutions[r][c] = subSolutions[r + 1][c + 1] + subSolutions[r + 1][c];
                else
                    subSolutions[r][c] = subSolutions[r + 1][c];
            }
        }
        
        return subSolutions[0][0];
    }
}
