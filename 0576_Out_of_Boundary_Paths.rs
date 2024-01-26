impl Solution {
    pub fn find_paths(m: i32, n: i32, max_move: i32, start_row: i32, start_column: i32) -> i32 {
        const MOD: i32 = 1000000007;
        let N = n as usize;
        let M = m as usize;
        let K = (max_move + 1) as usize;
        
        let mut dp = vec![vec![vec![0; N]; M]; K];
        let mut res = 0;
        
        dp[0][start_row as usize][start_column as usize] = 1;
        
        for k in 1..K { for i in 0..M { for j in 0..N {
            for (di, dj) in vec![(1, 0), (-1, 0), (0, 1), (0, -1)] {
                let ti = (i as i32 + di);
                let tj = (j as i32 + dj);

                if ti < 0 || tj < 0 || ti == M as i32 || tj == N as i32 {
                    res = (res + dp[k - 1][i][j]) % MOD;
                } else {
                    let ti = ti as usize;
                    let tj = tj as usize;
                    dp[k][ti][tj] = (dp[k][ti][tj] + dp[k - 1][i][j]) % MOD;
                }
            }
        }}}
        
        res
    }
}
