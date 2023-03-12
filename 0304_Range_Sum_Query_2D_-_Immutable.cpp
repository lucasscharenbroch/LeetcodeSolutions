class NumMatrix {
public:
    vector<vector<int>> sums; // sums[i][j] stores the sum of all elements (i1, j1) where i1 <= i, j1 <= j
    
    NumMatrix(vector<vector<int>>& matrix) : sums(matrix) {
        // row prefix-sum
        for(int i = 0; i < matrix.size(); i++)
            for(int j = 1; j < matrix[i].size(); j++) 
                sums[i][j] += sums[i][j - 1];
        
        // column prefix-sum
        for(int i = 1; i < matrix.size(); i++)
            for(int j = 0; j < matrix[i].size(); j++) 
                sums[i][j] += sums[i - 1][j];
    }
    
    int sumRegion(int i1, int j1, int i2, int j2) {
        int sum = sums[i2][j2];
        
        if(i1 > 0) sum -= sums[i1 - 1][j2];
        if(j1 > 0) sum -= sums[i2][j1 - 1];
        if(i1 > 0 && j1 > 0) sum += sums[i1 - 1][j1 - 1];
        
        return sum;
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * int param_1 = obj->sumRegion(row1,col1,row2,col2);
 */
