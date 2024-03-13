impl Solution {
    pub fn knight_dialer(n: i32) -> i32 {
        let n = n as usize;
        let M = 1000000007;
        
        let mut dp = vec![vec![0; 10]; n]; // dp[k][i] = number of combinations
                                           // of length k, ending in digit i
        
        // hard-code it
        let knight_moves: Vec<Vec<usize>> = vec![
            vec![4, 6], // 0
            vec![8, 6], // 1
            vec![7, 9], // 2
            vec![4, 8], // 3
            vec![3, 9, 0], // 4
            vec![], // 5
            vec![1, 7, 0], // 6
            vec![2, 6], // 7
            vec![1, 3], // 8
            vec![4, 2], // 9
        ];
        
        for i in 0..10 {
            dp[0][i] = 1;
        }
        
        for k in 1..n {
            for i in 0..10 {
                for &x in knight_moves[i].iter() {
                    dp[k][i] += dp[k - 1][x];
                    dp[k][i] %= M;
                }
            }
        }
        
        dp[n - 1].iter().fold(0, |s, x| (s + x) % M)
    }
}
