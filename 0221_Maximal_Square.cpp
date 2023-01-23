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

// DP (O(n * m))
class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        // three 2d DP arrays:
        int dpSquare[300][300] = {0};   // largest square whose bot-right corner = (i, j)
        int dpVertLine[300][300] = {0}; // longest vertical line whose bot point is (i, j)
        int dpHorzLine[300][300] = {0}; // longest horizontal line whose bot point is (i, j)
        
        int n = matrix.size();
        int m = matrix[0].size();
        
        int largestSquareSide = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0) dpVertLine[i][j] = matrix[i][j] == '1';
                else dpVertLine[i][j] = matrix[i][j] == '0' ? 0 : 1 + dpVertLine[i - 1][j];
                
                if(j == 0) dpHorzLine[i][j] = matrix[i][j] == '1';
                else dpHorzLine[i][j] = matrix[i][j] == '0' ? 0 : 1 + dpHorzLine[i][j - 1];
                
                if(i == 0 || j == 0) dpSquare[i][j] = matrix[i][j] == '1';
                else if(matrix[i][j] == '0') dpSquare[i][j] = 0;
                else dpSquare[i][j] = 1 + min(dpSquare[i - 1][j - 1], min(dpVertLine[i - 1][j],
                                                                      dpHorzLine[i][j - 1]));
                largestSquareSide = max(largestSquareSide, dpSquare[i][j]);
            }
        }
        
        return largestSquareSide * largestSquareSide;
    }
};
