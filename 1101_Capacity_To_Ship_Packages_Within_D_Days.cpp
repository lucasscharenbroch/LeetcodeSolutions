class Solution {
public:
    int shipWithinDays(vector<int>& weights, int days) {
        // binary-search-the-answer
        long long low = 0, high = INT_MAX;
        
        for(int& w : weights) low = max(low, (long long)w);
        
        while(low < high) {
            int mid = (low + high) / 2;
            
            // do "simulation"
            int days_remaining = days;
            int capacity_left = mid;
            
            for(int& w : weights) {
                if(w > capacity_left) {
                    days_remaining--;
                    capacity_left = mid;
                }
                
                capacity_left -= w;
            }
            
            if(days_remaining > 0) high = mid;
            else low = mid + 1;
        }
        
        return low;
    }
};
