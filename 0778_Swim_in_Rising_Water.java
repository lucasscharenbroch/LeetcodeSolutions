class Solution {
    public int swimInWater(int[][] grid) {
        // greedy bfs: always explore the unexplored tile with least elevation
        
        int n = grid.length;
        
        boolean[][] vis = new boolean[n][n];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        int timeElapsed = 0;
        
        q.add(new int[] {0, 0}); // start in cell (0, 0)
        
        while(!vis[n - 1][n - 1]) {
            int[] cur = q.poll();
            if(vis[cur[0]][cur[1]]) continue;
            vis[cur[0]][cur[1]] = true;
            timeElapsed = Math.max(timeElapsed, grid[cur[0]][cur[1]]);
            
            // add unvisited neighbors to queue
            for(int[] offset : offsets) {
                int neighR = cur[0] + offset[0];
                int neighC = cur[1] + offset[1];
                
                if(neighR >= 0 && neighR < n && neighC >= 0 && neighC < n && !vis[neighR][neighC])
                    q.add(new int[] {neighR, neighC});
            }
        }
        
        return timeElapsed;
    }
    
    final int[][] offsets = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
}
