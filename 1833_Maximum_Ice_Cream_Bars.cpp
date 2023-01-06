class Solution {
public:
    int maxIceCream(vector<int>& costs, long long coins) {
        sort(costs.begin(), costs.end());
        int result = 0;
        
        for(int& cost : costs) {
            if((coins -= cost) >= 0) result++;
        }
            
        return result;
    }
};
