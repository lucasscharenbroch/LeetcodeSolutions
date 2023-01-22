class Solution {
public:
    vector<vector<string>> result;
    vector<string> current_partition;
    
    vector<vector<string>> partition(string s) {
        const int n = s.length();
        vector<vector<bool>> isPalindrome(n, vector<bool>(n));
        
        // fill isPalindrome
        for(int i = 0; i < n; i++) for(int j = 0; j < n; j++) {
            int l = i, r = j;
            while(l < r && s[l] == s[r]) l++, r--;
            isPalindrome[i][j] = l >= r;
        }
        
        solve(s, 0, isPalindrome);
        return result;
    }
    
    void solve(string&s, int i, vector<vector<bool>>& isPalindrome) {
        if(i == s.length()) result.push_back(current_partition);
        for(int j = i; j < s.length(); j++) {
            if(isPalindrome[i][j]) {
                current_partition.push_back(s.substr(i, j - i + 1));
                solve(s, j + 1, isPalindrome);
                current_partition.pop_back();
            }
        }
    }
};
