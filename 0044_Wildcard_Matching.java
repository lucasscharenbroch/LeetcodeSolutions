// recursive solution (fails)
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        
        if(p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) ||  // match 0
                   s.length() > 0 && isMatch(s.substring(1), p); // match >= 1
        }
        
        if(p.charAt(0) == '?') {
            return s.length() != 0 && isMatch(s.substring(1), p.substring(1));
        }
        
        return s.length() != 0 && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }
}

// optimized recursive solution
class Solution {
    // determines if two chars (without wildcards) match
    private boolean charMatch(char s, char p) {
        if(p == '?') return true;
        return s == p;
    }
    
    // determines if two strings of the same length (without wildcards) match
    private boolean stringMatch(String s, String p) {
        if(p.length() == 0) return true; // "" matches "" (length is always the same)
        
        for(int i = 0; i < s.length(); i++) {
            if(!charMatch(s.charAt(i), p.charAt(i))) return false;    
        }
        return true;
    }
    
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        
        if(p.charAt(0) != '*') { // check for regular match
            return s.length() != 0 && charMatch(s.charAt(0), p.charAt(0)) && // s[0] matches p[0]
                isMatch(s.substring(1), p.substring(1)); // s[rest] matches p[rest]
        } else { // p[0] == '*'
            int firstNonStar = 1; // the first index of p that does not == '*'
            for(; firstNonStar < p.length() && p.charAt(firstNonStar) == '*'; firstNonStar++);
            if(firstNonStar == p.length()) return true;
            
            int nextStar = p.indexOf('*', firstNonStar);
            
            if(nextStar == -1) {
                if(s.length() < (p.length() - firstNonStar)) return false; // s is too small to match
                return stringMatch(s.substring(s.length() - (p.length() - firstNonStar)), // last n of s
                                   p.substring(firstNonStar)); // where n is the number of non-star chars
            }                                                  // at the end of p
            
            String sToMatch = p.substring(firstNonStar, nextStar);
            // find the first occurence of sToMatch, then recurse
            for(int i = 0; i < s.length() - sToMatch.length() + 1; i++) {
                String current = s.substring(i, i + sToMatch.length());
                if(stringMatch(current, sToMatch)) 
                    return isMatch(s.substring(i + current.length()), p.substring(nextStar));
            }
        }
        
        return false;
    }
}
