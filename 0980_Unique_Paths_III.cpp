class Solution {
    int startR, startC, endR, endC, m, n, numWays, numSeen;
    vector<vector<int>> offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
public:
    
    int uniquePathsIII(vector<vector<int>>& grid) {
        numWays = 0;
        numSeen = 0;
        m = grid.size();
        n = grid[0].size();
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    startR = i;
                    startC = j;
                } else if(grid[i][j] == 2) {
                    endR = i;
                    endC = j;
                } else if(grid[i][j] == -1) {
                    numSeen++; // count each off-limits square as "seen" (so when numSeen == n * m),
                               // all visitable squares are seen
                }
            }
        }
        
        dfs(startR, startC, grid);
        
        return numWays;
    }
    
    void dfs(int r, int c, vector<vector<int>>& grid) {
        grid[r][c] = -1;
        numSeen++;
        
        if(r == endR && c == endC && numSeen == m * n) numWays++;
        for(vector<int>& offset : offsets) {
            int r1 = r + offset[0], c1 = c + offset[1];
            if(r1 >= 0 && r1 < m && c1 >= 0 && c1 < n && grid[r1][c1] != -1) dfs(r1, c1, grid);
        }
        
        grid[r][c] = 0;
        numSeen--;
    }
};
