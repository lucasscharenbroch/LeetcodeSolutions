impl Solution {
    pub fn min_cost(colors: String, needed_time: Vec<i32>) -> i32 {
        let colors = colors.as_bytes();
        let n = colors.len();
        let mut dp = vec![0; n];
        let mut streak_cost = 0;
        let mut dp_before_streak = 0;
        
        for i in 1..n {
            if colors[i] == colors[i - 1] {
                streak_cost += needed_time[i - 1];
            } else {
                streak_cost = 0;
                dp_before_streak = dp[i - 1];
            }
            
            dp[i] = std::cmp::min(dp_before_streak + streak_cost, needed_time[i] + dp[i - 1]); // take it or leave it
        }
        
        dp[n - 1]
    }
}
