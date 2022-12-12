class Solution {
public:
    int deleteGreatestValue(vector<vector<int>>& grid) {
        for(vector<int>& row : grid) sort(row.begin(), row.end()); // sort each row
        
        int result = 0;
        
        // add the max of each column to the result
        for(int c = 0; c < grid[0].size(); c++) {
            int maxElem = grid[0][c];
            for(int r = 0; r < grid.size(); r++) maxElem = max(maxElem, grid[r][c]);
            result += maxElem;
        }
        
        return result;
    }
};
