class Solution {
    private boolean charIsMatch(char s, char p) {
        if(p == '.') {
            return true;
        } else {
            return s == p;
        }
    }
    
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) {
            return true;
        }         
        
        if(p.length() > 1 && p.charAt(1) == '*') {
            if(s.length() > 0 && charIsMatch(s.charAt(0), p.charAt(0))) { // first char matches
                // skip first char (and keep pattern), or skip pattern (and keep first char) 
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            } else {
                // first char does not match, so pattern is skipped
                return isMatch(s, p.substring(2));
            }    
        }
        
        if(s.length() == 0 || p.length() == 0 || !charIsMatch(s.charAt(0), p.charAt(0))) {
            return false; 
        }
        
        // skip over matched char (s[0] and p[0]), and continue checking
        return isMatch(s.substring(1), p.substring(1));
    }
}
