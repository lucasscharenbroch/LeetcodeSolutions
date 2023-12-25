impl Solution {
    pub fn num_decodings(s: String) -> i32 {
        let mut dp = vec![0; s.len() + 2];
        dp[s.len()] = 1;
        dp[s.len() + 1] = 1;
        let b = s.as_bytes();
        
        for i in (0..s.len()).rev() {
            match b[i] {
                b'0' => continue,
                b'*' => dp[i] += dp[i + 1] * 9,
                _ => dp[i] += dp[i + 1],
            }
            
            if i == s.len() - 1 {
                continue;
            }
            
            let mult = match (b[i], b[i + 1]) {
                (b'*', b'*') => 15,
                (_, b'*') => (b[i] == b'1') as i64 * 9 + (b[i] == b'2') as i64 * 6,
                (b'*', _) => 1 + (b[i + 1] < b'7') as i64,
                _ => (b[i] < b'2' || (b[i] == b'2' && b[i + 1] < b'7')) as i64,
            };
            
            dp[i] += mult * dp[i + 2];
            dp[i] %= 1000000007;
        }
        
        dp[0] as i32
    }
}
