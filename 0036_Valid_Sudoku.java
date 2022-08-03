class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Character, Boolean> seen;
        
        // check for repeats in rows
        for(int r = 0; r < 9; r++) {
            seen = new HashMap<Character, Boolean>();
            for(int c = 0; c < 9; c++) {
                if(board[r][c] == '.') continue;
                if(seen.get(board[r][c]) != null) return false;
                seen.put(board[r][c], true);
            }
        }
        
        // repeats in columns
        for(int c = 0; c < 9; c++) {
            seen = new HashMap<Character, Boolean>();
            for(int r = 0; r < 9; r++) {
                if(board[r][c] == '.') continue;
                if(seen.get(board[r][c]) != null) return false;
                seen.put(board[r][c], true);
            }
        }
        
        // repeats in sub-boxes
        for(int boxNumber = 0; boxNumber < 9; boxNumber++) {
            seen = new HashMap<Character, Boolean>();
            int boxRow = boxNumber / 3;
            int boxCol = boxNumber % 3;
            
            
            for(int r = boxRow * 3; r < (boxRow * 3) + 3; r++) {
                for(int c = boxCol * 3; c < (boxCol * 3) + 3; c++) {
                    if(board[r][c] == '.') continue;
                    if(seen.get(board[r][c]) != null) return false;
                    seen.put(board[r][c], true);
                } 
            }
        }
        
        return true;
    }
}
