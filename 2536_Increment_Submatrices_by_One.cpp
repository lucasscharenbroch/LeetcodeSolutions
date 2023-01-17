class Solution {
public:
    vector<vector<int>> rangeAddQueries(int n, vector<vector<int>>& queries) {
        // mark the left-side-start of each rectangle and the right-side-end, then
        // use the prefix-sum of those marks to determine the values of each cell in the result
        
        vector<vector<int>> result(n, vector<int>(n));
        
        for(vector<int>& q : queries) {
            int rs = q[0], cs = q[1], re = q[2], ce = q[3];
            for(int r = rs; r <= re; r++) {
                result[r][cs]++;
                if(ce + 1 < n) result[r][ce + 1]--;
            }
        }
        
        for(int r = 0; r < n; r++) {
            int value = 0;
            for(int c = 0; c < n; c++) {
                value += result[r][c];
                result[r][c] = value;
            }
        }
        
        return result;
    }
};
