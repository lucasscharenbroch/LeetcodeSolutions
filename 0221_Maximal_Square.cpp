// Brute-Force (binary-search-the-answer), O(n * m * lg(min(n, m)))
class Solution {
public:
    // returns true if matrix has a NxN square
    bool hasSquare(vector<vector<char>>& matrix, int n) {
        vector<int> rowSum(matrix[0].size(), 0); // the sum of n consecurive rows
        for(int i = 0; i < n; i++) 
            for(int j = 0; j < matrix[i].size(); j++) 
                rowSum[j] += matrix[i][j] - '0';
        
        for(int i = 0; i <= matrix.size() - n; i++) {
            // scan rowSum and check for a subarray of length n whose values are all n
            int currentNumN = 0;
            for(int& s : rowSum) {
                if(s == n) currentNumN++;
                else currentNumN = 0;
                
                if(currentNumN == n) return true;
            }
            
            if(i == matrix.size() - n) break;
            for(int j = 0; j < matrix[i].size(); j++) rowSum[j] += matrix[i + n][j] - matrix[i][j];
        }
        
        return false;
    }
    
    int maximalSquare(vector<vector<char>>& matrix) {
        // binary-search-the-answer
        int l = 0, r = min(matrix.size(), matrix[0].size());
        while(l < r) {
            int mid = (l + r + 1) / 2;
            if(hasSquare(matrix, mid)) l = mid;
            else r = mid - 1;
        }
        return l * l;
    }
};
