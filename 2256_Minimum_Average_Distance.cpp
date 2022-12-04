class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        // compute the average difference of each, and take a running minimum
        long long preSum = 0;
        long long restSum = 0;
        for(int& n : nums) restSum += n;
        
        long long minAvgDist = LONG_MAX;
        int resultIndex = -1;
        
        for(int i = 0; i < nums.size(); i++) {
            preSum += nums[i];
            restSum -= nums[i];
            
            int preAvg = preSum / (i + 1);
            int restAvg = (restSum == 0) ? 0 : restSum / (nums.size() - i - 1);
            
            int avgDist = abs(preAvg - restAvg);
            
            if(avgDist < minAvgDist) {
                minAvgDist = avgDist;
                resultIndex = i;
            }
        }
        
        return resultIndex;
    }
};
