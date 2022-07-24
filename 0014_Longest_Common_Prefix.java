class Solution {
    public String longestCommonPrefix(String[] strs) {
        String longest = strs[0];    
        String current;
        
        for(int i = 1; i < strs.length && longest.length() > 0; i++) {
            current = strs[i];
            int k = 0; // iterator for current and longest
            
            while(k < longest.length() && k < current.length() && 
                  longest.charAt(k) == current.charAt(k)) {
                k++;    
            }
           
            longest = longest.substring(0, k); // remove any unmatched characters
        }
        
        return longest;
    }
}
