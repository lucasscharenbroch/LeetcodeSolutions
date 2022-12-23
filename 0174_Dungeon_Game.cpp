// Binary-Search-The-Answer, O(n * m * lg(-MIN_BONUS * m * n + 1))
class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int low = 1, high = 1000 * 200 * 200 + 1;
        
        while(low != high) {
            int mid = (low + high) / 2;
            
            if(canRescue(dungeon, mid)) high = mid;
            else low = mid + 1;
        }
        
        return low;
    }
    
    bool canRescue(vector<vector<int>>& dungeon, int initialHealth) {
        vector<vector<int>> copy = dungeon;
        copy[0][0] += initialHealth;
        
        for(int j = 1; j < copy[0].size(); j++) {
            copy[0][j] = copy[0][j - 1] > 0 ? copy[0][j - 1] + copy[0][j] : 0;
        }
        for(int i = 1; i < copy.size(); i++) {
            copy[i][0] = copy[i - 1][0] > 0 ? copy[i - 1][0] + copy[i][0] : 0;
        }
        
        for(int i = 1; i < copy.size(); i++) {
            for(int j = 1; j < copy[i].size(); j++) {
                int inc = max(copy[i - 1][j], copy[i][j - 1]);
                if(inc <= 0) copy[i][j] = 0;
                else copy[i][j] += inc;
            }
        }
        
        return copy[copy.size() - 1][copy[0].size() - 1] > 0;
    }
    
};

// Bottom-Up-DP O(n * m)
class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        int n = dungeon.size();
        int m = dungeon[0].size();
        
        int dp[200][200]; // dp[i][j] = min health needed from (i, j) to (n - 1, m - 1)
        
        int lr = n - 1; // last row
        int lc = m - 1; // last col
        
        dp[lr][lc] = max(1, 1 - dungeon[lr][lc]);
        
        for(int i = n - 2; i >= 0; i--)  // last col
            dp[i][lc] = max(max(1, 1 - dungeon[i][lc]), dp[i + 1][lc] - dungeon[i][lc]);
        for(int j = m - 2; j >= 0; j--)  // last row
            dp[lr][j] = max(max(1, 1 - dungeon[lr][j]), dp[lr][j + 1] - dungeon[lr][j]);
        
        for(int i = n - 2; i >= 0; i--) {
            for(int j = m - 2; j >= 0; j--) {
                dp[i][j] = max( // max(min-possible-life, min-to-get-past-this-cell, min-to-get-to-end)
                                max(1, 1 - dungeon[i][j]), // 1, score needed to get past (i, j)
                                min(dp[i + 1][j] - dungeon[i][j], dp[i][j + 1] - dungeon[i][j]) // rest
                              );
            }
        }
        
        return dp[0][0];
    }
};
