// recursion
class Solution {
    private boolean isInterLeave(String s1, String s2, String s3, int s1p, int s2p) {
        // return true if both string's ends are reached
        if(s1p + s2p == s3.length()) return true; // all chars correctly matched
        
        // if one string's end is reached, only check the other
        if(s1p == s1.length()) return s2.charAt(s2p) == s3.charAt(s1p + s2p) && 
                                      isInterLeave(s1, s2, s3, s1p, s2p + 1);
        if(s2p == s2.length()) return s1.charAt(s1p) == s3.charAt(s1p + s2p) && 
                                      isInterLeave(s1, s2, s3, s1p + 1, s2p);
        
        // check the validity of both strings' characters, and recurse acordingly
        char cToMatch = s3.charAt(s1p + s2p);
        if(s1.charAt(s1p) == cToMatch && s2.charAt(s2p) == cToMatch) { // both chars match
            return isInterLeave(s1, s2, s3, s1p + 1, s2p) ||
                   isInterLeave(s1, s2, s3, s1p, s2p + 1);
        } else if(s1.charAt(s1p) == cToMatch) {
            return isInterLeave(s1, s2, s3, s1p + 1, s2p);
        } else if(s2.charAt(s2p) == cToMatch) {
            return isInterLeave(s1, s2, s3, s1p, s2p + 1);
        } else {
            return false;
        }
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        return isInterLeave(s1, s2, s3, 0, 0);
    }
}
