class Solution {
    // represents coordinate changes of down, right, up, and left
    private int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    private boolean expand(char[][] board, String word, int i, int r, int c) {
        if(i == word.length()) return true; // no more chars to match
        // set board[r][c] to '.' to ensure that the board[r][c] will not be used to match another char
        char prevCharValue = board[r][c];
        board[r][c] = '.';
        
        for(int[] direction : directions) {
            int rdest = r + direction[0];
            int cdest = c + direction[1];
            
            // ensure (rdest, cdest) is NOT out of bounds
            if(rdest < 0 || rdest >= board.length || cdest < 0 || cdest >= board[rdest].length) continue;
            
            // recursively expand
            if(board[rdest][cdest] == word.charAt(i) && 
               expand(board, word, i + 1, rdest, cdest)) return true;
        }
        
        board[r][c] = prevCharValue; // allow board[r][c] to be matched again
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        // check if any cell matches word[0]
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] == word.charAt(0)) {
                    if(expand(board, word, 1, r, c)) return true; // return true if word is found
                }
            }
        }
        
        return false;
    }
}
