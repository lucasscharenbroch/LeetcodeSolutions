class Solution {
    public int orangesRotting(int[][] grid) {
        final int FRESH = 1;
        final int ROTTEN = 2;
        
        LinkedList<int[]> q = new LinkedList<>(); // queue for bfs
        boolean[][] vis = new boolean[grid.length][grid[0].length]; // visited matrix
        int numFresh = 0; // counter of num remaining fresh oranges, used to determine
                          // if bfs successfully converted all fresh oranges (otherwise return -1)
        int time = 0; // number of breadths searched to convert all bordering fresh oranges
        
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == ROTTEN) q.add(new int[] {r, c});
                else if(grid[r][c] == FRESH) numFresh++;
            }
        }
        
        final int[][] offsets = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        while(!q.isEmpty()) {
            if(numFresh == 0) break;
            int n = q.size();
            time++;
            while(n-- > 0) {
                int[] current = q.poll();
                int r = current[0], c = current[1];
                
                for(int[] offset : offsets) {
                    int rDest = r + offset[0], cDest = c + offset[1];
                    if(rDest >= 0 && rDest < grid.length && cDest >= 0 && cDest < grid[0].length &&
                                             grid[rDest][cDest] == FRESH && !vis[rDest][cDest]) {
                        vis[rDest][cDest] = true;
                        numFresh--;
                        q.add(new int[] {rDest, cDest});
                    }
                }
                
            }
        }
        
        return (numFresh == 0) ? time : -1;
    }
}
