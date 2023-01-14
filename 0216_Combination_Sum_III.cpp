class Solution {
public:
    vector<vector<int>> result;
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> combo;
        solve(combo, 1, k, n);
        
        return result;
    }
    
    // c = currentNumber k = numNumsRemaining, n = targetSum
    void solve(vector<int>& combo, int c, int k, int n) { 
        if(n == 0 && k == 0) return result.push_back(combo);
        if(c == 10) return; // spent all numbers
        if(n - c < 0) return; // current number is too large
        
        solve(combo, c + 1, k, n); // don't use current k
        
        // use current c
        combo.push_back(c);
        solve(combo, c + 1, k - 1, n - c);
        combo.pop_back();
    }
};
