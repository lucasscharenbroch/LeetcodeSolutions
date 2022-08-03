class Solution {
    private boolean cellIsValid(char[][] board, int r, int c) {
        // check for same-column conflicts     
        for(int row = 0; row < 9; row++) {
            if(row != r && board[row][c] == board[r][c]) return false;    
        }
        
        // check for same-row conflicts
        for(int col = 0; col < 9; col++) {
            if(col != c && board[r][col] == board[r][c]) return false;
        }
        
        // check for same-sub-box conflicts
        int boxRow = r / 3;
        int boxCol = c / 3;
        for(int row = boxRow * 3; row < (boxRow * 3) + 3; row++) {
            for(int col = boxCol * 3; col < (boxCol * 3) + 3; col++) {
                if(row != r && col != c && board[row][col] == board[r][c]) return false;
            }
        }
        
        return true;
    }
    
    // backtracking algorithm for solving a sudoku board
    private boolean solve(char[][] board, int r, int c) {
        if(r == 9) return true;
        if(c == 9) return solve(board, r + 1, 0);
        
        // ignore already-filled digits
        if(board[r][c] != '.') return solve(board, r, c + 1);
        
        // for each valid digit, attempt to solve the rest of the board.
        for(int i = 1; i <= 9; i++) {
            board[r][c] = (char) ('0' + i);
            if(cellIsValid(board, r, c) && solve(board, r, c + 1)) {
                return true;
            }
        }
        
        // no valid solutions on given board
        board[r][c] = '.'; // reset this cell to blank (prevent false collisions)
        return false; // backtrack
    }
    
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0); 
    }
}
