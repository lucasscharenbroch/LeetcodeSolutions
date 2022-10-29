// dfs from each node and memo
class Solution {
    interface OceanCriteria {
        boolean borders(int r, int c);
    }
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        OceanCriteria bordersPacific = (r, c) -> r == 0 || c == 0;
        OceanCriteria bordersAtlantic = (r, c) -> r == heights.length - 1 || c == heights[0].length - 1;
        int rows = heights.length;
        int cols = heights[0].length;
        visiting = new boolean[rows][cols];
        boolean[][] memoA = new boolean[rows][cols], memoP = new boolean[rows][cols];
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                if(canReach(heights, r, c, bordersPacific, memoP) &&
                   canReach(heights, r, c, bordersAtlantic, memoA)) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }
        
        return result;
    }
    
    boolean[][] visiting;
    private boolean canReach(int[][] heights, int r, int c, OceanCriteria oc, boolean[][] memo) {
        if(visiting[r][c]) return false;
        if(oc.borders(r, c) || memo[r][c]) return true;
        
        visiting[r][c] = true;
        
        int lr = heights.length - 1, lc = heights[0].length - 1; // last row and last column
        boolean result = r != 0 && heights[r][c] >= heights[r - 1][c] && canReach(heights, r - 1, c, oc, memo) ||
                         r != lr && heights[r][c] >= heights[r + 1][c] && canReach(heights, r + 1, c, oc, memo) ||
                         c != 0 && heights[r][c] >= heights[r][c - 1] && canReach(heights, r, c - 1, oc, memo) ||
                         c != lc && heights[r][c] >= heights[r][c + 1] && canReach(heights, r, c + 1, oc, memo);
            
        visiting[r][c] = false;
        return memo[r][c] = result;
    }
}

// bfs from oceans
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][] canReachA = new boolean[heights.length][heights[0].length];
        boolean[][] canReachP = new boolean[heights.length][heights[0].length];
        LinkedList<int[]> queueA = new LinkedList<>();
        LinkedList<int[]> queueP = new LinkedList<>();
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        int rows = heights.length;
        int cols = heights[0].length;
        
        // place ocean-bordering cells into their corresponding queues, and set the canReach elements accordingly
        for(int r = 0; r < rows; r++) {
            canReachP[r][0] = true;
            queueP.add(new int[] {r, 0});
            canReachA[r][cols - 1] = true;
            queueA.add(new int[] {r, cols - 1});
        }
        for(int c = 0; c < cols; c++) {
            canReachP[0][c] = true;
            queueP.add(new int[] {0, c});
            canReachA[rows - 1][c] = true;
            queueA.add(new int[] {rows - 1, c});
        }
        
        bfs(heights, canReachA, queueA);
        bfs(heights, canReachP, queueP);
        
        for(int r = 0; r < heights.length; r++) {
            for(int c = 0; c < heights[0].length; c++) {
                if(canReachA[r][c] && canReachP[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }
        
        return result;
    }
    
    final int[][] offsets = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    private void bfs(int[][] heights, boolean[][] canReach, LinkedList<int[]> queue) {
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];
            
            for(int[] offset : offsets) {
                int r1 = r + offset[0];
                int c1 = c + offset[1];
                
                if(r1 >= 0 && r1 < heights.length && c1 >= 0 && c1 < heights[0].length && 
                                   heights[r][c] <= heights[r1][c1] && !canReach[r1][c1]) {
                    canReach[r1][c1] = true;
                    queue.add(new int[] {r1, c1});
                }
            }
        }
    }
}
