class Solution {
    public int longestValidParentheses(String s) {
        int longest = 0;     
        int left = 0;
        int right = 0;
        int numUnclosed = 0;
        for(; right < s.length(); right++) {
            if(s.charAt(right) == ')') numUnclosed--;
            else numUnclosed++;
            
            if(numUnclosed == 0) longest = Math.max(longest, right - left + 1);
            else if(numUnclosed < 0) { // un-opened paren is closed
                left = right + 1;
                numUnclosed = 0;
            }
        }
        
        // do a similar iteration, backwards
        int numUnopened = 0;
        right = s.length() - 1;
        for(left = right; left >= 0; left--) {
            if(s.charAt(left) == '(') numUnopened--;
            else numUnopened++;
            
            if(numUnopened == 0) longest = Math.max(longest, right - left + 1);
            else if(numUnopened < 0) { // un-closed paren is opened
                right = left - 1;    
                numUnopened = 0;
            }
        }
        
        return longest;
    }
}
