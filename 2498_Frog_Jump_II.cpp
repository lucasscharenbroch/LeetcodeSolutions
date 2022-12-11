class Solution {
public:
    int maxJump(vector<int>& stones) {
        if(stones.size() == 2) return stones[1] - stones[0]; // base-case
        
        // answer is maximum distance of a single-skip hop (jump OVER a single stone)
        int result = INT_MIN;
        for(int i = 0; i < stones.size() - 2; i++) {
            result = max(result, stones[i + 2] - stones[i]);
        }
        
        return result;
    }
};
