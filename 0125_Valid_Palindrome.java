class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while(left < right) {
            while(left < s.length() && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while(right >= 0 && !Character.isLetterOrDigit(s.charAt(right))) right--;
            
            if(left > right) break;
            
            if(Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) 
                return false;
        }
        
        return true;
    }
}
