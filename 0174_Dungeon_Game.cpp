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
