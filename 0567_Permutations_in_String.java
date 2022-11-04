class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        
        int[] targetCounts = new int['z' + 1];
        int[] charCounts = new int['z' + 1];
        int left = 0,right = s1.length();
        
        // count chars in first window and s1
        for(int c = left; c < right; c++) {
            charCounts[s2.charAt(c)]++;
            targetCounts[s1.charAt(c)]++;
        }
        
        while(true) {
            // check if window is permutation
            boolean windowIsPermutation = true;
            for(int c = 'a'; c <= 'z'; c++) {
                if(targetCounts[c] != charCounts[c]) windowIsPermutation = false;
            }
            if(windowIsPermutation) return true;
            
            // update window and charCounts
            if(right == s2.length()) break;
            charCounts[s2.charAt(left++)]--;
            charCounts[s2.charAt(right++)]++;
        }
        
        return false;
    }
}
