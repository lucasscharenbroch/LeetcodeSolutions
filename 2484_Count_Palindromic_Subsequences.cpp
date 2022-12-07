class Solution {
public:
    int countPalindromes(string s) {
        if(s.length() < 5) return 0;
        
        unsigned int prefixCounts[10000][10][10] = {0}; // [i][j][k] => number of subsequence
        unsigned int suffixCounts[10000][10][10] = {0}; // (pre/suf)fixes starting @ i, that are "jk"
        int currentCounts[10] = {0}; // maintains running count of seen digits
        const int modConst = 1e9 + 7;
        
        // count prefixes
        currentCounts[s[0] - '0']++;
        for(int i = 1; i < s.length(); i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= 9; k++) {
                    prefixCounts[i][j][k] = prefixCounts[i - 1][j][k];
                    if(s[i] - '0' == k) prefixCounts[i][j][k] += currentCounts[j];
                }
            }
            currentCounts[s[i] - '0']++;
        }
        
        memset(currentCounts, 0, sizeof(currentCounts)); // clear counts
        
        // count suffixes
        currentCounts[s[s.length() - 1] - '0']++;
        for(int i = s.length() - 2; i >= 0; i--) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= 9; k++) {
                    suffixCounts[i][j][k] = suffixCounts[i + 1][j][k];
                    if(s[i] - '0' == j) suffixCounts[i][j][k] += currentCounts[k];
                }
            }
            currentCounts[s[i] - '0']++;
        }
        
        unsigned long long result = 0;
        
        for(int i = 2; i < s.length() - 2; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = 0; k <= 9; k++) {
                    result += 1LL * prefixCounts[i - 1][j][k] * suffixCounts[i + 1][k][j];
                    result %= modConst;
                }
            }
        }
        
        return result;
    }
};
