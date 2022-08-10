class Solution {
    // globals
    int n;
    ArrayList<List<String>> result = new ArrayList<>();
    char[][] board;
    boolean[] columnHasQueen;
    boolean[] upDiagonalHasQueen;
    boolean[] downDiagonalHasQueen;
    
    public List<List<String>> solveNQueens(int n) {
        this.n = n;    
        board = new char[n][n];
        // fill board with '.'
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                board[r][c] = '.';
            }
        }
        
        // initialize territory arrays
        columnHasQueen = new boolean[n];
        upDiagonalHasQueen = new boolean[n * 2];
        downDiagonalHasQueen = new boolean[n * 2];
        
        backtrack(0);
        return result;
    }
    
    private void backtrack(int r) {
        for(int c = 0; c < n; c++) {
            // continue if territory is claimed
            if(columnHasQueen[c] || upDiagonalHasQueen[r + c] || downDiagonalHasQueen[r - c + n])
                continue;
            
            markTerritory(r, c, true); // claim territory
            board[r][c] = 'Q';
            if(r == n - 1) result.add(boardToStrList()); // found a solution
            else backtrack(r + 1);
            board[r][c] = '.';
            markTerritory(r, c, false); // unclaim territory
        }
    }
    
    private boolean isFreeTerritory(int r, int c) {
        return !(columnHasQueen[c] || upDiagonalHasQueen[r + c] || downDiagonalHasQueen[r - c + n]);
    }
    
    
    private void markTerritory(int r, int c, boolean isEntering) {
        columnHasQueen[c] = isEntering;
        upDiagonalHasQueen[r + c] = isEntering;
        downDiagonalHasQueen[r - c + n] = isEntering;
    }
    
    private List<String> boardToStrList() {
        ArrayList<String> list = new ArrayList<>();
        for(char[] row : board) {
            list.add(String.valueOf(row));    
        }
        return list;
    }
}
