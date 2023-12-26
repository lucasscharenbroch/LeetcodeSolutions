impl Solution {
    pub fn num_rolls_to_target(n: i32, k: i32, target: i32) -> i32 {
        let (n, k, target) = (n as usize, k as usize, target as usize);
        
        // dp[i][j] = number of ways to roll j with i dice
        let mut dp = vec![vec![0; (n * k + 1) as usize]; (n + 1) as usize];
        
        // 1 way to roll 1..=k with 1 die
        for j in 1..=k {
            dp[1][j] = 1;
        }
        
        for i in 2..=n {
            let mut running_sum = 0;
            for j in 1..=(n * k) {
                running_sum += dp[i - 1][j - 1];
                running_sum %= 1000000007;
                if j > k {
                    running_sum -= dp[i - 1][j - k - 1];
                    running_sum += 1000000007;
                    running_sum %= 1000000007;
                }
                dp[i][j] = running_sum;
            }
        }
        
        if target > n * k { 0 } else { dp[n][target] }
    }
}
