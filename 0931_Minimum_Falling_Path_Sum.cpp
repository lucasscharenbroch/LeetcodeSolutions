class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        // set each cell to its minimum falling path sum
        for(int i = 1; i < matrix.size(); i++) {
            for(int j = 0; j < matrix[i].size(); j++) {
                int elem = matrix[i][j];
                matrix[i][j] = INT_MAX;
                
                for(int o : {-1, 0, 1}) {
                    if(j + o < 0 || j + o >= matrix[i - 1].size()) continue; // no such cell
                    matrix[i][j] = min(matrix[i][j], elem + matrix[i - 1][j + o]);
                }
            }
        }
        
        return *min_element(matrix[matrix.size() - 1].begin(), matrix[matrix.size() - 1].end());
    }
};
