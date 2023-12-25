impl Solution {
    pub fn num_decodings(s: String) -> i32 {
        let mut dp = vec![0; s.len() + 2];
        dp[s.len()] = 1;
        dp[s.len() + 1] = 1;
        let b = s.as_bytes();
        
        for i in (0..s.len()).rev() {
            if b[i] == b'0' {
                continue;
            }
            
            dp[i] += dp[i + 1];
            
            if i == s.len() - 1 {
                continue;
            }
            
            let n = (b[i] - b'0') * 10 + (b[i + 1] - b'0');
            if n <= 26 {
                dp[i] += dp[i + 2];
            }
        }
        
        dp[0]
    }
}
