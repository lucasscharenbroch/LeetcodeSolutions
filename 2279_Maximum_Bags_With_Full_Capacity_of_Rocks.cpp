class Solution {
public:
    int maximumBags(vector<int>& capacity, vector<int>& rocks, int additionalRocks) {
        for(int i = 0; i < capacity.size(); i++) { // update capacity based on amount of rocks
            capacity[i] -= rocks[i];
        }
      
        // greedily fill the emptiest bags
        
        int i = 0;
        
        sort(capacity.begin(), capacity.end());
        
        for(; i < capacity.size(); i++) {
            if(capacity[i] > additionalRocks) break;
            additionalRocks -= capacity[i];
        }
        
        return i;
    }
};
