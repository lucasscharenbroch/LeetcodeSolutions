class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        
        for(int i = 0; i < s.length(); i++) {
            int left = i, right = i; // expand from i ("single-character center")
            while(left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++))
                result++;
            
            left = i; right = i + 1; // expand from [i, i+1] ("double-character center")
            while(left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++))
                result++;
        }
        
        return result;
    }
}
