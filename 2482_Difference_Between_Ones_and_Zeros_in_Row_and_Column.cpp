class Solution {
public:
    vector<vector<int>> onesMinusZeros(vector<vector<int>>& grid) {
        vector<int> rowScores(grid.size(), 0);
        vector<int> colScores(grid[0].size(), 0);
        
        // iterate through grid, counting the "scores" (numOnes - numZeros) of each row
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid[i].size(); j++) {
                if(grid[i][j] == 1) {
                    rowScores[i]++;
                    colScores[j]++;
                } else if(grid[i][j] == 0) {
                    rowScores[i]--;
                    colScores[j]--;
                }
            }
        }
        
        // populate result based on those scores
        vector<vector<int>> result(grid.size(), vector<int>(grid[0].size()));
        
        for(int i = 0; i < result.size(); i++) {
            for(int j = 0; j < result[i].size(); j++) {
                result[i][j] = rowScores[i] + colScores[j];
            }
        }
        
        return result;
    }
};
