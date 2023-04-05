class Solution {
public:
    int minimizeArrayValue(vector<int>& nums) {
        long long maximum = nums[0];
        long long slack = 0; // difference between maximum * i and nums[0] + ... + nums[i - 1]
        
        for(int i = 1; i < nums.size(); i++) {
            if(nums[i] < maximum) slack += maximum - nums[i];
            else if(nums[i] == maximum);
            else { 
                int diff = min(slack, nums[i] - maximum);
                slack -= diff;
                nums[i] -= diff;
                
                if(nums[i] <= maximum) continue;
                
                // make a new maximum based on remaining amount in nums[i]
                long long total = i * maximum + nums[i]; // total from nums[0] to nums[i]
                
                // maximum = ceil(total / (i + 1))
                maximum = total % (i + 1) == 0 ? total / (i + 1) : total / (i + 1) + 1;
                slack = ((i + 1) * maximum) - total; // update slack accordingly
            }
        }
        
        return maximum;
    }
};
