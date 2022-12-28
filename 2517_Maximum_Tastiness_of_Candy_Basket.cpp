class Solution {
public:
    int maximumTastiness(vector<int>& price, int k) {
        sort(price.begin(), price.end());
        
        // binary-search-the-answer
        int low = 0;
        int high = INT_MAX / 2;
        
        while(low < high) {
            int mid = (low + high + 1) / 2;
            
            if(isValid(price, k, mid)) low = mid;
            else high = mid - 1;
        }
        
        return low;
    }
    
    bool isValid(vector<int>& price, int k, int tastiness) {
        long long lastTaken = INT_MIN; // last price taken
        
        for(int i = 0; i < price.size(); i++) {
            if(k == 0) break;
            if(price[i] - lastTaken >= tastiness) {
                lastTaken = price[i];
                k--;
            }
        }
        
        return k == 0;
    }
};
