impl Solution {
    fn dfs(vis: &mut Vec<Vec<bool>>, board: &Vec<Vec<char>>, word: &[u8], k: usize, i: i32, j: i32) -> bool {
        let n = board.len() as i32;
        let m = board[0].len() as i32;
        
        if k == word.len() {
            return true;
        } else if i < 0 || j < 0 || i >= n || j >= m  ||
                  vis[i as usize][j as usize] ||
                  word[k] != board[i as usize][j as usize] as u8 {
            return false;
        }
        
        vis[i as usize][j as usize] = true;
        
        let mut res = false;
        
        for (ip, jp) in vec![(1, 0), (-1, 0), (0, 1), (0, -1)] {
            res = res || Solution::dfs(vis, board, word, k + 1, i + ip, j + jp);
        }
        
        vis[i as usize][j as usize] = false;
        
        res
    }
    
    pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
        let n = board.len();
        let m = board[0 as usize].len();
        
        let mut vis = vec![vec![false; m]; n];
        let word = word.as_bytes();
        
        for i in 0..board.len() {
            for j in 0..board[i].len() {
                if Solution::dfs(&mut vis, &board, word, 0, i as i32, j as i32) {
                    return true;
                }
            }
        }
        
        false
    }
}
