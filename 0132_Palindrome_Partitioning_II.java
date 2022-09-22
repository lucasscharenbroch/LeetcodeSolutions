class Solution {
    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    
    public int minCut(String s) {
        int[][] dp = new int[s.length()][2];
        // dp[i][0] => the minimum splits from s[0 -> i] such that i is its own partition
        // dp[i][1] => the minimum splits of the substring {0 -> i} (i is not necessarily in its own partition)
        
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        for(int i = 1; i < s.length(); i++) {
            dp[i][0] = dp[i - 1][1] + 1;
            int minSplits = dp[i][0];
            
            // search for all palindromes, j -> i
            for(int j = 0; j < i; j++) {
                if(isPalindrome(s, j, i)) {
                    minSplits = Math.min(minSplits, dp[j][0]);
                }
            }
            dp[i][1] = minSplits;
        }
        
        return dp[s.length() - 1][1];
    }
}
