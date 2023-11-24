class Solution {
public:
    int maxCoins(vector<int>& piles) {
        // optimal = give bob lowest (1/3), take alternating coins of highest (2/3).
        sort(piles.begin(), piles.end());
        
        int res = 0;
        
        for(int i = piles.size() / 3; i < piles.size(); i += 2)
            res += piles[i];
        
        return res;
    }
};
