class Solution {
    // globals
    int n;
    ArrayList<List<String>> result = new ArrayList<>();
    char[][] board;
    HashMap<Integer, Integer> columnHasQueen = new HashMap<>();
    HashMap<Integer, Integer> upDiagonalHasQueen = new HashMap<>();
    HashMap<Integer, Integer> downDiagonalHasQueen = new HashMap<>();
    
    public List<List<String>> solveNQueens(int n) {
        this.n = n;    
        board = new char[n][n];
        // fill board with '.'
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                board[r][c] = '.';
            }
        }
        
        backtrack(0, n);
        return result;
    }
    
    private void backtrack(int r, int queensRemaining) {
        for(int c = 0; c < n; c++) {
            if(isFreeTerritory(r, c)) {
                markTerritory(r, c, 1); // claim territory
                board[r][c] = 'Q';
                if(queensRemaining == 1) result.add(boardToStrList()); // found a solution
                else backtrack(r + 1, queensRemaining - 1);
                board[r][c] = '.';
                markTerritory(r, c, -1); // unclaim territory
            }
        }
    }
    
    private boolean isFreeTerritory(int r, int c) {
        return (columnHasQueen.get(c) == null || columnHasQueen.get(c) == 0) &&
               (upDiagonalHasQueen.get(r + c) == null || upDiagonalHasQueen.get(r + c) == 0) &&
               (downDiagonalHasQueen.get(r - c) == null || downDiagonalHasQueen.get(r - c) == 0);
    }
    
    
    private void markTerritory(int r, int c, int i) {
        increment(columnHasQueen, c, i);
        increment(upDiagonalHasQueen, r + c, i);
        increment(downDiagonalHasQueen, r - c, i);
    }
    
    private List<String> boardToStrList() {
        ArrayList<String> list = new ArrayList<>();
        for(char[] row : board) {
            list.add(String.valueOf(row));    
        }
        return list;
    }
    
    private void increment(HashMap<Integer, Integer> map, int key, int amount) {
        map.put(key, map.get(key) == null ? amount : map.get(key) + amount);    
    }
}
