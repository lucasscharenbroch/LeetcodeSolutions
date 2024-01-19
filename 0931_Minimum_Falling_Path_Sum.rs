use std::cmp::min; 

impl Solution {
    pub fn min_falling_path_sum(matrix: Vec<Vec<i32>>) -> i32 {
        let n = matrix.len();
        let m = matrix[0].len();
        // dp[i][j] = min sum of falling path to cell (i, j)
        let mut dp = matrix.clone();
        
        for i in 0..n {
            for j in 0..m {
                if i == 0 { continue; }
                
                let mut min_above = dp[i - 1][j];
                
                if j != 0 { min_above = min(min_above, dp[i - 1][j - 1]); }
                if j != m - 1 { min_above = min(min_above, dp[i - 1][j + 1]); }
                
                dp[i][j] += min_above;
            }
        }
        
        *dp[n - 1].iter().min().unwrap()
    }
}
