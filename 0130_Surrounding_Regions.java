class Solution {
    private boolean[][] visited;
    
    public void solve(char[][] board) {
        visited = new boolean[board.length][board[0].length];
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(!visited[r][c] && board[r][c] == 'O') bfs(board, r, c);
            }
        }
    }
    
    private void bfs(char[][] board, int r, int c) {
        boolean reachesEnd = false; // false => this "island" is completely surrounded by Xs
        
        LinkedList<int[]>  queue = new LinkedList<>();
        LinkedList<int[]> neighbors = new LinkedList<>(); // a list of all cells in this "island"
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            neighbors.add(current);
            
            for(int[] offset : surroundingOffsets) {
                int r1 = current[0] + offset[0];
                int c1 = current[1] + offset[1];
                
                if(r1 < 0 || r1 >= board.length || c1 < 0 || c1 >= board[r].length) {
                    reachesEnd = true;
                    continue;
                }
                
                if(board[r1][c1] == 'X') continue;
                
                if(!visited[r1][c1]) {
                    visited[r1][c1] = true;
                    queue.add(new int[] {r1, c1});
                }
            }
        }
        
        if(reachesEnd) return; // the island is NOT surrounded by Xs, so it is not captured
        
        // set each neighbor to 'X'
        for(int[] coord : neighbors) {
            board[coord[0]][coord[1]] = 'X';
        }
    }
    
    private int[][] surroundingOffsets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
