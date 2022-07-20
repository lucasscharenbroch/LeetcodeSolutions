class Solution {
    public String longestPalindrome(String s) {
        // iterate through s, treating each char as a center, expand outwards,
        // update longest accordingly.
        
        String longest = "";
        int left, right; // pointers to the ends of the current substring
        for(int i = 0; i < s.length(); i++) {
            // center = i 
            left = i;
            right = i;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                longest = (right - left + 1 > longest.length()) ? s.substring(left, right+1) : longest;
                left--;
                right++;
            }
            // center = i and i+1 
            left = i;
            right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                longest = (right - left + 1 > longest.length()) ? s.substring(left, right+1) : longest;
                left--;
                right++;
            }
        }
        
        return longest;
    }
}
