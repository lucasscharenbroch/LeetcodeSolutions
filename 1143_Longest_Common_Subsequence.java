// recursive solution O(n*2^m)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        return solve(text1, 0, text2, 0);
    }
    
    private int solve(String s1, int s1Start, String s2, int s2Start) {
        if(s1Start == s1.length() || s2Start == s2.length()) return 0;
        
        int i = s1Start;
        
        while(i < s1.length() && s1.charAt(i) != s2.charAt(s2Start)) i++;
        
        if(i == s1.length()) return solve(s1, s1Start, s2, s2Start + 1); // no match for s2[s2Start]
        return Math.max(
            1 + solve(s1, i + 1, s2, s2Start + 1),  // take match
            solve(s1, s1Start, s2, s2Start + 1) // don't match s2[s2Start]
        );
    }
}

// 2-d dp, O(n*m)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] will hold LCS(text1[0..i], text2[0..j] (end-bound-exclusive))
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        
        for(int r = 1; r <= text1.length(); r++) {
            for(int c = 1; c <= text2.length(); c++) {
                if(text1.charAt(r - 1) == text2.charAt(c - 1)) dp[r][c] = 1 + dp[r - 1][c - 1];
                else dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
            }
        }
        
        return dp[text1.length()][text2.length()];
    }
}
