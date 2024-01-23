use std::cmp::{min, max};

impl Solution {
    fn lcs(x: &[u8], y: &[u8]) -> i32 {
        let xn = x.len();
        let yn = y.len();
        let mut res = 0;
        let mut dp = vec![vec![0; yn]; xn];
        
        for i in 0..xn { for j in 0..yn {
            let is_match = if x[i] == y[j] { 1 } else { 0 };
            
            dp[i][j] = if i == 0 && j == 0 {
                is_match
            } else if i == 0 {
                max(is_match, dp[i][j - 1])
            } else if j == 0 {
                max(is_match, dp[i - 1][j])
            } else if x[i] == y[j] {
                1 + dp[i - 1][j - 1]
            } else {
                max(dp[i - 1][j], dp[i][j - 1])
            };
            
            res = max(res, dp[i][j]);
        }}
        
        res
    }
    
    fn rev_slice(s: &[u8]) -> Vec<u8> {
        s.iter().rev().cloned().collect::<Vec<_>>()
    }
    
    pub fn longest_palindrome_subseq(s: String) -> i32 {
        let b = s.as_bytes();
        Self::lcs(b, &Self::rev_slice(b))
    }
}
